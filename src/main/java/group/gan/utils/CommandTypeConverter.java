package group.gan.utils;

import group.gan.exception.InvalidMenuChoose;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.view.View;
import group.gan.mvc.view.impl.FlyPhaseView;
import group.gan.mvc.view.impl.MillPhaseView;
import group.gan.mvc.view.impl.MovePhaseView;
import group.gan.mvc.view.impl.PlacePhaseView;

/**
 * @author: fengyuxiang
 * @ClassName: CommandTypeConverter
 * @version: 1.0
 * @description:
 * @create: 25/4/2023
 */
public class CommandTypeConverter {
    public static CommandType IntegerToCommandType(Integer input, View view) throws InvalidMenuChoose {
        CommandType commandType = null;
        if ((view instanceof PlacePhaseView || view instanceof MovePhaseView) && input == 1){
            commandType =  CommandType.MOVE;
        } else if ((view instanceof PlacePhaseView || view instanceof MovePhaseView) && input == 2) {
            commandType =  CommandType.QUIT;
        } else if (view instanceof MillPhaseView  && input == 1) {
            commandType = CommandType.MILL;
        } else if (view instanceof MillPhaseView && input == 2) {
            commandType = CommandType.QUIT;
        } else if (view instanceof FlyPhaseView && input == 1) {
            commandType = CommandType.MOVE;
        } else if (view instanceof FlyPhaseView && input == 2) {
            commandType = CommandType.QUIT;
        } else {
            throw new InvalidMenuChoose("Please enter 1 or 2!");
        }

        return commandType;
    }
}
