package src.events;


import examples.events.ListenerType;

/**
 * A group of interfaces for implementing the Event Listening design pattern
 */
public interface EventListener {
    /**
     * Event listener interface
     * @param event
     */
    void onEvent(Event event);

    /**
     * Get the type of listener
     * @return
     */
    ListenerType getListenerType();
}
