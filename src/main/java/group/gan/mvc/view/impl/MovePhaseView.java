package group.gan.mvc.view.impl;

import group.gan.mvc.view.View;
import group.gan.utils.Display;

/**
 * @author: fengyuxiang
 * @ClassName: MovePhaseView
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class MovePhaseView implements View {
    /**
     * Drawing methods implemented by subclasses
     *
     * @param display
     */
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("  1: Move Token\n");
        stringBuilder.append("  2: Quit Game\n");
        stringBuilder.append("  Your select(integer): ");

        display.displayView(stringBuilder.toString());
    }
}
