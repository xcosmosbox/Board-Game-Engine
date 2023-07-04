package src.events;

/**
 * Interface for implementing the observer pattern
 */
public interface Observable {
    /**
     * add one observer to observer list
     * @param observer
     */
    void addObserver(Observer observer);

    /**
     * pass one observer and remove it from observe list
     * @param observer
     */
    void removeObserver(Observer observer);
}
