package com.chatmotorapi.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorMessage;
import com.chatmotorapi.api.MotorRequest;
import com.chatmotorapi.api.MotorStreamStatus;
import com.chatmotorapi.api.MotorSystemMessage;
import com.chatmotorapi.api.MotorUserMessage;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;
import com.chatmotorapi.api.listener.ConsoleResponseListener;
import com.chatmotorapi.api.listener.MotorResponseListener;

public class MotorRequestStreamingSample {

    public static void main(String[] args) throws Exception {
	
	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	ChatMotor chatMotor = ChatMotor.builder()
		       .build();
	 	
	 String system = "You are an expert in programming.";
	 String user = "Write a simple technical article about Java language";
	 
	 List<MotorMessage> motorMessages = new ArrayList<MotorMessage>();
	 motorMessages.add(new MotorSystemMessage(system));
	 motorMessages.add(new MotorUserMessage(user));

	 // Mmake a request to the ChatMotor.
	 MotorRequest motorRequest = MotorRequest.builder()
		.chatMotor(chatMotor)
		.messages(motorMessages)
		.build();
	 
         // Execute the request and display immediately the response
	 // in the console.
	 // This uses a dedicated listener 
	 MotorResponseListener listener = new ConsoleResponseListener();
	 MotorStreamStatus status = motorRequest.executeAsStream(listener);
	 
	 // Check if the response is ok.
	 if (status.isResponseOk()) {
	     // Nothing to do here. Display is done by the listener.
	 } else {
	     // If the response is not ok, we get the error message and the throwable.
	     OpenAiError openAiError = status.getOpenAiError();
	     OpenAiErrorType errorType = openAiError.getType();

	     if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		 System.out.println("OpenAI has returned an error : " + openAiError);
	     } else {
		 System.out.println("throwable   : " + status.getThrowable());
	     }
	 }
		
	 System.out.println(new Date() + " End.");
    }

}
