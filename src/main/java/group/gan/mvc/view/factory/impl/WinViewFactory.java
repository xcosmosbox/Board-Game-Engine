package group.gan.mvc.view.factory.impl;

import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.view.View;
import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.WinView;

/**
 * @author: fengyuxiang
 * @ClassName: WinViewFactory
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class WinViewFactory implements ViewFactory {

    Pollable winner;

    public WinViewFactory(Player player){
        winner = (Pollable) player;
    }

    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return new WinView(winner);
    }
}
