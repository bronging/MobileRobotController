package com.nsg.addon.gui.event;

import com.nsg.addon.gui.design.*;
import com.nsg.addon.voice.manager.VoiceRecMananger;

import javax.swing.*;
import java.awt.event.*;

public class EventManager implements ActionListener{
	
    private boolean isButtonPressed = false;
    
    private VoiceRecMananger voiceRecManager;
    
    public EventManager() {
        // VoiceRecManager 인스턴스 초기화
        voiceRecManager = new VoiceRecMananger();
    }
    
    
    @Override
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
    
}
