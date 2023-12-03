package com.nsg.addon.rescue.modeling;

import java.awt.Point;
import java.util.ArrayList;

public class Map implements ModelingInterface{

	protected int m, n; 
	protected Element[][] map;
	protected ArrayList<Point> search_pts; // 탐색 지점
	protected ArrayList<Point> visited;    // 방문한 탐색 지점
	private int numOfSearch, idx;  
	
	@Override
	public void create (int m, int n) {
		this.m = m;
		this.n = n;
		
		search_pts = new ArrayList<>();
		visited = new ArrayList<>();
		map = new Element[m][n];
		numOfSearch = 0;
		idx = 0;
		
		for (int r = 0; r < m; r++)
			for(int c = 0; c < n; c++)
				map[r][c] = Element.NONE;
	}
	
	@Override
	public void updateMapElem(Point pos, Element elem) {
		int r = pos.y;
		int c = pos.x;
		map[r][c] = elem;

		if((c >= 0 && c < this.n) && (r >= 0 && r < this.m)){
			map[r][c] = elem;
		}
		
		if(elem == Element.SEARCHPOINT) {
			search_pts.add(pos);
		}
	}
	
	
	@Override
	public Point getSearchPos(Point p) {
		/** TODO
		 * 가장 가까운 탐색 지점을 찾아냄 
		 */
		return search_pts.get(idx++);
	}
	
	@Override
	public void addVisited(Point p) {
		visited.add(p);
	}
	
	/**
	 * 특정 좌표가 지도 범위 안에 있는지 여부 반환 
	 * @param p
	 * @return
	 */
	public boolean isInMap(Point p) {
		return (p.x >= 0 && p.x < n) && (p.y >= 0 && p.y < m); 
	}
	
	
	/**
	 * 특정 위치의 요소 반환 
	 * @param pos
	 * @return
	 * Element 
	 */
	public Element getElem(Point pos) {
		return map[pos.y][pos.x];
	}
	
	
	public ArrayList<Point> getAllSearch() {
		return search_pts;
	}
	
	@Override
	public Point getForwardPos(Point position, Direction direction) {
		int c = position.x;
		int r = position.y;
		
		if(direction == Direction.EAST)
			c += 1;
		else if(direction == Direction.WEST)
			c -= 1;
		else if(direction == Direction.NORTH)
			r -= 1;
		else if(direction == Direction.SOUTH)
			r += 1;
		
		return new Point(c, r);
	}
	
	/**
	 *
	 * @return  로봇의 현재 방향에 따른 후방 1칸 의 좌표
	 *  
	 */
	public Point getBackwardPos(Point position, Direction direction) {
		int c = position.x;
		int r = position.y;
		
		if(direction == Direction.EAST)
			c -= 1;
		else if(direction == Direction.WEST)
			c += 1;
		else if(direction == Direction.NORTH)
			r += 1;
		else if(direction == Direction.SOUTH)
			r -= 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 좌측 1칸 옆의 좌표
	 *  
	 */
	public Point getLeftPos(Point position, Direction direction) {
		int c = position.x;
		int r = position.y;
		
		if(direction == Direction.EAST)
			r -= 1;
		else if(direction == Direction.WEST)
			r += 1;
		else if(direction == Direction.NORTH)
			c -= 1;
		else if(direction == Direction.SOUTH)
			c += 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 우측 1칸 옆의 좌표 
	 * 
	 */
	public Point getRightPos(Point position, Direction direction) {
		int c = position.x;
		int r = position.y;
		
		if(direction == Direction.EAST)
			r += 1;
		else if(direction == Direction.WEST)
			r -= 1;
		else if(direction == Direction.NORTH)
			c += 1;
		else if(direction == Direction.SOUTH)
			c -= 1;
		
		return new Point(c, r);
	}

	/**
	 * 
	 * @return map 객체 
	 */
	public Element[][] getMap() {
		return map;
	}
	public int getM() {
		return this.m;
	}
	public int getN() {
		return this.n;
	}
	
}