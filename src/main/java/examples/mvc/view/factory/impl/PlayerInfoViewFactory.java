package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.PlayerInfoView;
import src.mvc.controller.turn.Pollable;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;

/**
 * @author: fengyuxiang
 * @ClassName: PlayerInfoViewFactory
 * @version: 1.0
 * @description:
 * @create: 26/4/2023
 */
public class PlayerInfoViewFactory implements ViewFactory {

    private PlayerInfoView playerInfoView = null;

    public PlayerInfoViewFactory(Pollable pollable) {
        this.playerInfoView = new PlayerInfoView(pollable);
    }

    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return playerInfoView;
    }
}
