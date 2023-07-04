package examples.mvc.view.impl;
import src.mvc.controller.turn.Pollable;
import src.mvc.view.View;
import src.utils.Display;
/**
 * @author: fengyuxiang
 * @ClassName: PlayerInfoView
 * @version: 1.0
 * @description:
 * @create: 26/4/2023
 */
public class PlayerInfoView implements View {

    /**
     * object for this turn
     */
    private Pollable pollable;

    /**
     * one parameter constructor
     * @param pollable
     */
    public PlayerInfoView(Pollable pollable) {
        this.pollable = pollable;
    }

    /**
     * Drawing methods implemented by subclasses
     *
     * @param display
     */
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("  Current Player: " + pollable + "\n");

        display.displayView(stringBuilder.toString());
    }
}
