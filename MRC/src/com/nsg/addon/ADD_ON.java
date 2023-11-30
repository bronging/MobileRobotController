package com.nsg.addon;

import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.rescue.modeling.Map;
import com.nsg.addon.rescue.modeling.RealMap;
import com.nsg.addon.rescue.control.RobotController;
import com.nsg.addon.voice.manager.VoiceRecMananger;
import com.nsg.addon.gui.event.EventManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ADD_ON {
	
	private static ADD_ON instance = new ADD_ON();

	private Map modelingMap; 
	private RobotController robotController;
	private VoiceRecMananger voiceRecManager; 
	private EventManager eventManager; 
	private ArrayList<Point> path;  
	private int numOfSearch;       // 전체 탐색 지점 개수 
	private int visited;           // 방문한 탐색 지점의 개수 
	private Point target;          // 현재 탐색할 목표 탐색 지점 
	
	public static ADD_ON getInstance() {
		return instance; 
	}
	
	private ADD_ON() {
		modelingMap = new Map();
		robotController = new RobotController();
		voiceRecManager = new VoiceRecMananger();
		eventManager = new EventManager();
		numOfSearch = 0;
		visited = 0; 
	}
	
	/**
	 * 로봇의 초기 위치를 설정한다. 
	 * @param pos
	 * 로봇 초기 위치 
	 */
	public void initRobot(Point pos) {
		robotController.initRobot(pos);
	}
	
	/**
	 * 지도 생성 
	 * - realmap, modelingmap 함께 생성 
	 * @param m 
	 * 지도의 행 크기 
	 * @param n
	 * 지도의 열 크기 
	 */
	void createMap(int m, int n) {
		modelingMap.create(m, n);
		RealMap.getInstance().createRealMap(m, n);
	}
	
	/**
	 * real 지도 값 업데이트 
	 * @param pos
	 * 위치 
	 * @param elem
	 * 위험, 중요 지점 정보 
	 */
	void updateRealMap(Point pos, Element elem) {
		RealMap.getInstance().updateRealMapElem(pos, elem);
		//eventManager.updateRealMap(pos, elem); // UI 업데이트 
	}
	
	/**
	 * modeling 지도 값 업데이트 
	 * @param pos
	 * 위치 
	 * @param elem
	 * 위험, 중요 지점 정보 
	 */
	public void updateMap(Point pos, Element elem) {
		modelingMap.updateMapElem(pos, elem);
		//eventManager.updateModelingMap(pos, elem); // UI 업데이트 
	}
	
	/**
	 * 탐색 위치를 업데이트 한다. 
	 * 모델링 지도와 real map 모두 업데이트한다. 
	 * @param pos
	 * 탐색 지점의 위치
	 */
	public void updateSearchPos(Point pos) {
		RealMap.getInstance().updateRealMapElem(pos, Element.SEARCHPOINT);
		modelingMap.updateMapElem(pos, Element.SEARCHPOINT);
		numOfSearch++;	
	}
	
	/**
	 * 모든 탐색 지점을 순회하며 구조 작업을 진행 
	 */
	public void rescue() {
		// 탐색 지점마다 경로를 계산해서 search 호출 
		while(visited < numOfSearch) {
			getTarget(); 		 // 탐색 지점 선택 
			calcPath(); 		 // 탐색 지점까지의 경로 계산			
			search(); 			 // 해당 탐색 지점까지 구조 작업
			modelingMap.addVisited(target); // 탐색 완료 표시 
			visited++;
			
			//eventManager.rescueDone(target);   // 화면에 구조 한 거 표시 
			
			try {
				Thread.sleep(1000); // delay 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		return;
	}
	
	/**
	 * 특정 탐색 지점에 대해 탐색 작업 진행. 
	 * 경로를 따라 로봇을 제어한다. 
	 */
	public void search() {
		
		while (!path.isEmpty()) { // 탐색 지점까지 모든 경로를 방문할 때까지 	
			robotController.setNext(path.get(0)); //계산된 경로를 넘겨줌
			
			do {
				
				robotController.rotate(); 		  //올바른 방향으로 로봇 회전
			
				if(robotController.pretask()) {   //움직이기 전 센서값 읽어서 지도 반영
					calcPath();					  //경로상 위험 지점 -> 경로 재계산 
				}
				else{
					robotController.control();    //로봇 움직인다
				
					// 움직인 결과 화면에 띄우고 
					//eventManager.updateRobotPos(robotController.getRobotPos());
				}
			}while(!robotController.validation());//로봇이 올바르게 움직일 때까지 반복 
		
			path.remove(0);
			
			//TODO test code -> 지우기 
			printMap();
		}
	}
	
	/**
	 * 로봇의 현재 위치로부터 가장 가까운 탐색 지점을 찾아낸다. 
	 */
	public void getTarget() {
		target = modelingMap.getSearchPos(robotController.getRobotPos());
	}
	
	
	/**
	 * 로봇의 현재위치로 부터 탐색지점까지의 경로 계산 
	 *  
	 */
	public void calcPath() {
		Point v, adj, temp;
		
		Point start = robotController.getRobotPos();
		Direction[] prior = getDirPrior(start);
		
		Queue<Point> q = new LinkedList<Point>();
		Point[][] predecessor = new Point[modelingMap.getM()][modelingMap.getN()];
		 
		q.add(start);
		
		while(!q.isEmpty()) {
			v = q.remove();
			if(v == target) break; 
			
			for(int i = 0; i < 4; i++) {
				adj = modelingMap.getForwardPos(v, prior[i]); //v의 인접 노드를 방문
		
				if((modelingMap.isInMap(adj))
						&& (predecessor[adj.y][adj.x] == null)) {
					if(modelingMap.getElem(adj) != Element.HAZARD) { //위험 지점이 아닌 경우 
						predecessor[adj.y][adj.x] = v; // adj 직전 노드를 v로 기록
						q.add(adj);
					}
					 
					
				}
			}
		}
		
		q.clear();
		
		path = new ArrayList<Point>();
		
		temp = target;
		path.add(0, temp);
		
		while(temp != start) {
			temp = predecessor[temp.y][temp.x];
			path.add(0, temp);
		}
		
		printPath();
	
	}
	
	
	/**
	 * 목표 지점으로 가기 위한 방향을 계산한다. 
	 * @param start
	 * 시작 위치 
	 * @return
	 * 방향 우선 순위. 0, 1순위 방향으로 경로를 계산한다. 
	 */
	public Direction[] getDirPrior(Point start) {
		Direction prior[] = new Direction[4];
		
		if(start.y == target.y) { // r 이 같은 경우
			prior[0] = Direction.NORTH; prior[2] = Direction.SOUTH; 
			if(start.x < target.x) {
				prior[1] = Direction.EAST; prior[3] = Direction.WEST;}
			else {
				prior[1] = Direction.WEST; prior[3] = Direction.EAST;}
		}
		else if(start.x == target.x) { // c 가 같은 경우 
			prior[1] = Direction.EAST; prior[2] = Direction.WEST;
			if(start.y > target.x) {
				prior[0] = Direction.NORTH;  prior[3] = Direction.SOUTH;}
			else {
				prior[0] = Direction.SOUTH;  prior[3] = Direction.NORTH;}
		}
		
		else if((start.y > target.y) && (start.x < target.x)) {
			prior[0] = Direction.NORTH; prior[1] = Direction.EAST; prior[2] = Direction.SOUTH; prior[3] = Direction.WEST;
		}
		else if((start.y < target.y) && (start.x > target.x)) {
			prior[0] = Direction.SOUTH; prior[1] = Direction.WEST; prior[2] = Direction.EAST; prior[3] = Direction.NORTH;
		}
		else if((start.y < target.y) && (start.x < target.x)) {
			prior[0] = Direction.SOUTH; prior[1] = Direction.EAST; prior[2] = Direction.NORTH; prior[3] = Direction.WEST;
		}
		else {
			prior[0] = Direction.NORTH; prior[1] = Direction.WEST; prior[2] = Direction.SOUTH; prior[3] = Direction.EAST;
		}
		
		return prior;
	}
	
	
	/**
	 * 
	 * @return modeling map의 행 크기 
	 */
	public int getM() {
		return modelingMap.getM();
	}
	
	
	/** 
	 * 
s	 * @return modeling map의 열 크기 
	 */
	public int getN() {
		return modelingMap.getN();
	}
	
	
	public Element[][] getModelingMap() {
		return modelingMap.getMap();
	}
	
	public Element[][] getRealMap() {
		return RealMap.getInstance().getRealMap();
	}
	
	public Point getRobotPos() {
		return robotController.getRobotPos();
	}
	
	public void startVoiceRec() {
		voiceRecManager.startRecording();
	}
	
	public void stopVoiceRec() {
		voiceRecManager.stopRecording();
	}
	
	
	public void voiceRst(Point pos, Element elem) {
		//eventManager.showVoiceRst(pos, elem);
	}
	
 	public void printPath() {
		System.out.println("PATH >>>>>");
		for(int i = 0; i < path.size(); i++)
			System.out.println(path.get(i));
	}

	//테스트용 map 객체 프린트 
	public void printMap() {
		Point p;
		for(int c = 0; c < modelingMap.getN() + 2; c++) 
			System.out.print("=");
		System.out.println();
		
		for(int r = 0; r < modelingMap.getM(); r++) {
			System.out.print("+");
			for(int c = 0; c < modelingMap.getN(); c++) {
				Element e;
				p = robotController.getRobotPos();
				if((e = modelingMap.getElem(new Point(c, r))) == Element.NONE)
					System.out.print("-");
				else if(e == Element.HAZARD)
					System.out.print("H");
				else if(e == Element.COLORBLOB)
					System.out.print("C");
				else if(e == Element.SEARCHPOINT)
					System.out.print("#");
				
				
				if((p!=null) && (p.x == c) && (p.y == r)) {
					System.out.print("R");
				}
			}
			System.out.println("+");
		}
		
		for(int c = 0; c < modelingMap.getN() + 2; c++) 
			System.out.print("=");
		
		System.out.println();
	}
	
	//
	public void printRealMap() {
		System.out.println("REAL MAP <<<<<<<<<<"); 
		Point p;
		for(int c = 0; c < modelingMap.getN() + 2; c++) 
			System.out.print("=");
		System.out.println();
		
		for(int r = 0; r < modelingMap.getM(); r++) {
			System.out.print("+");
			for(int c = 0; c < modelingMap.getN(); c++) {
				Element e;
				p = robotController.getRobotPos();
				e = RealMap.getInstance().getRealMapElem(new Point(c, r));
				if(e == Element.NONE)
					System.out.print("-");
				else if(e == Element.HAZARD)
					System.out.print("H");
				else if(e == Element.COLORBLOB)
					System.out.print("C");
				else if(e == Element.SEARCHPOINT)
					System.out.print("#");
				
				
				if((p!=null) && (p.x == c) && (p.y == r)) {
					System.out.print("R");
				}
			}
			System.out.println("+");
		}
		
		for(int c = 0; c < modelingMap.getN() + 2; c++) 
			System.out.print("=");
		
		System.out.println();
	}
}
