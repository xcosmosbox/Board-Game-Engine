package group.gan.mvc.controller.board;

import group.gan.exception.InvalidCoordinate;
import group.gan.exception.InvalidPosition;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.token.Token;

/**
 * Board controller.
 *
 * It isolates other controllers from direct access to the BoardModel,
 * and uses the interface exposed by the Board controller to perform data operations on the BoardModel.
 *
 * The Board controller performs complex data operations by combining primitive data operations in the BoardModel.
 *
 * The Board controller provides external and internal logic judgment functions for testing Coordinate and Position,
 * which are used to ensure the rigor of the logic and the stability of the function.
 */
public interface Board {

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
     * Check whether the removed token belong to different players
     * (Sprint2 does not test this)
     * @param player
     * @param coordinate
     * @return
     * @throws InvalidPosition
     */
    Boolean checkRemoveValid(Player player, Coordinate coordinate) throws InvalidPosition;

    /**
     * Check position is empty or not
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    Boolean checkPositionIsEmpty(Coordinate coordinate) throws InvalidCoordinate;

    /**
     * Map the Integer corresponding to Coordinate according to the properties file
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    Integer parsePosition(Coordinate coordinate) throws InvalidCoordinate;

    /**
     * Get one position from the board
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    Position getOnePositionFromBoard(Coordinate coordinate) throws InvalidCoordinate;

    /**
     * Get all positions
     * @return
     */
    Position[] getAllPositionsFromBoard();




}
