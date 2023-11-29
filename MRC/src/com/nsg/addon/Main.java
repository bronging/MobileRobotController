package com.nsg.addon;

import java.awt.Point;

import com.nsg.addon.rescue.modeling.Element;

public class Main {

	public static void main(String[] args) {
		
		ADD_ON.getInstance().createMap(12, 15);
		
		ADD_ON.getInstance().updateSearchPos(new Point(13,2));
		ADD_ON.getInstance().updateSearchPos(new Point(3,3));
		
		ADD_ON.getInstance().updateRealMap(new Point(1,1), Element.HAZARD);
		ADD_ON.getInstance().updateRealMap(new Point(6,5), Element.HAZARD);
		ADD_ON.getInstance().updateRealMap(new Point(4,10), Element.COLORBLOB);
		ADD_ON.getInstance().updateRealMap(new Point(5,6), Element.COLORBLOB);
		
		
		ADD_ON.getInstance().printRealMap();
				
		ADD_ON.getInstance().initRobot(new Point(6,6));
		
		System.out.println();
		
		ADD_ON.getInstance().printMap();
		
		ADD_ON.getInstance().rescue();
		
		return; 
	}

}
