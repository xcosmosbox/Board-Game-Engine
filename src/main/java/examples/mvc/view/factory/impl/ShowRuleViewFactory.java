package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.ShowRuleWordOnly;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;

/**
 * @author: fengyuxiang
 * @ClassName: showRuleViewFactory
 * @version: 1.0
 * @description:
 * @create: 7/5/2023
 */
public class ShowRuleViewFactory implements ViewFactory {
    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new ShowRuleWordOnly();
    }
}
