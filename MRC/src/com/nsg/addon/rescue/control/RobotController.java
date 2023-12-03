package com.nsg.addon.rescue.control;

import com.nsg.sim.Movement;
import com.nsg.sim.Rotate;
import com.nsg.sim.SIM;
import java.awt.Point;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.ADD_ON;

public class RobotController {
	private SIM sim;	
	private Direction robotDrct; // 로봇의 현재 방향 
	private Point robotPos;      // 로봇의 현재 위치 
	private Point prevPos; 		 // 로봇이 움직이기 전의 위치 
	private Point next;          // 다음에 움직여야 하는 위치 
	
	public RobotController() {
		sim = new SIM();
	}
	
	public void initRobot(Point pos) {
		sim.init(pos);
	}
	
	public Point getRobotPos() {
		robotPos = sim.robotPosition();
		return robotPos;
	}
	
	public Point getPrevRbPos() {
		return prevPos; 
	}
	
	public void stopRobot() {
		sim.stopRobot();
	}
	
	/**
	 * 다음에 가야하는 경로 상의 좌표 값 설정 
	 * @param pos 다음 좌표 
	 */
	public void setNext(Point pos) {
		this.next = pos; 
	}
	
	/**
	 * 로봇을 움직여야 하는 방향에 맞춰 회전시킴  
	 */
	public void rotate() {	
		
		Direction nextd;
		int dr, dc;
		
		robotPos = sim.robotPosition(); //현재 위치
		
		dr = next.y - robotPos.y;
		dc = next.x - robotPos.x;
		
		// 로봇이 움직여야 하는 방향 파악 
		if(dr == 0 && dc == 1)
			nextd = Direction.EAST;
		else if(dr == 1 && dc == 0)
			nextd = Direction.SOUTH;
		else if(dr == 0 && dc == -1)
			nextd = Direction.WEST;
		else
			nextd = Direction.NORTH;
		
		robotDrct = sim.robotDirection();
		
		//움직여야 하는 방향까지 로봇을 회전시킴  
		while(robotDrct != nextd)
			robotDrct = sim.rotate(Rotate.CLOCK_90_ROTATION);
	}
	
	/**
	 * 로봇을 움직이기 전에 해야하는 작업. 
	 *
	 * 로봇의 센서 값을 읽어 중요 지점과 위험 지점을 파악한다.  
	 * 
	 * 필요하면 지도를 업데이트 한다. 
	 * 
	 * 
	 * @return 경로 재계산 필요 여부
	 * 
	 *   위험 지점이 발견되면 경로 재탐색 진행 
	 */
	public boolean pretask() {
		
		boolean rst = false;
		
		int hazard, colorblob;
				
		// 움직이기 전에 중요 지점 탐지 
		colorblob = sim.isColorBlob();
		
		if((colorblob & 1) == 1) {
			ADD_ON.getInstance().updateMap(getForwardPos(), Element.COLORBLOB);
		}
		if((colorblob>>1 & 1) == 1) { 
			ADD_ON.getInstance().updateMap(getBackwardPos(), Element.COLORBLOB);
		}
					
		if((colorblob>>2 & 1) == 1) { 
			ADD_ON.getInstance().updateMap(getLeftPos(), Element.COLORBLOB);
		}
		if((colorblob>>3 & 1) == 1) { 
			ADD_ON.getInstance().updateMap(getRightPos(), Element.COLORBLOB);
		}				
					
		// 움직이기 전에 위험 지점 탐지 
		hazard = sim.isHazard();
		
		if(hazard == 1) {
			ADD_ON.getInstance().updateMap(getForwardPos(), Element.HAZARD);
			rst = true;
		}
		
		return rst;
	}
	
	public void avoid() {
		//현재 이동 방향이 아닌 방향으로 이동 
		robotDrct = sim.rotate(Rotate.CLOCK_90_ROTATION);
		robotPos = sim.move(Movement.FORWARD);
	}
	
	/**
	 * 로봇 이동 제어.
	 * @return
	 */
	public void control() {
		prevPos = robotPos;
		robotPos = sim.move(Movement.FORWARD);
	}
	
	/**
	 * @return 로봇이 올바르게 이동하였는지 여부 
	 */
	public boolean validation() {
		return samePos(next, robotPos);
	}
	
	/**
	 * 두 좌표가 같은지 비교 연산 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean samePos(Point p1, Point p2) {
		if((p1.x == p2.x) && (p1.y == p2.y))
			return true;
		else
			return false;
	}
	
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 전방 1칸 앞의 좌표 
	 */
	public Point getForwardPos() {
		int c = robotPos.x;
		int r = robotPos.y;
		
		if(robotDrct == Direction.EAST)
			c += 1;
		else if(robotDrct == Direction.WEST)
			c -= 1;
		else if(robotDrct == Direction.NORTH)
			r -= 1;
		else if(robotDrct == Direction.SOUTH)
			r += 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 후방 1칸 의 좌표 
	 */
	public Point getBackwardPos() {
		int c = robotPos.x;
		int r = robotPos.y;
		
		if(robotDrct == Direction.EAST)
			c -= 1;
		else if(robotDrct == Direction.WEST)
			c += 1;
		else if(robotDrct == Direction.NORTH)
			r += 1;
		else if(robotDrct== Direction.SOUTH)
			r -= 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 좌측 1칸 옆의 좌표 
	 */
	public Point getLeftPos() {
		int c = robotPos.x;
		int r = robotPos.y;
		
		if(robotDrct == Direction.EAST)
			r -= 1;
		else if(robotDrct == Direction.WEST)
			r += 1;
		else if(robotDrct == Direction.NORTH)
			c -= 1;
		else if(robotDrct == Direction.SOUTH)
			c += 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 우측 1칸 옆의 좌표 
	 */
	public Point getRightPos() {
		int c = robotPos.x;
		int r = robotPos.y;
		
		if(robotDrct == Direction.EAST)
			r += 1;
		else if(robotDrct == Direction.WEST)
			r -= 1;
		else if(robotDrct == Direction.NORTH)
			c += 1;
		else if(robotDrct == Direction.SOUTH)
			c -= 1;
		
		return new Point(c, r);
	}
}
