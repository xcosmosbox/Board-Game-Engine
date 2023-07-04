package examples.mvc.controller.command.factory.impl;

import examples.mvc.controller.command.CommandType;
import examples.mvc.controller.command.impl.MillCommand;
import examples.mvc.controller.command.impl.QuitCommand;
import src.mvc.controller.command.Command;
import src.mvc.controller.command.factory.CommandFactory;

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
