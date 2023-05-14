package group.gan.mvc.controller.move.factory.impl;

import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.move.factory.MoveStrategyFactory;
import group.gan.mvc.controller.move.impl.MillStrategy;

/**
 * @author: fengyuxiang
 * @ClassName: MillStrategyFactory
 * @version: 1.0
 * @description:
 * @create: 14/5/2023
 */
public class MillStrategyFactory implements MoveStrategyFactory {
    /**
     * A function for generating MoveStrategy
     *
     * @return The specific implementation class of MoveStrategy
     */
    @Override
    public MoveStrategy createStrategy() {
        return new MillStrategy();
    }
}
