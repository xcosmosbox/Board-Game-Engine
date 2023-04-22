package group.gan.mvc.controller.turn;


import group.gan.exception.InvalidCoordinate;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.token.Token;

//the aim of this interface is: Turn is used to control the round in the game. Turn stores the player. Whenever the  Game runs a Turn, the Turn will notify the player to play with the game. After a  player has played a turn, the Turn changes who will be playing the next time.



/**
 * Turn controller.
 *
 * It isolates other controllers from direct access to the TurnModel,
 * and uses the interface exposed by the Turn controller to perform data operations on the TurnModel.
 *
 * The Turn controller performs complex data operations by combining primitive data operations in the TurnModel.
 *
 * The Turn controller provides external and internal logic judgment functions for testing Coordinate and Position,
 * which are used to ensure the rigor of the logic and the stability of the function.
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
