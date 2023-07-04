package src.mvc.controller.turn;

import examples.mvc.controller.command.CommandType;
import src.mvc.controller.command.Command;
import src.mvc.view.View;


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
