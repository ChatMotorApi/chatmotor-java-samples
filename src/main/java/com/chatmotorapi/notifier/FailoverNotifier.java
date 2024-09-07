package com.chatmotorapi.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code FailoverNotifier} interface is used to notify users when a failover
 * occurs in ChatMotor API key usage. Implementations should define the {@code notifyFailover()}
 * method to handle actions like logging, sending alerts, or other custom logic.
 * 
 * <p>This interface allows for flexible implementation strategies depending on the
 * specific needs of the application, whether it's simply logging the failover event,
 * sending notifications, or executing more complex recovery procedures.</p>
 * 
 * <h2>Default Implementation:</h2>
 * The default implementation logs the failover event using a static logger named 'logger'
 * which is shared among all instances of implementing classes unless overridden.
 * <pre>{@code
 * public class DefaultFailoverNotifier implements FailoverNotifier {
 *     private static final Logger logger = LoggerFactory.getLogger(DefaultFailoverNotifier.class);
 *
 *     @Override
 *     public void notifyFailover() {
 *         logger.info("API key switched to the failover key.");
 *     }
 * }
 * }</pre>
 * 
 * <h2>Example Implementation:</h2>
 * Below is a simple implementation example that logs the failover event:
 * <pre>{@code
 * public class MyFailoverNotifier implements FailoverNotifier {
 *     @Override
 *     public void notifyFailover() {
 *         System.out.println("API key switched to the failover key.");
 *         // Custom logic for failover notification
 *     }
 * }
 * 
 * ChatMotor chatMotor = ChatMotor.builder()
 *     .failoverNotifier(new MyFailoverNotifier())
 *     .build();
 * }</pre>
 *
 * <p>For a concrete implementation using Twilio to send SMS notifications on failover,
 * refer to the following GitHub repository:</p>
 * <a href="https://github.com/ChatMotorApi/chatmotor-java-samples/blob/master/src/main/java/com/chatmotorapi/notifier/SmsFailoverNotifier.java">
 * SmsFailoverNotifier on GitHub</a>
 *
 * @since 1.2
 */
public interface FailoverNotifier {

    /**
     * Static logger used by the default implementation to log failover events.
     */
    Logger logger = LoggerFactory.getLogger(FailoverNotifier.class);

    /**
     * Invoked when a failover occurs, signaling that the ChatMotor API has switched
     * from the primary API key to the failover key. The implementation should define
     * the actions to be taken upon this event.
     * 
     * <p>This default implementation logs the event using the above-mentioned static logger.
     */
    default void notifyFailover() {
        logger.info("Default failover action: API key switched to the failover key.");
    }

}
