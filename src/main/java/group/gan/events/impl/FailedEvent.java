package group.gan.events.impl;

import group.gan.events.Event;
import group.gan.events.EventType;

/**
 * @author: fengyuxiang
 * @ClassName: FailedEvent
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class FailedEvent<T> implements Event<T> {

    /**
     * private variable for Event Type
     */
    private EventType eventType = EventType.FAILED;

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
    public Object getEventContext() {
        return eventContext;
    }

    /**
     * Set the specific content of the event (Optional)
     *
     * @param context
     */
    @Override
    public void setEventContext(Object context) {
        eventContext = context;
    }
}
