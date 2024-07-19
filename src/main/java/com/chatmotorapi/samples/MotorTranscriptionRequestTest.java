package com.chatmotorapi.samples;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorLargeResponse;
import com.chatmotorapi.api.transcription.MotorTranscriptionRequest;

public class MotorTranscriptionRequestTest {

    public static void main(String[] args) throws Exception {

	String audioFilePath = SamplesParms.SAMPLE_FILES_DIR +  File.separator + "sunalsorises_01_hemingway_64kb.mp3";
	String transcriptionFilePath = audioFilePath + ".txt";
		
	System.out.println(new Date() + " Begin...");
	//String model = "whisper-1";

	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	// Create a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder().build();

	// Build the transcription request.
	MotorTranscriptionRequest transcriptionRequest = MotorTranscriptionRequest.builder()
		.chatMotor(chatMotor)
		.filePath(audioFilePath)
		.build();

	// Execute the transcribe request.
	MotorLargeResponse largeReponse = transcriptionRequest.execute();

	if (largeReponse.isResponseOk()) {
	    try (InputStream in = largeReponse.getInputStream()) {
		Files.copy(in, Paths.get(transcriptionFilePath), StandardCopyOption.REPLACE_EXISTING);
	    }
	 
	    System.out.println("transcriptionFilePath:");
	    System.out.println(transcriptionFilePath);
	} else {
             System.out.println("throwable   : " + largeReponse.getThrowable());
	}
	
	System.out.println(new Date() + " End.");
    }


}
