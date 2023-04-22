package group.gan.mvc.model.board.impl;

import group.gan.mvc.model.board.BoardModel;
import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.position.impl.PositionImpl;
import group.gan.mvc.model.token.Token;

import java.util.ResourceBundle;

/**
 * @author: fengyuxiang
 * @ClassName: BoardModelImpl
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class BoardModelImpl implements BoardModel {

    /**
     * private attribute
     * store all position
     */
    private Position[] positions;


    /**
     * no-parameter constructor
     */
    public BoardModelImpl() {
        initPositions();
    }

    /**
     * private method to initialize all positions
     */
    private void initPositions() {
        // load resource file
        ResourceBundle resourceBundleBundle = ResourceBundle.getBundle("CoordinatePositionMap");

        // read the number of lines
        int numberOfPositions = Integer.parseInt(resourceBundleBundle.getString("numberOfPositions"));

        // init positions done
        this.positions = new Position[numberOfPositions];

        // init position object
        for (int i = 0; i < numberOfPositions; i++) {
            positions[i] = new PositionImpl();
        }
    }

    /**
     * pass an index to get one unmodifiable position
     * Defensive Copying
     * @param index index is a map that stores the Position data structure
     * @return One Position object reference
     */
    @Override
    public Position selectOnePosition(int index) {
        // Create an empty Position object
        Position unmodifiablePosition = new PositionImpl();
        // Through defensive copying to copy token and position data
        if (!positions[index].isEmpty()){
            unmodifiablePosition.addToken(positions[index].peekToken());
        }
        // return unmodifiable position
        return unmodifiablePosition;
    }

    /**
     * Get all the Position information and return it in the form of an array
     *
     * @return A set of data that cannot be modified
     */
    @Override
    public Position[] selectAllPositions() {
        // Create an empty unmodifiable position list
        Position[] unmodifiablePositionList = new PositionImpl[positions.length];

        // init unmodifiablePositionList object
        for (int i = 0; i < unmodifiablePositionList.length; i++) {
            unmodifiablePositionList[i] = new PositionImpl();
            // Through defensive copying to copy token and position data
            if (!positions[i].isEmpty()){
                unmodifiablePositionList[i].addToken(positions[i].peekToken());
            }
        }

        // return unmodifiable positions list
        return unmodifiablePositionList;
    }

    /**
     * Pass an index to get one token on corresponding postion
     *
     * @param index index is a map that stores the Position data structure
     * @return One token object reference
     */
    @Override
    public Token selectOneTokenByPosition(int index) {
        return positions[index].peekToken();
    }

    /**
     * Add a Token to the corresponding Position
     *
     * @param token One token object reference
     * @param index Index is a map that stores the Position data structure
     */
    @Override
    public void addOneTokenIntoPosition(Token token, int index) {
        positions[index].addToken(token);
    }

    /**
     * Remove one token for the corresponding Position
     *
     * @param index Index is a map that stores the Position data structure
     * @return One token object reference
     */
    @Override
    public Token removeOneTokenByPosition(int index) {
        return positions[index].removeToken();
    }
}
