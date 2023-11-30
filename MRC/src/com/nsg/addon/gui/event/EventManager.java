package com.nsg.addon.gui.event;

import com.nsg.addon.gui.design.*;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.voice.manager.VoiceRecMananger;

import javax.swing.*;

import java.awt.Point;
import java.awt.event.*;

public class EventManager implements ActionListener{
   
    private boolean isButtonPressed = false;
    
    private VoiceRecMananger voiceRecManager;
    
    public EventManager() {
        // VoiceRecManager 인스턴스 초기화
        voiceRecManager = new VoiceRecMananger();
    }
    
    
    @Override
    /***/
    public void actionPerformed(ActionEvent e) {
        JButton button  = (JButton)e.getSource();
        if (isButtonPressed) {
            // 버튼이 이미 눌려있는 경우
            button.setText("음성 녹음");
            
            // 녹음 중이면 녹음을 종료
            voiceRecManager.stopRecording();
        } 
        else {
            // 버튼이 눌리지 않은 경우
            button.setText("녹음 중..");
            
            // 녹음을 시작
            voiceRecManager.startRecording();
        }
        
        isButtonPressed = !isButtonPressed;
    }
    
    
    /**구조 작업 중 업데이트 정보*/
    public void updateRobotPos(Point p) {
       
    }
    
    
    /**구조 작업 중 업데이트 정보*/
    public void updateRealMap(Point p, Element e) {
       
    }
    
    
    /**구조 작업 중 업데이트 정보*/
    public void updateModelingMap(Point p, Element e) {
       
    }

    
    /**구조 작업 중 업데이트 정보*/
    public void rescueDone(Point p) {
       
    }
    
    
    /**음성인식 정보 업데이트*/
    public void showVoiceRst(Point p, Element e) {
       // extractText() 호출해 말한 결과 띄우기
       
    }
}
