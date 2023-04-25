package group.gan.mvc.view.impl;

import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.view.View;
import group.gan.utils.Display;

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
