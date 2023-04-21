package group.gan.mvc.controller.turn;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.view.View;



public interface Pollable {

    /**
     * get the command type from the view
     * @param view
     * @return
     */
    CommandType poll(View view);

    /**
     * fill the command with the command type
     * @param command
     * @return
     */
    Command fillCommand(Command command);

}
