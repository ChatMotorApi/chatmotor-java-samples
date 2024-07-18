package com.chatmotorapi.api.test.android.run;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorLargeResponse;
import com.chatmotorapi.api.test.android.util.ChatMotorGetter;
import com.chatmotorapi.api.test.android.util.ParmsAndroid;
import com.chatmotorapi.api.transcription.MotorTranscriptionRequest;
import com.chatmotorapi.api.version.VersionValues;

public class MotorTranscriptionRequestTest {

    public static void main(String[] args) throws Exception {
	test();
    }

    public static void test() throws Exception {

	System.out.println(new Date() + " MotorTranscriptionRequestTest Begin...");
	System.out.println(VersionValues.VERSION + " " + VersionValues.DATE);
	
	String audioFile = ParmsAndroid.AUDIO_FILE_PATH;
	String transcriptionFile = ParmsAndroid.TEXTFILE_PATH;

	String model = "whisper-1";

	// We build a ChatMotor instance.
	ChatMotor chatMotor = ChatMotorGetter.getChatMotor();

	MotorTranscriptionRequest motorTranscriptionRequest = MotorTranscriptionRequest.builder()
		.chatMotor(chatMotor)
		.aiModel(model)
		.filePath(audioFile)
		.build();

	// We execute the transcribe request.
	MotorLargeResponse largeReponse = motorTranscriptionRequest.execute();

	if (largeReponse.isResponseOk()) {
	     try (InputStream in = largeReponse.getInputStream();
		     OutputStream out = new BufferedOutputStream(new FileOutputStream(transcriptionFile))) {
		 IOUtils.copy(in, out);
	     }
	     
	    System.out.println("transcriptionFile: " + transcriptionFile);
	} else {
	     System.out.println("KO!");
             System.out.println("largeReponse.getThrowable()   : " + largeReponse.getThrowable());
             
	}
	
	System.out.println(new Date() + " MotorTranscriptionRequestTest End...");
	
	System.exit(1);
    }

}
