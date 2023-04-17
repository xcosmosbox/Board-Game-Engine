package group.gan.mvc.controller.move.factory;

import group.gan.mvc.controller.move.MoveStrategy;

/**
 * A MoveStrategy factory interface for implementing the factory pattern
 */
public interface MoveStrategyFactory {
    /**
     * A function for generating MoveStrategy
     * @return The specific implementation class of MoveStrategy
     */
    MoveStrategy createStrategy();
}
