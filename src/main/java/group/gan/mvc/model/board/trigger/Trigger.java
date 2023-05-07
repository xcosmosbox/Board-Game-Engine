package group.gan.mvc.model.board.trigger;

import group.gan.events.EventListener;
import group.gan.events.EventSource;

/**
 * A generic trigger interface.
 * Allows for configuring triggers and rules in many different ways.
 * Reusable with different scenes and games.
 */
public interface Trigger extends EventSource, EventListener {
    /**
     * Initialize triggers and rules.
     * Multiple ways are allowed.
     */
    void initializeTrigger();

}
