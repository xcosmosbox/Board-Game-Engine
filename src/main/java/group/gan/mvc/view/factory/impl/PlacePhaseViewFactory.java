package group.gan.mvc.view.factory.impl;

import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.PlacePhaseView;
import group.gan.mvc.view.View;

/**
 * @author: fengyuxiang
 * @ClassName: PlacePhaseViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class PlacePhaseViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new PlacePhaseView();
    }
}
