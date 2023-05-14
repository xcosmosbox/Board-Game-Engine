package group.gan.mvc.view.factory.impl;

import group.gan.mvc.view.View;
import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.MillPhaseView;

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
