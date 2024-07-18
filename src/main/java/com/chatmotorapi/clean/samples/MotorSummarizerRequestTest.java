package com.chatmotorapi.clean.samples;

import java.io.File;
import java.net.http.HttpClient;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.api.MotorAiOptions;
import com.chatmotorapi.api.MotorResponse;
import com.chatmotorapi.api.functional.MotorStrategicSummaryRequest;

public class MotorSummarizerRequestTest {


    public static void main(String[] args)  throws Exception  {
	System.out.println(new Date() + " Begin...");
	
	String filePath = SamplesParms.SAMPLE_FILES_DIR +  File.separator+ "proust.txt";
	File responseFile =  new File(filePath + ".summary.html");
	
	//String organisationId = "KawanSoft";
	
	HttpClient httpsClient = HttpClient.newHttpClient();
	
	// We build a ChatMotor instance.
	ChatMotor chatMotor = ChatMotor.builder()
		    //.openAiKey(key)
		    .httpClient(httpsClient)
		    //.organizationId(organisationId)
		    .build();
	
	 // We optionally build a MotorAiOptions instance.
	 MotorAiOptions options = MotorAiOptions.builder()
		    .temperature(0.0)    // Controls the randomness of the AI responses
		    .build();
	 
	 // We make a request to the ChatMotor.
	 MotorStrategicSummaryRequest summarizerRequest = MotorStrategicSummaryRequest.builder()
		.chatMotor(chatMotor)
		.motorAiOptions(options)
		.filePath(filePath)
		.languageCode("fr")
		.build();
	 
         // We execute the request.
	 MotorResponse response = summarizerRequest.execute();
	 
	 if (response.isResponseOk()) {
             System.out.println("responseFile: " + responseFile);
             FileUtils.write(responseFile, response.getResponse(), "UTF-8");
	 }
	 else {
             System.out.println("throwable   : " + response.getThrowable());
	 }
	 
	 System.out.println();
	 System.out.println(new Date() + " End.");
    }
    
}
