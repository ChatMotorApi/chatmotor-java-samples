package com.chatmotorapi.samples;

import java.util.Date;
import java.util.List;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;
import com.chatmotorapi.api.image.MotorImageContainer;
import com.chatmotorapi.api.image.MotorImageRequest;
import com.chatmotorapi.api.image.MotorImageResponse;
import com.chatmotorapi.api.image.MotorImageSize;
import com.chatmotorapi.api.image.ResponseImageFormat;

public class MotorImageRequestSample {


    public static void main(String[] args)  throws Exception  {
	chatMotorApi();
    }
    
    /**
     * ChatMotor usage
     */
    
    public static void chatMotorApi() {
	
	System.out.println(new Date() + " Begin...");
	
	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	// Create a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder()
		.build();
	
	String prompt = "hyperealtic view of the Eiffel Tower in green under a blue clear sky with sunset orange sun.";

	// Make a request to the ChatMotor.
	MotorImageRequest motorImageRequest = MotorImageRequest.builder()
		.chatMotor(chatMotor)
		.n(1).prompt(prompt)
		.motorImageSize(MotorImageSize.X1024)
		.responseImageFormat(ResponseImageFormat.URL)
		.build();

	// Execute the image request.
	MotorImageResponse motorImageResponse = motorImageRequest.execute();

	if (motorImageResponse.isResponseOk()) {
	    List<MotorImageContainer> motorImageContainers = motorImageResponse.getImageContainer();
	    for (MotorImageContainer motorImageContainer : motorImageContainers) {
		System.out.println(motorImageContainer.getUrl());
	    }
	    System.out.println();
	} else {
	    OpenAiError openAiError = motorImageResponse.getOpenAiError();
	    OpenAiErrorType errorType = openAiError.getType();

	    if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		System.out.println("OpenAI has returned an error : " + openAiError);

		// Take specific action depending on the error code:
		if (errorType.equals(OpenAiErrorType.SERVER_ERROR)) {
		    // ...
		} else if (errorType.equals(OpenAiErrorType.TOKENS)) {
		    // ...
		} else {
		    // Etc.
		}

	    } else {
		System.out.println("throwable: " + motorImageResponse.getThrowable());
	    }
	}

	 System.out.println();
	 System.out.println(new Date() + " End.");
    }
    

}
