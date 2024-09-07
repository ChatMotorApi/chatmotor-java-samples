package com.chatmotorapi.notifier;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * SmsFailoverNotifier sends an SMS to a list of phone numbers using Twilio when
 * a failover occurs.
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
    }
}
