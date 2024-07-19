package com.chatmotorapi.samples;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorLargeRequestText;
import com.chatmotorapi.api.MotorLargeResponse;
import com.chatmotorapi.api.MotorSystemMessage;
import com.chatmotorapi.api.OpenAiError;
import com.chatmotorapi.api.OpenAiErrorType;

public class MotorLargeRequestTextSample {

    public static String CR_LF = System.getProperty("line.separator");

    public static void main(String[] args) throws Exception {
	System.out.println(new Date() + " Begin...");

	String filePath = SamplesParms.SAMPLE_FILES_DIR + File.separator  + "proust.txt";
	String responseFile = filePath + ".response.txt";
	
	// Create a ChatMotor instance.
	// We assume that env var MOTOR_LICENSE_FILE_PATH and MOTOR_API_KEY are set
	ChatMotor chatMotor = ChatMotor.builder().build();

	String systemPrompt = "I will give you a French extract from 'A la Recherche du Temps Perdu.' "
		+ "Please explain in English who the characters are, what they are doing, "
		+ "and their goals if you can perceive them."
		+ "Please add a blank line, then this line: "
		+ "--------------------------------------------------------------------------------------------"
		+ ", and then a second blank line at the end of your response. "
		+ "Do not include the extraction from the book.";
		
	MotorSystemMessage motorSystemMessage = new MotorSystemMessage(systemPrompt);
	
	// Make a request to the ChatMotor.
	MotorLargeRequestText request = MotorLargeRequestText.builder()
		.chatMotor(chatMotor)
		.systemMessage(motorSystemMessage)
		.filePath(filePath)
		.build();

	// Execute the request.
	MotorLargeResponse largeResponse = request.execute();

	if (largeResponse.isResponseOk()) {
	     try (InputStream in = largeResponse.getInputStream();) {
		 Files.copy(in, Paths.get(responseFile), StandardCopyOption.REPLACE_EXISTING);
	     }
	} else {
	    
	    OpenAiError openAiError = largeResponse.getOpenAiError();
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
			+ largeResponse.getThrowable());
	    }
	}

	System.out.println();
	System.out.println(new Date() + " End.");
	System.exit(0);
    }

}
