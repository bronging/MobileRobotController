package com.nsg.addon.voice.manager;

import javax.sound.sampled.*;

import java.awt.Point;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.nsg.addon.ADD_ON;
import com.nsg.addon.rescue.modeling.Element;
import com.nsg.addon.voice.engine.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import com.nsg.addon.voice.engine.*;

public class VoiceRecMananger {
   
    private AudioFormat format;
    private TargetDataLine line;
    private ByteArrayOutputStream out;
    private boolean isRecording = false;
    private Thread recordingThread;
    private String result;


    VoiceRecEngine voiceRecEngine = new VoiceRecEngine();

    // 생성자 수정
    public VoiceRecMananger() {
        this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000, 16, 1, 2, 16000, false);

    }
    
    public String getResult() {
    	return result;
    }
    
    /** 녹음 시작 메서드 */
    public void startRecording() {
        if (!isRecording) {
            isRecording = true;
            recordingThread = new Thread(() -> {
                try {
                    line = AudioSystem.getTargetDataLine(format);
                    if (line == null) {
                        System.err.println("타겟 데이터 라인을 사용할 수 없습니다.");
                        return;
                    }
                    line.open(format);
                    line.start();

                    System.out.println("녹음을 시작합니다.");

                    out = new ByteArrayOutputStream();

                    byte[] buffer = new byte[line.getBufferSize() / 5];

                    while (isRecording) {
                        int bytesRead = line.read(buffer, 0, buffer.length);
                        out.write(buffer, 0, bytesRead);
                        System.out.println("녹음중!");
                    }
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            });

            recordingThread.start();
        }
    }

    /** 녹음 중지 메서드 */
    public void stopRecording() {
        if (isRecording) {
            isRecording = false;

            if (line != null && line.isOpen()) {
                System.out.println("녹음을 중지합니다.");

                // 자원 해제
                line.stop();
                line.close();

                // 스레드 종료
                recordingThread.interrupt();
                
                result = saveToFile(out.toByteArray(), "recordedAudio__.wav");
                extractText(result);

                System.out.println("녹음 파일이 저장되었습니다.");
                
            } else {
                System.out.println("녹음 중이 아닙니다.");
            }
        }
    }

    /** 녹음 저장 메서드*/
    private String saveToFile(byte[] audioData, String outputFilePath) {
        try {
            // 파일로 저장
            File outputFile = new File(outputFilePath);
            AudioFormat audioFormat = line.getFormat();
            AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(audioData), audioFormat, audioData.length);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);

            // 자원 해제
            audioInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return voiceRecEngine.getVoiceRecRst(outputFilePath);

    }
    
    /**음성인식 결과 추출*/
    public void extractText(String s) {
       
       String extractText;
       
       //인식된 결과만 추출
       extractText = s.substring(98, s.length() - 3);
       
       //공백 없애기
        extractText.replaceAll("\\s", "");
        
        int pointX, pointY;
        Element e;
        char pointx = s.charAt(4);
        char pointy = s.charAt(7);
        
        pointX = charToInt(pointx);
        pointY = charToInt(pointy);
        
        if(extractText.substring(0,4)=="중요지점") {
           e = Element.COLORBLOB;
        }
        else if(extractText.substring(0,4)=="위험지점") {
           e = Element.HAZARD;
        }
        else {
           //잘못 인식
           System.out.println("다시 녹음하십시오");
        }
        
        Point p;
        //p.x = pointX;
        //p.y = pointY;
        
        //ADD_ON.getInstance().updateRealMap(p,e);
        //ADD_ON.getInstance().VoiceRst(p,e);
        // addon의 updateRealmap호출
        // addon VoiceRst 호출
        p = new Point(3, 2);
        //ADD_ON.getInstance().updateRealMap(p,e);
        //ADD_ON.getInstance().VoiceRst(p,e);
        
    }
    
    
    /**음성녹음 한 글자를 숫자로 변경*/
    private int charToInt(char pointx) {
       
       int pointX;
       
        switch(pointx) {
        case '영': pointX = 0;
           break;
        case '일': pointX = 1;
           break;
        case '이': pointX = 2;
           break;
        case '삼': pointX = 3;
           break;
        case '사': pointX = 4;
           break;
        case '오': pointX = 5;
           break;
        case '육': pointX = 6;
           break;
        case '칠': pointX = 7;
          break;
       case '팔': pointX = 8;
          break;
       case '구': pointX = 9;
          break;
       default:
          System.out.println("다시 녹음하십시오.");
            return -1;
        }
        return pointX;
    }

}