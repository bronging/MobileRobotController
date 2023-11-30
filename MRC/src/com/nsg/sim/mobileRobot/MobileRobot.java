package com.nsg.sim.mobileRobot;

import java.awt.Point;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.sim.Movement;
import com.nsg.sim.Rotate;
import com.nsg.addon.rescue.modeling.RealMap;

public class MobileRobot implements RobotSimulInterface{
	
	private Point position;
	private Direction direction;
	
	public MobileRobot() {

	}
	
	@Override
	public void init(Point pos) {
		this.position = pos;
		this.direction = Direction.EAST;
	}
	
	@Override
	public int hazardSensor() {
		Point forward = getForwardPos();
		
		Element e = RealMap.getInstance().getRealMapElem(forward);
		
		if(e == Element.HAZARD)
			return 1;
		else 
			return 0;
	}

	@Override
	public int colorBlobSensor() {
		int rst = 0; 
		Element[] e = new Element[4];
		
		// 전후좌우에 해당하는 좌표의 값을 읽어옴 
		e[3] = RealMap.getInstance().getRealMapElem(getForwardPos());
		e[2] = RealMap.getInstance().getRealMapElem(getBackwardPos());
		e[1] = RealMap.getInstance().getRealMapElem(getLeftPos());
		e[0] = RealMap.getInstance().getRealMapElem(getRightPos());
		
		for(Element elem: e) {
			if(elem == Element.COLORBLOB)
				rst = (rst<<1) | 1; 
			else 
				rst = rst<<1; 
		}
		return rst;
	}

	@Override
	public void move(Movement move) {
		if(move == Movement.FORWARD) {
			
			/**TODO 랜덤한 확률로 로봇 잘못 이동하는 케이스 구현  
			 */
			int r = (int)(Math.random() * 10); 

			if(r > 1) 
				this.position= getForwardPos();
			
			// 10번에 1번씩 2칸 이동
			else if(r == 0) {
				this.position = getForwardPos();
				// 1칸 더 움직일 시, 지도 밖으로 나가면 더 움직이지 않게 함 
				if(RealMap.getInstance().isInMap(getForwardPos()))
					this.position = getForwardPos();	
			}
			// 10번에 1번씩 움직이지 않음 (r == 1) 
		}
	}

	@Override
	public void rotate(Rotate rtt) {
		if(rtt == Rotate.CLOCK_90_ROTATION) {
			this.direction = this.direction.next(); 
		}
	}

	@Override
	public Point posSensor() {
		return this.position;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 전방 1칸 앞의 좌표 
	 */
	public Point getForwardPos() {
		int c = position.x;
		int r = position.y;
		
		if(this.direction == Direction.EAST)
			c += 1;
		else if(this.direction == Direction.WEST)
			c -= 1;
		else if(this.direction == Direction.NORTH)
			r -= 1;
		else if(this.direction == Direction.SOUTH)
			r += 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 후방 1칸 의 좌표 
	 */
	public Point getBackwardPos() {
		int c = position.x;
		int r = position.y;
		
		if(this.direction == Direction.EAST)
			c -= 1;
		else if(this.direction == Direction.WEST)
			c += 1;
		else if(this.direction == Direction.NORTH)
			r += 1;
		else if(this.direction == Direction.SOUTH)
			r -= 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 좌측 1칸 옆의 좌표 
	 */
	public Point getLeftPos() {
		int c = position.x;
		int r = position.y;
		
		if(this.direction == Direction.EAST)
			r -= 1;
		else if(this.direction == Direction.WEST)
			r += 1;
		else if(this.direction == Direction.NORTH)
			c -= 1;
		else if(this.direction == Direction.SOUTH)
			c += 1;
		
		return new Point(c, r);
	}
	
	/**
	 * 
	 * @return 로봇의 현재 방향에 따른 우측 1칸 옆의 좌표 
	 */
	public Point getRightPos() {
		int c = position.x;
		int r = position.y;
		
		if(this.direction == Direction.EAST)
			r += 1;
		else if(this.direction == Direction.WEST)
			r -= 1;
		else if(this.direction == Direction.NORTH)
			c += 1;
		else if(this.direction == Direction.SOUTH)
			c -= 1;
		
		return new Point(c, r);
	}
}
