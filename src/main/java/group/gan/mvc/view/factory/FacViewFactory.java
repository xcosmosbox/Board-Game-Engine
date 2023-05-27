package group.gan.mvc.view.factory;

import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.player.impl.PlayerImpl;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.view.factory.impl.FlyPhaseViewFactory;
import group.gan.mvc.view.factory.impl.MovePhaseViewFactory;
import group.gan.mvc.view.factory.impl.PlacePhaseViewFactory;

/**
 * @author: fengyuxiang
 * @ClassName: FacViewFactory
 * @version: 1.0
 * @description:
 * @create: 25/4/2023
 */
public class FacViewFactory {
    public static ViewFactory createFactory(Pollable pollable){
        ViewFactory factory = null;
        if (pollable instanceof Player){
            Player player = (Player) pollable;
            switch (player.getPlayerState()){
                case PLACING -> factory = new PlacePhaseViewFactory();
                case MOVING -> factory = new MovePhaseViewFactory();
                case FLYING -> factory = new FlyPhaseViewFactory();
            }
        }
        return factory;
    }
}
