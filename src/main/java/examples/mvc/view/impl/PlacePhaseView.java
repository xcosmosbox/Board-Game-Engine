package examples.mvc.view.impl;
import src.mvc.view.View;
import src.utils.Display;
/**
 * @author: fengyuxiang
 * @ClassName: PlacePhaseView
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class PlacePhaseView implements View {
    /**
     * Drawing methods implemented by subclasses
     *
     * @param display
     */
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("  1: Place Token\n");
        stringBuilder.append("  2: Quit Game\n");
        stringBuilder.append("  Your select(integer): ");

        display.displayView(stringBuilder.toString());

    }
}
