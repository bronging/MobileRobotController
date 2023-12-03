package com.nsg.addon.rescue.modeling;

import java.awt.Point;

public interface ModelingInterface {
	/**
	 * 지도 생성 : 지도 크기 m X n 입력
	 */
	public void create (int m, int n);
	
	
	/**
	 * 지도 업데이트 함수 
	 * @param x 업데이트할 x 좌표
	 * @param y 업데이트할 y 좌표
	 * @param elem 업데이트할 요소 - enum Element
	 */
	public void updateMapElem(Point pos, Element elem);
	
	/**
	 * 특정 위치의 요소 반환 
	 * @param pos
	 * @return
	 * Element 
	 */
	public Element getElem(Point pos);
	
	/**
	 * 로봇의 현재 위치를 기준으로 가장 가까운 탐색 지점 반환 
	 * @param p  로봇의 현재 위치 
	 * @return 가장 가까운 탐색 지점 좌표 
	 */
	public Point getSearchPos(Point p);
	
	/**
	 * 탐색 지점 방문 표시 
	 * @param p 방문한 탐색 지점 좌표 
	 */
	public void addVisited(Point p);

	/**
	 *  
	 * @return 로봇의 현재 방향에 따른 전방 1칸 앞의 좌표
	 */
	public Point getForwardPos(Point position, Direction direction);
}
