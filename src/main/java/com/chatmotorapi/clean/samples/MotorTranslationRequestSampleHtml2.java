package com.chatmotorapi.clean.samples;

import java.io.File;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorAiOptions;
import com.chatmotorapi.api.MotorLargeResponse;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;
import com.chatmotorapi.api.functional.MotorLargeTranslationRequest;

public class MotorTranslationRequestSampleHtml2 {

    public static void main(String[] args) throws Exception {
	System.out.println(new Date() + " Begin...");

	String filePath = SamplesParms.SAMPLE_FILES_DIR  + File.separator + "Share-Alike-and-ML-Report-FINAL-extract.html";
	String responsePath = filePath + ".translated.streaming.html";
	// String organisationId = "KawanSoft";

	HttpClient httpsClient = HttpClient.newHttpClient();

	// Build a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder()
		// .openAiKey(key)
		.httpClient(httpsClient)
		// .organizationId(organisationId)
		.build();

	// Optionally build a MotorAiOptions instance.
	MotorAiOptions options = MotorAiOptions.builder().temperature(0.0) // Controls the randomness of the AI
									   // responses
		.build();

	// Build a translation request to the ChatMotor.
	MotorLargeTranslationRequest translationRequest = MotorLargeTranslationRequest.builder()
		.aiModel("gpt-4")
		.chatMotor(chatMotor)
		.motorAiOptions(options)
		.filePath(filePath)
		.languageCode("fr")
		.useInputStreaming(true) // Enable streaming to reduce memory usage for large files.
		.build();

	// Execute the translation request (see code above)
	MotorLargeResponse response = translationRequest.execute();

	if (response.isResponseOk()) {
	     try (InputStream in = response.getInputStream();) {
		 Files.copy(in, Paths.get(responsePath), StandardCopyOption.REPLACE_EXISTING);
	     }
	} else {

	    OpenAiError openAiError = response.getOpenAiError();
	    OpenAiErrorType errorType = openAiError.getType();

	    if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		System.out.println("OpenAI has returned an error : " 
			+ openAiError);

		// Take specific action depending on the error code:
		if (errorType.equals(OpenAiErrorType.SERVER_ERROR)) {
		    // ...
		} else if (errorType.equals(OpenAiErrorType.TOKENS)) {
		    // ..
		} else {
		    // Etc.
		}

	    } else {
		System.out.println("throwable   : " 
			+ response.getThrowable());
	    }
	}

	System.out.println(new Date() + " End");
    }

}
