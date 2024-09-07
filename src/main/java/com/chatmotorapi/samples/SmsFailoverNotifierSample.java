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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatmotorapi.notifier.SmsFailoverNotifier;

public class SmsFailoverNotifierSample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

	Logger logger = LoggerFactory.getLogger(SmsFailoverNotifierSample.class);
	
	File file = new File( SystemUtils.USER_HOME + File.separator + "account_sid.txt");
	if (!file.exists() ) {
            logger.error("Account SID file not found at {}", file.getAbsolutePath());
            System.exit(1);
        }
	String accountSid = FileUtils.readFileToString( file, "UTF-8" );
	
	
	File file2 = new File( SystemUtils.USER_HOME + File.separator + "auth_token.txt");
	if (!file2.exists() ) {
            logger.error("Account SID file not found at {}", file2.getAbsolutePath());
            System.exit(1);
        }
	String authToken =  FileUtils.readFileToString( file2, "UTF-8" );
	

	List<String> phoneNumbers = Arrays.asList("+33623261275");
	String messageText = "Hello, this is a test message!";
	String fromPhoneNumber = "+14159149273";

	// Create a new SmsFailoverNotifier instance
	SmsFailoverNotifier notifier = new SmsFailoverNotifier(logger, accountSid,
	authToken, phoneNumbers, messageText, fromPhoneNumber);
	notifier.notifyFailover();

    }

}
