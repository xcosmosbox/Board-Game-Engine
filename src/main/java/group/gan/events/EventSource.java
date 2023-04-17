package group.gan.events;

/**
 * A group of interfaces for implementing the Event Listening design pattern
 * EventSource: Initiator of the event
 */
public interface EventSource {
    /**
     * Add one listener
     * @param listener
     */
    void addListener(EventListener listener);

    /**
     * remove one listener
     * @param listener
     */
    void removeListener(EventListener listener);

    /**
     * Notify all listeners
     */
    void notifyListeners();

    /**
     * Notify all listener and pass one Event
     * @param event
     */
    void notifyListeners(Event event);

    /**
     * Remove all listeners
     */
    void clearListeners();
}
