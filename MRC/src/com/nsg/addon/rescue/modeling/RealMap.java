package com.nsg.addon.rescue.modeling;

import java.awt.Point;
import java.util.ArrayList;


public class RealMap{
	private static RealMap instance = new RealMap();
	
	private int m, n; 
	private Element[][] realmap;
	
	
	private RealMap () {
		
	}
	
	public static RealMap getInstance() {
		return instance; 
	}
	
	/**
	 * 지도 생성 : 지도 크기 m X n 입력
	 */
	public void createRealMap(int m, int n) {
		this.m = m;
		this.n = n;
		
		realmap = new Element[m][n];
		
		for (int r = 0; r < m; r++)
			for(int c = 0; c < n; c++)
				realmap[r][c] = Element.NONE;
	}
	
	
	/**
	 * 지도 업데이트 함수 
	 * @param x 업데이트할 x 좌표
	 * @param y 업데이트할 y 좌표
	 * @param elem 업데이트할 요소 - Element
	 */
	public void updateRealMapElem(Point pos, Element elem) {
		int r = pos.y;
		int c = pos.x;
		
		if((c >= 0 && c < this.n) && (r >= 0 && r < this.m)){
			realmap[r][c] = elem;
		}
	}
	
	
	/**
	 * RealMap의 특정 좌표 상의 Element 값을 반환 
	 * @param pos 
	 * Element 값 읽을 지도 상의 위치 Point(y, x) 
	 * @return 
	 * 실제 지도 상의 Element 
	 */
	public Element getRealMapElem(Point pos) {
		if((pos.x >= 0 && pos.x < n) && (pos.y >= 0 && pos.y < m))
			return realmap[pos.y][pos.x];
		else 
			return Element.NONE;
	}
}
