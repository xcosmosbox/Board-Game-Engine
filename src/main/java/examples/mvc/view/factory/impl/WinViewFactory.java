package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.WinView;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Pollable;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;

/**
 * @author: fengyuxiang
 * @ClassName: WinViewFactory
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class WinViewFactory implements ViewFactory {

    private Pollable winner;

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
