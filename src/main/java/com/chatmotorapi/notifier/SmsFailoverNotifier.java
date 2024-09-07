package com.chatmotorapi.notifier;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * SmsFailoverNotifier sends an SMS to a list of phone numbers using Twilio when
 * a failover occurs. This class is designed to be integrated into systems that require
 * notification capabilities to handle API key failovers or other critical operational changes.
 *
 * <p>It uses the Twilio API to send messages and requires valid Twilio credentials.
 * The notifier is intended to be used as part of an application that can respond to
 * failover events, such as switching API keys when the primary key becomes unavailable.</p>
 *
 * <h2>Sample Usage:</h2>
 * The following example demonstrates how to initialize and use the SmsFailoverNotifier:
 * <pre>
 * Logger logger = LoggerFactory.getLogger(SmsFailoverNotifierSample.class);
 * 
 * String accountSid = "[Twilio Account SID]";  // Replace with your Twilio Account SID
 * String authToken = "[Twilio Auth Token]";    // Replace with your Twilio Auth Token
 * String recipientPhoneNumber = "+88888888888"; // The phone number to notify
 * String fromPhoneNumber = "+99999999999";      // The Twilio phone number used to send the message
 * 
 * List<String> phoneNumbers = Arrays.asList(recipientPhoneNumber);
 * String messageText = "The main ChatMotor API Key is down. Switching to the failover ChatMotor API Key....";
 * 
 * // Create a new SmsFailoverNotifier instance with the necessary details
 * FailoverNotifier smsFailoverNotifier = new SmsFailoverNotifier(
 *     logger, accountSid, authToken, phoneNumbers, messageText, fromPhoneNumber);
 * 
 * String failoverApiKey = "[Failover OpenAI API Key]";  // Replace with your failover API key
 * ChatMotor chatMotor = ChatMotor
 *     .builder()
 *     .failoverApiKey(failoverApiKey)         // Set the failover API key
 *     .failoverNotifier(smsFailoverNotifier)  // Set the notifier to the ChatMotor instance
 *     .build();
 * 
 * // The ChatMotor instance is now ready to use, handling failover with SMS notifications
 * </pre>
 *
 * @see com.chatmotorapi.api.ChatMotor
 * @see com.chatmotorapi.notifier.FailoverNotifier
 */

public class SmsFailoverNotifier implements FailoverNotifier {

    private final Logger logger;
    private final String accountSid;
    private final String authToken;
    private final List<String> phoneNumbers;
    private final String messageText;
    private final String fromPhoneNumber;

    /**
     * Constructs a new SmsFailoverNotifier with specified Twilio credentials, a
     * list of phone numbers, a message text, a sender's phone number, and a logger.
     *
     * @param logger          Logger for logging events.
     * @param accountSid      Twilio Account SID
     * @param authToken       Twilio Auth Token
     * @param phoneNumbers    List of phone numbers to notify
     * @param messageText     Message text to send
     * @param fromPhoneNumber Twilio phone number that will send the message
     */
    public SmsFailoverNotifier(Logger logger, String accountSid, String authToken, List<String> phoneNumbers,
	    String messageText, String fromPhoneNumber) {
	this.logger = Objects.requireNonNull(logger, "Logger cannot be null");
	this.accountSid = Objects.requireNonNull(accountSid, "Account SID cannot be null");
	this.authToken = Objects.requireNonNull(authToken, "Auth Token cannot be null");
	this.phoneNumbers = Objects.requireNonNull(phoneNumbers, "Phone numbers list cannot be null");
	this.messageText = Objects.requireNonNull(messageText, "Message text cannot be null");
	this.fromPhoneNumber = Objects.requireNonNull(fromPhoneNumber, "Sender's phone number cannot be null");
    }

    /**
     * Notify all listed phone numbers via SMS about the failover event.
     */
    @Override
    public void notifyFailover() {

	try {
	    Twilio.init(accountSid, authToken);

	    if (phoneNumbers.isEmpty()) {
		logger.warn("No phone numbers provided to notify.");
		return;
	    }

	    for (String number : phoneNumbers) {
		if (number != null && !number.trim().isEmpty()) {
		    try {
			Message message = Message.creator(new PhoneNumber(number), // To number
				new PhoneNumber(fromPhoneNumber), // From a valid Twilio number
				messageText).create();

			logger.info("Sent message to {} with SID: {}", number, message.getSid());
		    } catch (Exception e) {
			logger.error("Failed to send SMS to {}: {}", number, e.getMessage(), e);
		    }
		} else {
		    logger.warn("Invalid phone number provided; skipping.");
		}
	    }
	} catch (Exception e) {
	    logger.error("An Exception occured: " + e.toString());
	}

    }
}
