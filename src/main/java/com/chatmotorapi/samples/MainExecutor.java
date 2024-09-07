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

import java.util.Date;

import org.apache.commons.lang3.SystemUtils;

public class MainExecutor {
    public static void main(String[] args) throws Exception {

	if (SystemUtils.IS_OS_WINDOWS) {
	    MotorAudioFileConverterTest.main(args);
	    System.out.println("*** " + new Date() + " MotorAudioFileConverterTest ***");
	}

	MotorImageRequestSample.main(args);
	System.out.println("*** " + new Date() + " MotorImageRequestSample ***");

	MotorLargeRequestLinesSample.main(args);
	System.out.println("*** " + new Date() + " MotorLargeRequestLinesSample ***");

	MotorLargeRequestTextSample.main(args);
	System.out.println("*** " + new Date() + " MotorLargeRequestTextSample ***");

	MotorRequestSample.main(args);
	System.out.println("*** " + new Date() + " MotorRequestSample ***");

	MotorRequestStreamingSample.main(args);
	System.out.println("*** " + new Date() + " MotorRequestStreamingSample ***");

	MotorRequestTestAsStreamSample.main(args);
	System.out.println("*** " + new Date() + " MotorRequestTestAsStreamSample ***");

	MotorRequestTestContext.main(args);
	System.out.println("*** " + new Date() + " MotorRequestTestContext ***");

	MotorSummarizerRequestTest.main(args);
	System.out.println("*** " + new Date() + " MotorSummarizerRequestTest ***");

	MotorTranslationRequestSample.main(args);
	System.out.println("*** " + new Date() + " MotorTranslationRequestSample ***");

	MotorTranslationRequestSampleHtml.main(args);
	System.out.println("*** " + new Date() + " MotorTranslationRequestSampleHtml ***");

	MotorTranslationRequestSampleHtml2.main(args);
	System.out.println("*** " + new Date() + " MotorTranslationRequestSampleHtml2 ***");

	MotorVisionRequestSample.main(args);
	System.out.println("*** " + new Date() + " MotorVisionRequestSample ***");

	MotorVisionRequestSampleLocal.main(args);
	System.out.println("*** " + new Date() + " MotorVisionRequestSampleLocal ***");
	
	MotorLargeTranscriptionRequestTest.main(args);
	System.out.println("*** " + new Date() + " MotorLargeTranscriptionRequestTest ***");
	
	MotorTranscripFormatterRequestSample.main(args);
	System.out.println("*** " + new Date() + " MotorTranscripFormatterRequestSample ***");

    }
}
