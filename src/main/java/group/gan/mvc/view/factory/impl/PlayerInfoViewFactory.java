package group.gan.mvc.view.factory.impl;

import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.view.View;
import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.PlayerInfoView;

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
