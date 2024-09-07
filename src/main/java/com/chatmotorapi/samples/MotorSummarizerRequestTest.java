/*
 * ChatMotor API Samples
 *
 * MIT License
 * 
 * Copyright (c) 2024 ChatMotorApi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.chatmotorapi.samples;

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
