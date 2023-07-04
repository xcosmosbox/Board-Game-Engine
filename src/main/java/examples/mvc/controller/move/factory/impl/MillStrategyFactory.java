package examples.mvc.controller.move.factory.impl;

import examples.mvc.controller.move.impl.MillStrategy;
import src.mvc.controller.move.MoveStrategy;
import src.mvc.controller.move.factory.MoveStrategyFactory;

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
