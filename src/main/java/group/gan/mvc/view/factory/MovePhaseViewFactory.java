package group.gan.mvc.view.factory;

import group.gan.mvc.view.MovePhaseView;
import group.gan.mvc.view.View;

/**
 * @author: fengyuxiang
 * @ClassName: MovePhaseViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class MovePhaseViewFactory implements ViewFactory{
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
