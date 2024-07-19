package com.chatmotorapi.samples;

import java.util.ArrayList;
import java.util.List;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorMessage;
import com.chatmotorapi.api.MotorRequest;
import com.chatmotorapi.api.MotorResponse;
import com.chatmotorapi.api.MotorSystemMessage;
import com.chatmotorapi.api.MotorUserMessage;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;

public class MotorRequestSample {

    public static void main(String[] args) throws Exception {

	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	// Create a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder().build();

	String system = "You are an expert in programming.";
	String user = "Write a simple technical article about Java language";

	List<MotorMessage> motorMessages = new ArrayList<MotorMessage>();
	motorMessages.add(new MotorSystemMessage(system));
	motorMessages.add(new MotorUserMessage(user));

	// Make a request to the ChatMotor.
	MotorRequest motorRequest = MotorRequest.builder().chatMotor(chatMotor).messages(motorMessages).build();

	// Execute the request.
	MotorResponse motorResponse = motorRequest.execute();

	// We check if the response is ok.
	if (motorResponse.isResponseOk()) {
	    String response = motorResponse.getResponse();
	    System.out.println(response);
	} else {
	    OpenAiError openAiError = motorResponse.getOpenAiError();
	    OpenAiErrorType errorType = openAiError.getType();

	    if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		System.out.println("OpenAI has returned an error : " 
			+ openAiError);

		// Take specific action depending on the error code:
		if (errorType.equals(OpenAiErrorType.SERVER_ERROR)) {
		    // ...
		} else if (errorType.equals(OpenAiErrorType.TOKENS)) {
		    // ...
		} else {
		    // Etc.
		}

	    } else {
		System.out.println("throwable: " + motorResponse.getThrowable());
	    }
	}

    }

}
