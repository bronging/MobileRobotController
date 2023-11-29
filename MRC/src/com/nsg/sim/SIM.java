package com.nsg.sim;

import java.awt.Point;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.sim.mobileRobot.*;
import com.nsg.addon.rescue.modeling.Map;

public class SIM implements RobotMovInterface{
	
	private MobileRobot robot;  
	
	public SIM() {
		robot = new MobileRobot();
	}
	
	/**
	 * 
	 * @param rb_pos
	 * 초기 로봇의 위치
	 */
	public void init(Point rb_pos) {
		robot.init(rb_pos);
	}
	
	@Override
	public void stopRobot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isHazard() {
		return robot.hazardSensor();
	}

	@Override
	public int isColorBlob() {
		return robot.colorBlobSensor();
	}

	@Override
	public Point move(Movement m) {
		robot.move(m);
		return robot.posSensor();
	}

	@Override
	public Direction rotate(Rotate r) {
		robot.rotate(r);
		return robot.getDirection();
	}
	
	@Override
	public Point robotPosition() {
		return robot.posSensor();
	}
	
	@Override
	public Direction robotDirection() {
		return robot.getDirection();
	}
}
