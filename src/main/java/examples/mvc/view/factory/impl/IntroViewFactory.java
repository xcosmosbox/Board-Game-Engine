package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.IntroduceView;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;
/**
 * @author: fengyuxiang
 * @ClassName: IntroViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class IntroViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new IntroduceView();
    }
}
