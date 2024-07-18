package com.chatmotorapi.api.test.android.run;

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
import com.chatmotorapi.api.listener.ConsoleResponseListener;
import com.chatmotorapi.api.listener.MotorResponseListener;
import com.chatmotorapi.api.test.android.util.ChatMotorGetter;
import com.chatmotorapi.api.version.VersionValues;

public class MotorRequestTestAsStream {

    public static void main(String[] args)  throws Exception  {
	test();
    }

    
    public static void test() {
	
	System.out.println(new Date() + " MotorRequestTestAsStream Begin...");
	System.out.println(VersionValues.VERSION + " " + VersionValues.DATE);
	
	//String organisationId = "KawanSoft";
	 
	ChatMotor chatMotor = ChatMotorGetter.getChatMotor();
	
	 // We optionally build a MotorAiOptions instance.
	 MotorAiOptions options = MotorAiOptions.builder()
		    .temperature(0.0)    // Controls the randomness of the AI responses
		    .maxTokens(150)      // Defines the maximum number of tokens in each response
		    .build();
	 
	 List<MotorMessage> motorMessages = new ArrayList<MotorMessage>();
	 motorMessages.add(new MotorSystemMessage("You are an expert in AI."));
	 motorMessages.add(new MotorUserMessage("Write a technical article about ChatGPT, no more than 100 words."));

	 // We make a request to the ChatMotor.
	 MotorRequest motorRequest = MotorRequest.builder()
		.chatMotor(chatMotor)
		.aiModel("gpt-4") // The AI model to use. FOR ANDROID.
		.motorAiOptions(options)
		.messages(motorMessages)
		.build();
	 
         // We execute the request.
	 MotorResponseListener consoleResponseListener = new ConsoleResponseListener();
	 MotorStreamStatus motorStreamStatus = motorRequest.executeAsStream(consoleResponseListener);
	
	 if (motorStreamStatus.isResponseOk()) {
	     System.out.println("OK!");
	 }
	 else {
	     System.out.println("KO!");
             System.out.println("motorStreamStatus.getThrowable()   : " + motorStreamStatus.getThrowable());
             
	 }
	 
	 System.out.println();
	 System.out.println(new Date() + " MotorRequestTestAsStream End.");
	 System.exit(1);
    }
    
    
  
 

}
