package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.PlacePhaseView;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;

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
