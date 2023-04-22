package group.gan.mvc.controller.command.factory.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.factory.CommandFactory;
import group.gan.mvc.controller.command.impl.QuitCommand;

/**
 * @author: fengyuxiang
 * @ClassName: QuitCommandFactory
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class QuitCommandFactory implements CommandFactory {
    /**
     * Generate a specific Command according to the CommandType
     *
     * @param commandType
     * @return
     */
    @Override
    public Command createCommand(CommandType commandType) {
        if (commandType == CommandType.QUIT){
            return new QuitCommand();
        }
        return null;
    }
}
