package src.events;

/**
 * Interface for implementing the observer pattern
 */
public interface Observer {
    /**
     * Observer interface for receiving information
     * @param observable
     */
    void notifyObserver(Observable observable);
}
