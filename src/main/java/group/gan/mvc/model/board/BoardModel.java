package group.gan.mvc.model.board;

import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.token.Token;

/**
 * BoardModel only stores and processes Board data without designing any operation logic.
 * So its operations on the data are primitive and simple, so no checking methods exists.
 *
 */
public interface BoardModel {
    /**
     * pass an index to get one position
     * @param index index is a map that stores the Position data structure
     * @return One Position object reference
     */
    Position selectOnePosition(int index);

    /**
     * Get all the Position information and return it in the form of an array
     * @return A set of data that cannot be modified
     */
    Position[] selectAllPositions();

    /**
     * Pass an index to get one token on corresponding postion
     * @param index index is a map that stores the Position data structure
     * @return One token object reference
     */
    Token selectOneTokenByPosition(int index);

    /**
     * Add a Token to the corresponding Position
     * @param token One token object reference
     * @param index Index is a map that stores the Position data structure
     */
    void addOneTokenIntoPosition(Token token, int index);

    /**
     * Remove one token for the corresponding Position
     * @param index Index is a map that stores the Position data structure
     * @return One token object reference
     */
    Token removeOneTokenByPosition(int index);


}
