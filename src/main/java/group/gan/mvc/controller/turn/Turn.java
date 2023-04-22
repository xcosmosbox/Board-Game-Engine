package group.gan.mvc.controller.turn;


import group.gan.exception.InvalidCoordinate;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.token.Token;




/**
 * Turn controller.
 */

public interface Turn {

        /**
         * run the turn
         * @return
         */
        Command runTurn();

        /**
         * after the turn, make player keep running the turn
         * @return
         */
        Command continueRun();


        /**
         * when we get a invalid command, we will send a command with the same type command.
         * @return
         */
        Command refillCommand();


        /**
         * get the pollable instance
         * @return
         */
        Pollable getPollableInstance();

        /**
         * switch the pollable object
         */
        void switchPollableObject();

        /**
         * register the pollable object
         * @param pollable
         */
        void registerPollableObject(Pollable pollable);
















}
