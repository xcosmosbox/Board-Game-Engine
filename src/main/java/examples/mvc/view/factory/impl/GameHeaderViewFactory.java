package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.GameHeaderView;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;
/**
 * @author: fengyuxiang
 * @ClassName: GameHeader
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class GameHeaderViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new GameHeaderView();
    }
}
