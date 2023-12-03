package com.nsg.addon.voice.manager;

public interface VoiceRecognitionInterface {
	
    public void init();
	
	/** 녹음 시작 메서드 */
    public void startRecording();
	
	/** 녹음 중지 메서드 */
    public void stopRecording();
}
