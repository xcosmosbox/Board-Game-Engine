package group.gan.events;

/**
 * A group of interfaces for implementing the Event Listening design pattern
 */
public interface Event<T> {
    /**
     * Get event type
     * @return
     */
    EventType getEventType();

    /**
     * The event source usually refers to the initiator of the event
     * @return A generic T that may come from different event sources for events with the same content
     */
    T getEventSource();

    /**
     * Get the specific content of the event (Optional)
     * @return Object
     */
    Object geEventContext();

    /**
     * Set the specific content of the event (Optional)
     */
    void setEventContext();


}
