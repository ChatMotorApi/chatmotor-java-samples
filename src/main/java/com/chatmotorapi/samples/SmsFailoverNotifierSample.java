package com.chatmotorapi.samples;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatmotorapi.api.ChatMotor;
import com.chatmotorapi.notifier.FailoverNotifier;
import com.chatmotorapi.notifier.SmsFailoverNotifier;

/**
 * Demonstrates how to use the {@link SmsFailoverNotifier} in conjunction with the {@link ChatMotor} API
 * to handle failover scenarios by sending SMS notifications.
 * 
 * This sample initializes a {@link SmsFailoverNotifier} with Twilio credentials and sets it up to send
 * an SMS alert when the primary ChatMotor API key fails, automatically switching to a failover API key.
 * 
 * Usage:
 * <ul>
 * <li>Set your Twilio Account SID and Auth Token</li>
 * <li>Configure the recipient and sender phone numbers</li>
 * <li>Set the message to be sent on failover</li>
 * <li>Initialize the {@link ChatMotor} instance with the failover notifier</li>
 * </ul>
 */
public class SmsFailoverNotifierSample {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(SmsFailoverNotifierSample.class);
        
        String accountSid = "[Twilio Account SID]";
        String authToken = "[Twilio Auth Token]";
        String recipientPhoneNumber = "+88888888888";
        String fromPhoneNumber = "+99999999999"; 
        
        List<String> phoneNumbers = Arrays.asList(recipientPhoneNumber);
        String messageText = "The main ChatMotor API Key is down. Switching to the failover ChatMotor API Key....";

        // Create a new SmsFailoverNotifier instance
        FailoverNotifier smsFailoverNotifier = new SmsFailoverNotifier(logger, accountSid, authToken, phoneNumbers, messageText,
            fromPhoneNumber);
        
        String failoverApiKey = "[Failover OpenAI API Key]";
        ChatMotor chatMotor = ChatMotor
            .builder()
            .failoverApiKey(failoverApiKey)
            //.failoverNotifier(smsFailoverNotifier) // Uncomment to activate failover notifier
            .build();
        
        // Use the ChatMotor instance as needed...
    }
}
