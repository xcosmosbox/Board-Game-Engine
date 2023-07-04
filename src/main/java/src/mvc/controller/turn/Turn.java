package src.mvc.controller.turn;


import src.mvc.controller.command.Command;


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
         * @param command one command
         * @return refill command
         */
        Command refillCommand(Command command);


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
