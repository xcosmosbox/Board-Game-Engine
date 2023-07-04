package examples.mvc.controller.move.factory.impl;

import examples.mvc.controller.move.impl.FlyingStrategy;
import src.mvc.controller.move.MoveStrategy;
import src.mvc.controller.move.factory.MoveStrategyFactory;

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
