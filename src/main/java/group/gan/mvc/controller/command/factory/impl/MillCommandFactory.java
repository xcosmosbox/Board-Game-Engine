package group.gan.mvc.controller.command.factory.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.factory.CommandFactory;
import group.gan.mvc.controller.command.impl.MillCommand;
import group.gan.mvc.controller.command.impl.QuitCommand;

/**
 * @author: fengyuxiang
 * @ClassName: MillCommandFactory
 * @version: 1.0
 * @description:
 * @create: 8/5/2023
 */
public class MillCommandFactory implements CommandFactory {
    /**
     * Generate a specific Command according to the CommandType
     *
     * @param commandType
     * @return
     */
    @Override
    public Command createCommand(CommandType commandType) {
        if (commandType == CommandType.MILL){
            return new MillCommand();
        }
        if (commandType == CommandType.QUIT){
            return new QuitCommand();
        }
        return null;
    }
}
