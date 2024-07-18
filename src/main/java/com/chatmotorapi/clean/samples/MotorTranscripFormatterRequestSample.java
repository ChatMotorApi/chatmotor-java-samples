package com.chatmotorapi.clean.samples;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.ChunkSize;
import com.chatmotorapi.api.MotorLargeResponse;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;
import com.chatmotorapi.api.transcription.MotorTranscriptFormatterRequest;


public class MotorTranscripFormatterRequestSample {

    public static void main(String[] args) throws Exception {
	String transcriptionFilePath = SamplesParms.SAMPLE_FILES_DIR + File.separator + "sunalsorises_01_hemingway_64kb.mp3.txt";
	String transcriptionFormattedFilePath = transcriptionFilePath + ".formmatted.txt";
	
	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set.
	ChatMotor chatMotor = ChatMotor.builder().build();

	MotorTranscriptFormatterRequest formatterRequest = MotorTranscriptFormatterRequest.builder()
		.chatMotor(chatMotor)
		.filePath(transcriptionFilePath)
		.inputChunkSize(ChunkSize.ofKilobytes(10))
		.build();

	MotorLargeResponse largeResponse = formatterRequest.execute();

	if (largeResponse.isResponseOk()) {
	    try (InputStream in = largeResponse.getInputStream()) {
		Files.copy(in, Paths.get(transcriptionFormattedFilePath), StandardCopyOption.REPLACE_EXISTING);
	    }
	} else {
	    OpenAiError openAiError = largeResponse.getOpenAiError();
	    OpenAiErrorType errorType = openAiError.getType();

	    if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		System.out.println("OpenAI has returned an error : " + openAiError);

		// Take specific action depending on the error code:
		if (errorType.equals(OpenAiErrorType.SERVER_ERROR)) {
		    // ...
		} else if (errorType.equals(OpenAiErrorType.TOKENS)) {
		    // ..
		} else {
		    // Etc.
		}

	    } else {

	    }
	}
	
	 System.out.println(new Date() + " End.");

    }

}
