package group.gan.mvc.view.factory.impl;

import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.IntroduceView;
import group.gan.mvc.view.View;

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