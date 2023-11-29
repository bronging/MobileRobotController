package com.nsg.sim;

import java.awt.Point;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.sim.Rotate;
import com.nsg.sim.Movement;

public interface RobotMovInterface {
	
	/**
	 * 로봇 움직임 중단
	 */
	void stopRobot();
	
	/**
	 * 로봇 전방 1칸 위험 지점 여부 
	 * @return 1: 위험 지점, 0: 위험 지점 아님 
	 */
	int isHazard();
	
	/**
	 * 로봇 전후좌우 1칸 중요 지점 여부
	 * @return 4bit 순서대로 전후좌우. 
	 * 1: 중요 지점, 0: 중요 지점 아님 
	 */
	int isColorBlob(); 
	
	/**
	 * 로봇에게 전진 명령 등의 이동 명령 
	 * @param m
	 * 	로봇에 명령 내릴 동작 종류
	 * @return  
	 * 	동작 이후 로봇의 위치 
	 * 	
	 */
	Point move(Movement m);
	
	
	/**
	 * 로봇에게 회전 명령
	 * @param r
	 * 로봇에 명령 내릴 회전 종류
	 * @return
	 * 동작 이후 로봇의 방향
	 */
	Direction rotate(Rotate r);
	
	/**
	 * 
	 * @return 로봇의 현재 위치 
	 */
	Point robotPosition();
	
	/**
	 * 
	 * @return 로봇의 현재 방향
	 */
	Direction robotDirection();
}
