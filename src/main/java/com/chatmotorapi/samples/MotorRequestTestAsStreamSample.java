package com.chatmotorapi.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorAiOptions;
import com.chatmotorapi.api.MotorMessage;
import com.chatmotorapi.api.MotorRequest;
import com.chatmotorapi.api.MotorStreamStatus;
import com.chatmotorapi.api.MotorSystemMessage;
import com.chatmotorapi.api.MotorUserMessage;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;
import com.chatmotorapi.api.listener.ConsoleResponseListener;
import com.chatmotorapi.api.listener.MotorResponseListener;

public class MotorRequestTestAsStreamSample {

    public static void main(String[] args)  throws Exception  {
	System.out.println(new Date() + " ChatMotorApiInChunks Begin...");
	 
	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	// Create a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder().build();
	
	 // We optionally build a MotorAiOptions instance.
	 MotorAiOptions options = MotorAiOptions.builder()
		    .temperature(0.0)    // Controls the randomness of the AI responses
		    .maxTokens(1500)      // Defines the maximum number of tokens in each response
		    .build();
	 
	 List<MotorMessage> motorMessages = new ArrayList<MotorMessage>();
	 motorMessages.add(new MotorSystemMessage("You are an expert in AI."));
	 motorMessages.add(new MotorUserMessage("Write a technical article about ChatGPT, no more than 1500 words."));

	 // We make a request to the ChatMotor.
	 MotorRequest motorRequest = MotorRequest.builder()
		.chatMotor(chatMotor)
		//.aiModel("gpt-4")
		.motorAiOptions(options)
		.messages(motorMessages)
		.build();
	 
         // We execute the request.
	 MotorResponseListener consoleResponseListener = new ConsoleResponseListener();
	 
	 // Get error details if any.
	 MotorStreamStatus motorStreamStatus = motorRequest.executeAsStream(consoleResponseListener);
	 
	 // Check if the response is ok.
	 if (motorStreamStatus.isResponseOk()) {
	     // Nothing to do here. Display is done by the listener.
	 } else {
	     // If the response is not ok, we get the error message and the throwable.
	     OpenAiError openAiError = motorStreamStatus.getOpenAiError();
	     OpenAiErrorType errorType = openAiError.getType();

	     if (errorType != OpenAiErrorType.NO_OPENAI_ERROR) {
		 System.out.println("OpenAI has returned an error : " + openAiError);
	     } else {
		 System.out.println("throwable   : " + motorStreamStatus.getThrowable());
	     }
	 }
	 
	 System.out.println();
	 System.out.println(new Date() + " ChatMotorApiInChunks End.");
    }


    
    
  
 

}
