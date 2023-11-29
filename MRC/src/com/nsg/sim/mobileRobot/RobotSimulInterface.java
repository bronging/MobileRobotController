package com.nsg.sim.mobileRobot;

import java.awt.Point;
import com.nsg.sim.Movement;
import com.nsg.sim.Rotate;
import com.nsg.addon.rescue.modeling.Direction;

public interface RobotSimulInterface {
	
	/**
	 * 로봇의 초기 위치를 설정한다. 
	 * @param pos
	 * 초기 위치 
	 */
	void init(Point pos);
	
	/**
	 *로봇의 Hazard Sensor 값을 읽는다. 
	 * @return 전방 1칸 앞의 hazard 여부 
	 */
	int hazardSensor();
	
	/**
	 * 로봇의 Color blob Sensor 값을 읽는다.  
	 * @return 전후좌우 color blob 여부 
	 */
	int colorBlobSensor();
	
	/**
	 * 로봇을 위치를 이동시킨다.
	 * @param move
	 * 제어할 로봇의 동작 
	 */
	void move(Movement move);
	
	/**
	 * 로봇을 회전 시킨다. 
	 * @param rtt
	 * 제어할 로봇의 회전 종류
	 */
	void rotate(Rotate rtt);
	
	/**
	 * 로봇의 Positioning Sensor 값을 읽는다. 
	 * @return 로봇의 현재 위치
	 */
	Point posSensor();
	
	/**
	 * 로봇의 현재 방향 정보를 얻는다.
	 * @return 로봇이 바라보는 방향
	 */
	Direction getDirection();
}
