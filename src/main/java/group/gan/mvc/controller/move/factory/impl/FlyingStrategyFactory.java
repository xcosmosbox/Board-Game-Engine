package group.gan.mvc.controller.move.factory.impl;

import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.move.factory.MoveStrategyFactory;
import group.gan.mvc.controller.move.impl.FlyingStrategy;

/**
 * @author: fengyuxiang
 * @ClassName: FlyingStrategyFactory
 * @version: 1.0
 * @description:
 * @create: 8/5/2023
 */
public class FlyingStrategyFactory implements MoveStrategyFactory {
    /**
     * A function for generating MoveStrategy
     *
     * @return The specific implementation class of MoveStrategy
     */
    @Override
    public MoveStrategy createStrategy() {
        return new FlyingStrategy();
    }
}
