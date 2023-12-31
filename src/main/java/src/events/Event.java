package src.events;

import examples.events.EventType;

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
     * Set the specific event source
     * @param t
     */
    void setEventSource(T t);

    /**
     * Get the specific content of the event (Optional)
     * @return Object
     */
    Object getEventContext();

    /**
     * Set the specific content of the event (Optional)
     */
    void setEventContext(Object context);


}
