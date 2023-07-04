package src.mvc.controller.command.factory;

import examples.mvc.controller.command.CommandType;
import src.mvc.controller.command.Command;

/**
 * An interface for implementing simple factory pattern
 */
public interface CommandFactory {
    /**
     * Generate a specific Command according to the CommandType
     * @param commandType
     * @return
     */
    Command createCommand(CommandType commandType);
}
