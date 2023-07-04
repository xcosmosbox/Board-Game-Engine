package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.MillPhaseView;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;
/**
 * @author: fengyuxiang
 * @ClassName: MillPhaseViewFactory
 * @version: 1.0
 * @description:
 * @create: 14/5/2023
 */
public class MillPhaseViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new MillPhaseView();
    }
}
