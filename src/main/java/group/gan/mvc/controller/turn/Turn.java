package group.gan.mvc.controller.turn;


import group.gan.exception.InvalidCoordinate;
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
        * Players can place token on the board
        * @param token
        * @param coordinate
        * @throws InvalidCoordinate
        */
        void PlaceToken(Token token, Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Players can move token on the board
        * @param from
        * @param to
        * @throws InvalidCoordinate
        */
        void MoveToken(Coordinate from, Coordinate to) throws InvalidCoordinate;

        /**
        * Players can remove one token from the board
        * @param player
        * @param coordinate
        * @throws InvalidCoordinate
        */
        void RemoveToken(Player player, Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Check whether the Coordinate is within the allowed range of the chessboard
        * @param coordinate
        * @return
        */
        Boolean checkPositionValid(Coordinate coordinate);

        /**
        * Check whether the Move operation is in compliance with the rules
        * (Sprint2 does not test this)
        * @param from
        * @param to
        * @return
        * @throws InvalidCoordinate
        */
        Boolean checkMoveValid(Coordinate from, Coordinate to) throws InvalidCoordinate;

        /**
        * Check whether the Remove operation is in compliance with the rules
        * (Sprint2 does not test this)
        * @param player
        * @param coordinate
        * @return
        * @throws InvalidCoordinate
        */
        Boolean checkRemoveValid(Player player, Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Check whether the Place operation is in compliance with the rules
        * (Sprint2 does not test this)
        * @param token
        * @param coordinate
        * @return
        * @throws InvalidCoordinate
        */
        Boolean checkPlaceValid(Token token, Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Check whether the Coordinate is occupied
        * @param coordinate
        * @return
        * @throws InvalidCoordinate
        */
        Boolean checkPositionOccupied(Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Check whether the Coordinate is empty
        * @param coordinate
        * @return
        * @throws InvalidCoordinate
        */
        Boolean checkPositionEmpty(Coordinate coordinate) throws InvalidCoordinate;

        /**
        * Check whether the Coordinate is occupied by the player
        * @ param player
         * @param coordinate
         * @return
         * @throws InvalidCoordinate
         */
        Boolean checkPositionOccupiedByPlayer(Player player, Coordinate coordinate) throws InvalidCoordinate;

        /**
         * Check whether the Coordinate is occupied by the opponent
         * @param player
         * @param coordinate
         * @return
         * @throws InvalidCoordinate
         */
        Boolean checkPositionOccupiedByOpponent(Player player, Coordinate coordinate) throws InvalidCoordinate;




}
