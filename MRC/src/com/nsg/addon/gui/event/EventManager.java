package com.nsg.addon.gui.event;

import com.nsg.addon.ADD_ON;
import com.nsg.addon.gui.design.*;
import com.nsg.addon.rescue.control.RobotController;
import com.nsg.addon.rescue.modeling.Direction;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.voice.manager.VoiceRecMananger;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EventManager{
   

	private static Frame frame; 
	
    private static boolean isButtonPressed = false;
    private static boolean isStarted = false;
    
    private static VoiceRecMananger voiceRecManager;
    
    public EventManager() {
        
        voiceRecManager = new VoiceRecMananger();
        
        frame = new Frame();
        frame.setView1();

    }
  
    public static void controlVoiceRec(MouseEvent e) {
    	JLabel l  = (JLabel)e.getSource();
    	if(!isStarted) {
    		frame.view2.start();
    		isStarted = true;
    		l.setText("음성녹음");
    	}
    	
    	else {
    		
            if (isButtonPressed) {
                
                // 녹음 중이면 녹음을 종료
                voiceRecManager.stopRecording();
                
                
                l.setText("음성녹음");
        		ADD_ON.pause = false;

              
            } 
            else {
                // 버튼이 눌리지 않은 경우
                l.setText("녹음 중..");

                ADD_ON.pause = true;
                
                // 녹음을 시작
                voiceRecManager.startRecording();
                
            }
            
            isButtonPressed = !isButtonPressed;
    	}
        
    }
    
    public void rotateRobot(Point p, Direction d) {
    	frame.view2.rotateRobot(p, d);
    }
    public void startView2(String[] inputstr){

    	
    	// 문자열 배열을 Integer 배열로 변환
        Integer[] map_input = extractNumber(inputstr[0]);
        
        ADD_ON.getInstance().createMap(map_input[1]+1, map_input[0]+1);
        frame.setView2(map_input[0]+1, map_input[1]+1);
        frame.view2.setMapLabel(map_input[0], map_input[1]);
        

        //로봇 초기 위치 설정
    	Integer[] robot_input = extractNumber(inputstr[1]);   //(1 2) -> [1,2]    	
    	ADD_ON.getInstance().initRobot(new Point(robot_input[0], robot_input[1]));
    	
    	
    	//탐색 지점 설정 
    	Integer[] spotinput = extractNumber(inputstr[2]);    // ((45 1)(4 6)) -> [45, 1, 4, 6]
    	Point[] pos = new Point[spotinput.length / 2];
    	for(int i = 0; i < spotinput.length; i+=2) {
    		pos[i/2] = new Point(spotinput[i], spotinput[i+1]);
    		ADD_ON.getInstance().updateSearchPos(pos[i/2]);
    		
    	}
    	
   
    	frame.view2.setSpotLabel(pos);
    	
    	// 중요 지점 설정
    	Integer[] colorinput = extractNumber(inputstr[3]);
    	pos = new Point[colorinput.length / 2];
    	for(int i = 0; i < colorinput.length; i+=2) {
    		pos[i/2] = new Point(colorinput[i], colorinput[i+1]);
    		ADD_ON.getInstance().updateRealMap(pos[i/2], Element.COLORBLOB);
    	}
    	
    	// 위험 지점 설정
    	Integer[] hazardinput = extractNumber(inputstr[4]);
    	pos = new Point[hazardinput.length / 2];
    	for(int i = 0; i < hazardinput.length; i+=2) {
    		pos[i/2] = new Point(hazardinput[i], hazardinput[i+1]);
    		ADD_ON.getInstance().updateRealMap(pos[i/2], Element.HAZARD);
    	}
    	
    		
    }
    
   
    public Integer[] extractNumber(String input) {
        // 괄호와 공백을 제거
        String cleanedInput = input.replaceAll("[()]", "");

        // 쉼표를 기준으로 숫자 분리
        String[] numberStrings = cleanedInput.split("\\s+");

        // 문자열 배열을 Integer 배열로 변환
        List<Integer> numbersList = new ArrayList<>();
        for (String numberString : numberStrings) {
            try {
                // 문자열을 숫자로 변환
                int number = Integer.parseInt(numberString);
                numbersList.add(number);
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없는 문자열이라면 무시
            }
        }

        // Integer 리스트를 Integer 배열로 변환
        Integer[] numbers = new Integer[numbersList.size()];
        return numbersList.toArray(numbers);
    }
    
    /**로봇 위치 업데이트 정보*/
    public static void updateRobotPos(Point curr) {
    	System.out.println("robot update");
    	//System.out.println(prev + "and " + curr);
        frame.view2.robotUpdate(curr);
        frame.repaint();
 
    }
    
    
    /**리얼맵 업데이트 정보*/
    public void updateRealMap(Point p, Element e) {
    	System.out.println("real map update");
    	System.out.println(p + "and " + e);
       frame.view2.initElem(p, e);
       frame.repaint();
    }
    
    
    /**모델링맵  업데이트 정보*/
    public void updateModelingMap(Point p, Element e) {
    	System.out.println("modeling map update");
    	System.out.println(p + "and " + e);
        frame.view2.colorElem(p, e);
        frame.repaint();
    }

    
    /**구조 완료 업데이트 정보 */
    public static void rescueDone(Point p) {
    	System.out.println(p);
        frame.view2.rescueUpdate(p);
        frame.repaint();
    }
    
    
    /**음성인식 정보 업데이트*/
    public static void showVoiceRst(Point p, Element e) {
       // extractText() 호출해 말한 결과 띄우기
    	
    	String s = "";
    	if( p != null ) {
    		if(e == Element.COLORBLOB) 
        		s += "중요지점 ";
        	else if(e == Element.HAZARD)
        		s += "위험지점";
        	else if(e == Element.SEARCHPOINT)
        		s += "탐색지점";
        	
        	s += "(" + p.x + ", " + p.y + ") 추가";
        	
        	frame.view2.setVoiceInfo(s);
    	}
    }

	
}
