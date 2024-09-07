package com.chatmotorapi.notifier;

/**
 * The {@code FailoverNotifier} interface is used to notify users when a failover
 * occurs in ChatMotor API key usage. Implementations should define the {@code notifyFailover()}
 * method to handle actions like logging, sending alerts, or other custom logic.
 * 
 * Example:
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
 * @since 1.2
 */

public interface FailoverNotifier {

    /**
     * Invoked when a failover occurs, signaling that the ChatMotor API has switched
     * from the primary API key to the failover key. The implementation should define
     * the actions to be taken upon this event.
     */
    void notifyFailover();
    
}
