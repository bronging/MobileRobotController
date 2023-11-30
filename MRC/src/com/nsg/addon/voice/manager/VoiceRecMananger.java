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

    // ������ ����
    public VoiceRecMananger() {
        this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000, 16, 1, 2, 16000, false);

    }
    /** ���� ���� �޼��� */
    public void startRecording() {
        if (!isRecording) {
            isRecording = true;
            recordingThread = new Thread(() -> {
                try {
                    line = AudioSystem.getTargetDataLine(format);
                    if (line == null) {
                        System.err.println("Ÿ�� ������ ������ ����� �� �����ϴ�.");
                        return;
                    }
                    line.open(format);
                    line.start();

                    System.out.println("������ �����մϴ�.");

                    out = new ByteArrayOutputStream();

                    byte[] buffer = new byte[line.getBufferSize() / 5];

                    while (isRecording) {
                        int bytesRead = line.read(buffer, 0, buffer.length);
                        out.write(buffer, 0, bytesRead);
                        System.out.println("������!");
                    }
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            });

            recordingThread.start();
        }
    }

    /** ���� ���� �޼��� */
    public void stopRecording() {
        if (isRecording) {
            isRecording = false;

            if (line != null && line.isOpen()) {
                System.out.println("������ �����մϴ�.");

                // �ڿ� ����
                line.stop();
                line.close();

                // ������ ����
                recordingThread.interrupt();

                saveToFile(out.toByteArray(), "C:\\\\Users\\\\parkn\\\\git\\\\MobileRobotController\\\\MRC\\\\src\\\\com\\\\nsg\\\\addon\\\\voice\\\\temp\\\\recordedAudio__.wav");

                System.out.println("���� ������ ����Ǿ����ϴ�.");
            } else {
                System.out.println("���� ���� �ƴմϴ�.");
            }
        }
    }

    private void saveToFile(byte[] audioData, String outputFilePath) {
        try {
            // ���Ϸ� ����
            File outputFile = new File(outputFilePath);
            AudioFormat audioFormat = line.getFormat();
            AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(audioData), audioFormat, audioData.length);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);

            // �ڿ� ����
            audioInputStream.close();
            
            voiceRecEngine.extractText(outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}