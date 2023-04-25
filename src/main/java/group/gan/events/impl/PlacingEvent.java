package group.gan.events.impl;

import group.gan.events.Event;
import group.gan.events.EventType;

/**
 * @author: fengyuxiang
 * @ClassName: PlacingEvent
 * @version: 1.0
 * @description:
 * @create: 26/4/2023
 */
public class PlacingEvent<T> implements Event<T> {

    /**
     * private variable for Event Type
     */
    private EventType eventType = EventType.PLACE;

    /**
     * private variable for event source
     */
    private T eventSource = null;

    /**
     * private variable for event context
     */
    private Object eventContext = null;

    /**
     * Get event type
     *
     * @return
     */
    @Override
    public EventType getEventType() {
        return eventType;
    }

    /**
     * The event source usually refers to the initiator of the event
     *
     * @return A generic T that may come from different event sources for events with the same content
     */
    @Override
    public T getEventSource() {
        return eventSource;
    }

    /**
     * Set the specific event source
     *
     * @param t
     */
    @Override
    public void setEventSource(T t) {
        eventSource = t;
    }

    /**
     * Get the specific content of the event (Optional)
     *
     * @return Object
     */
    @Override
    public Object geEventContext() {
        return eventContext;
    }

    /**
     * Set the specific content of the event (Optional)
     */
    @Override
    public void setEventContext(Object context) {
        eventContext = context;
    }
}
