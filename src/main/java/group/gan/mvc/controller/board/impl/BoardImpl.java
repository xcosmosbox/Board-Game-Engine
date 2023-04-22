package group.gan.mvc.controller.board.impl;

import group.gan.exception.InvalidCoordinate;
import group.gan.exception.InvalidPosition;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.board.BoardModel;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.coordinate.impl.CoordinateImpl;
import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.token.Token;

import java.util.*;

/**
 * @author: fengyuxiang
 * @ClassName: BoardImpl
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class BoardImpl implements Board {

    /**
     * Board controller through the board model to access and operate position and token data
     */
    private BoardModel boardModel;

    /**
     * using a Map to store coordinate position mapping
     * for example:
     * Coordinate(0,6) == 6
     * Coordinate(1,3) == 10
     * BoardImpl doesn't care how this data is mapped on a specific board. It manages abstract mappings.
     * The specific mapping data is saved in the properties file and exists as a configuration file.
     */
    private Map<Coordinate, Integer> coordinatePositionMapping;

    /**
     * using Map to store all valid moves for each position
     * for example, position 0 can move to position 1 or 9
     * Map< 0, set<1,9> >
     */
    private Map<Integer, Set<Integer>> validMovesMap;

    /**
     * constructor
     * @param boardModel
     */
    public BoardImpl(BoardModel boardModel) {
        this.boardModel = boardModel;
        initCoordinatePositionMapping();
        initValidMovesMap();
    }

    /**
     * using resource bundle to read CoordinatePositionMap.properties file
     * to initialize the coordinatePositionMapping
     */
    private void initCoordinatePositionMapping() {
        // load resource file
        ResourceBundle resourceBundleBundle = ResourceBundle.getBundle("CoordinatePositionMap");

        // read the number of lines
        int numberOfPositions = Integer.parseInt(resourceBundleBundle.getString("numberOfPositions"));

        // instanceof coordinatePositionMapping
        this.coordinatePositionMapping = new HashMap<>();

        // assign value
        for (int position = 0; position < numberOfPositions; position++) {
            // create search key
            String key = String.valueOf(position);
            if (resourceBundleBundle.containsKey(key)) {
                // according to the key to get the coordinate string
                String moves = resourceBundleBundle.getString(key);

                // using split function to get x and y string
                String[] coordinateArray = moves.split(",");

                // parse the String to Int
                int x = Integer.parseInt(coordinateArray[0].trim());
                int y = Integer.parseInt(coordinateArray[1].trim());

                Coordinate coordinate = new CoordinateImpl(x, y);

                // assign value
                this.coordinatePositionMapping.put(coordinate, position);
            }
        }
    }

    /**
     * using resource bundle to read ValidMove.properties file
     * to initialize the validMovesMap
     */
    private void initValidMovesMap() {
        // load resource file
        ResourceBundle resourceBundleBundle = ResourceBundle.getBundle("ValidMove");

        // read the number of lines
        int numberOfPositions = Integer.parseInt(resourceBundleBundle.getString("numberOfPositions"));

        // instanceof coordinatePositionMapping
        this.validMovesMap = new HashMap<>();

        // assign value
        for (int position = 0; position < numberOfPositions; position++) {
            // create search key
            String key = String.valueOf(position);
            if (resourceBundleBundle.containsKey(key)) {
                // according to the key to get the coordinate string
                String moves = resourceBundleBundle.getString(key);

                // using split function to get x and y string
                String[] moveArray = moves.split(",");

                // create neighbor
                Set<Integer> moveSet = new HashSet<>();

                for (String move : moveArray) {
                    // parse the String to Int
                    int location = Integer.parseInt(move.trim());
                    moveSet.add(location);
                }

                // assign value
                validMovesMap.put(position, moveSet);
            }
        }
    }

    /**
     * Players can place token on the board
     *
     * @param token
     * @param coordinate
     * @throws InvalidCoordinate
     */
    @Override
    public void PlaceToken(Token token, Coordinate coordinate) throws InvalidCoordinate {
        if (checkPositionValid(coordinate) && checkPositionIsEmpty(coordinate)){
            boardModel.addOneTokenIntoPosition(token, parsePosition(coordinate));
        }
    }

    /**
     * Players can move token on the board
     *
     * @param from
     * @param to
     * @throws InvalidCoordinate
     */
    @Override
    public void MoveToken(Coordinate from, Coordinate to) throws InvalidCoordinate {
        if (checkPositionValid(from) && checkPositionValid(to)
                && checkMoveValid(from, to)
                && !checkPositionIsEmpty(from) && checkPositionIsEmpty(to)){
            Token token = boardModel.removeOneTokenByPosition(parsePosition(from));
            boardModel.addOneTokenIntoPosition(token, parsePosition(to));
        }
    }

    /**
     * Players can remove one token from the board (mill state)
     * (Sprint2 does not test this)
     *
     * @param player
     * @param coordinate
     * @throws InvalidCoordinate
     */
    @Override
    public void RemoveToken(Player player, Coordinate coordinate) throws InvalidCoordinate {
        // (Sprint2 does not test this)
    }

    /**
     * Check whether the Coordinate is within the allowed range of the chessboard
     *
     * @param coordinate
     * @return
     */
    @Override
    public Boolean checkPositionValid(Coordinate coordinate) {
        return coordinatePositionMapping.containsKey(coordinate);
    }

    /**
     * Check whether the Move operation is in compliance with the rules
     * (Sprint2 does not test this)
     *
     * @param from
     * @param to
     * @return
     * @throws InvalidCoordinate
     */
    @Override
    public Boolean checkMoveValid(Coordinate from, Coordinate to) throws InvalidCoordinate {
        return true; // ONLY Sprint2 always true (Sprint2 does not test this)
    }

    /**
     * Check whether the removed token belong to different players
     * (Sprint2 does not test this)
     *
     * @param player
     * @param coordinate
     * @return
     * @throws InvalidPosition
     */
    @Override
    public Boolean checkRemoveValid(Player player, Coordinate coordinate) throws InvalidPosition {
        return true; // (Sprint2 does not test this)
    }

    /**
     * Check position is empty or not
     *
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    @Override
    public Boolean checkPositionIsEmpty(Coordinate coordinate) throws InvalidCoordinate {
        return getOnePositionFromBoard(coordinate).isEmpty();
    }

    /**
     * Map the Integer corresponding to Coordinate according to the properties file
     *
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    @Override
    public Integer parsePosition(Coordinate coordinate) throws InvalidCoordinate {
        if (checkPositionValid(coordinate)){
            return coordinatePositionMapping.get(coordinate);
        }
        throw new InvalidCoordinate("Invalid Coordinate: You entered a wrong Coordinate!");
    }

    /**
     * Get one position from the board
     *
     * @param coordinate
     * @return
     * @throws InvalidCoordinate
     */
    @Override
    public Position getOnePositionFromBoard(Coordinate coordinate) throws InvalidCoordinate {
        return boardModel.selectOnePosition(parsePosition(coordinate));
    }

    /**
     * Get all positions
     *
     * @return
     */
    @Override
    public Position[] getAllPositionsFromBoard() {
        return boardModel.selectAllPositions();
    }
}
