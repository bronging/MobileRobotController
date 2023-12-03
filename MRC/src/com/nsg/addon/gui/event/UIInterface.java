package com.nsg.addon.gui.event;

import java.awt.Point;

import com.nsg.addon.rescue.modeling.Element;

public interface UIInterface {

	/**리얼맵 업데이트 정보*/
    public void updateRealMap(Point p, Element e);
    
    /**모델링맵  업데이트 정보*/
    public void updateModelingMap(Point p, Element e);
	
    /**구조 완료 업데이트 정보 */
    public void rescueDone(Point p);
    
	/**로봇 위치 업데이트 정보*/
    public void updateRobotPos(Point curr);
    
    /**음성인식 정보 업데이트*/
    public void showVoiceRst(Point p, Element e);
}
