package com.nsg.addon.voice.manager;

import javax.sound.sampled.*;
import java.io.*;
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


    VoiceRecEngine voiceRecEngine = new VoiceRecEngine();

    // 생성자 수정
    public VoiceRecMananger() {
        this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000, 16, 1, 2, 16000, false);

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

                saveToFile(out.toByteArray(), "C:\\\\Users\\\\parkn\\\\git\\\\MobileRobotController\\\\MRC\\\\src\\\\com\\\\nsg\\\\addon\\\\voice\\\\temp\\\\recordedAudio__.wav");

                System.out.println("녹음 파일이 저장되었습니다.");
            } else {
                System.out.println("녹음 중이 아닙니다.");
            }
        }
    }

    private void saveToFile(byte[] audioData, String outputFilePath) {
        try {
            // 파일로 저장
            File outputFile = new File(outputFilePath);
            AudioFormat audioFormat = line.getFormat();
            AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(audioData), audioFormat, audioData.length);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);

            // 자원 해제
            audioInputStream.close();
            
            voiceRecEngine.extractText(outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}