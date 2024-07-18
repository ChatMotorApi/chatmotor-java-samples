package com.chatmotorapi.api.test.android.util;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;
import java.util.concurrent.Executors;

import com.chatmotorapi.api.ChatMotor;

public class ChatMotorGetter {
    

    
    /**
     * Returns a ChatMotor instance.
     */
    public static ChatMotor getChatMotor() {
	 
	 String key = ParmsAndroid.KEY;
	 String licenseFilePath = ParmsAndroid.LICENSE_FILE_PATH;
	 		 
	 HttpClient httpClient = HttpClient.newBuilder()
		    .version(Version.HTTP_1_1)
		    .followRedirects(Redirect.NORMAL)
		    .connectTimeout(Duration.ofSeconds(200))
		    .executor(Executors.newFixedThreadPool(3))
		    //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
		    .build();
	 
	 ChatMotor chatMotor = ChatMotor.builder()
		       .licenseFilePath(licenseFilePath) // Optional if CHATMOTOR_API_KEY env var is set
		       .apiKey(key) // Optional if CHATMOTOR_API_KEY env var is set
		       .httpClient(httpClient) // Optional
		       //.organizationId(organisationId) // Optional
		       .maxRetries(3) // Sets the maximum number of retries for the API request. Optional
		       .retryInterval(Duration.ofMillis(3000)) // Sets the retry interval between API request retries. Optional
		       .build();
 
	 return chatMotor;
    }

}
