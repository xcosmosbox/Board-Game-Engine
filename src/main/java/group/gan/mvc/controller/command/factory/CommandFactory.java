package group.gan.mvc.controller.command.factory;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;

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
