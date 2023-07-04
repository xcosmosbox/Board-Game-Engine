package examples.mvc.view.factory;

import examples.mvc.view.factory.impl.FlyPhaseViewFactory;
import examples.mvc.view.factory.impl.MovePhaseViewFactory;
import examples.mvc.view.factory.impl.PlacePhaseViewFactory;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Pollable;
import src.mvc.view.factory.ViewFactory;

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
