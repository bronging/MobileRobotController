package com.nsg.addon.gui.event;

import com.nsg.addon.gui.design.*;
import com.nsg.addon.voice.manager.VoiceRecMananger;

import javax.swing.*;
import java.awt.event.*;

public class EventManager implements ActionListener{
	
    private boolean isButtonPressed = false;
    
    private VoiceRecMananger voiceRecManager;
    
    public EventManager() {
        // VoiceRecManager �ν��Ͻ� �ʱ�ȭ
        voiceRecManager = new VoiceRecMananger();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button  = (JButton)e.getSource();
        if (isButtonPressed) {
            // ��ư�� �̹� �����ִ� ���
            button.setText("���� ����");
            
            // ���� ���̸� ������ ����
            voiceRecManager.stopRecording();
        } 
        else {
            // ��ư�� ������ ���� ���
            button.setText("���� ��..");
            
            // ������ ����
            voiceRecManager.startRecording();
        }
        
        isButtonPressed = !isButtonPressed;
    }
    
}
