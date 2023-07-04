package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.MovePhaseView;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;

/**
 * @author: fengyuxiang
 * @ClassName: MovePhaseViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class MovePhaseViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new MovePhaseView();
    }
}
