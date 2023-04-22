package group.gan.mvc.controller.move.factory.impl;

import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.move.factory.MoveStrategyFactory;
import group.gan.mvc.controller.move.impl.PlacingStrategy;

/**
 * @author: fengyuxiang
 * @ClassName: PlacingStrategyFactory
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class PlacingStrategyFactory implements MoveStrategyFactory {
    /**
     * A function for generating MoveStrategy
     *
     * @return The specific implementation class of MoveStrategy
     */
    @Override
    public MoveStrategy createStrategy() {
        return new PlacingStrategy();
    }
}
