package group.gan.mvc.controller.board.impl;

import group.gan.events.Event;
import group.gan.events.EventListener;
import group.gan.events.EventSource;
import group.gan.events.impl.MillingEvent;
import group.gan.events.impl.PlacingEvent;
import group.gan.exception.InvalidCoordinate;
import group.gan.exception.InvalidPosition;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.board.BoardModel;
import group.gan.mvc.model.board.trigger.Trigger;
import group.gan.mvc.model.board.trigger.impl.MillNode;
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
public class BoardImpl implements Board, EventSource {

    /**
     * Board controller through the board model to access and operate position and token data
     */
    private BoardModel boardModel;

    /**
     * Has a trigger that stores the state of the mill on the board.
     * Since the data stored in the trigger is different from that of the BoardModel,
     * different objects are used for encapsulation to separate concerns.
     */
    private Trigger trigger;

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
     * using map to store all trigger node dictionary
     * for example, position 0 will affect trigger 1 & 0
     * Map<0,List<0,1>>
     */
    private Map<Integer, List<Integer>> triggerNodeMap;

    /**
     * A list of all listeners
     */
    private List<EventListener> listeners = new ArrayList<>();

    /**
     * constructor
     * @param boardModel
     */
    public BoardImpl(BoardModel boardModel, Trigger trigger) {
        this.boardModel = boardModel;
        this.trigger = trigger;
        initCoordinatePositionMapping();
        initValidMovesMap();
        initTriggerNodeMap();
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
     * using resource bundle to read MillTriggerConfig.properties file
     * to initialize the validMovesMap
     */
    private void initTriggerNodeMap() {
        // load resource file
        ResourceBundle MillTriggerConfigBundle = ResourceBundle.getBundle("MillTriggerConfig");

        // read the number of positions
        int numberOfPositions = Integer.parseInt(MillTriggerConfigBundle.getString("numberOfPositions"));

        //initialising triggerNodeMap
        triggerNodeMap=new HashMap<>();
        // init the key for triggerNodeMap
        for (int i = 0; i < numberOfPositions; i++) {
            triggerNodeMap.put(i,new ArrayList<>());
        }

        // read the number of lines
        int lines = Integer.parseInt(MillTriggerConfigBundle.getString("lines"));

        // assign value
        for (int line = 0; line < lines; line++) {
            // create search key
            String key = String.valueOf(line);
            if (MillTriggerConfigBundle.containsKey(key)){
                // according to the key to get the mill position
                String millStr = MillTriggerConfigBundle.getString(key);

                // using split function to get the three position index
                String[] millArray = millStr.split(",");

                // init the value list for triggerNodeMap
                for (String millIndex : millArray) {
                    Integer index = Integer.parseInt(millIndex.trim());
                    triggerNodeMap.get(index).add(line);
                }
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
    public void placeToken(Token token, Coordinate coordinate) throws InvalidCoordinate {
        if (checkPositionValid(coordinate) && checkPositionIsEmpty(coordinate)){
            // place token
            boardModel.addOneTokenIntoPosition(token, parsePosition(coordinate));

            // notify listeners through place event
            Event<Board> placeEvent = new PlacingEvent<>();
            placeEvent.setEventSource(this);
            placeEvent.setEventContext(token);
            notifyListeners(placeEvent);

        } else {
            throw new InvalidCoordinate("Invalid Coordinate: "+coordinate.toString()+ " !");
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
    public void moveToken(Coordinate from, Coordinate to) throws InvalidCoordinate {
        if (checkPositionValid(from) && checkPositionValid(to)
                && checkMoveValid(from, to)
                && !checkPositionIsEmpty(from) && checkPositionIsEmpty(to)){
            Token token = boardModel.removeOneTokenByPosition(parsePosition(from));
            boardModel.addOneTokenIntoPosition(token, parsePosition(to));
        } else {
            throw new InvalidCoordinate("Invalid Coordinate: " + to.toString()+ " !");
        }
    }

    /**
     * Players can remove one token from the board (mill state)
     *
     * @param player
     * @param coordinate
     * @throws InvalidCoordinate
     */
    @Override
    public void removeToken(Player player, Coordinate coordinate) throws InvalidCoordinate {

        // check the position is valid
        if (!checkPositionValid(coordinate)){
            throw new InvalidCoordinate("Invalid Coordinate: " + coordinate.toString()+ " !");
        }

        // check player can not remove their own token
        if (boardModel.selectOnePosition(parsePosition(coordinate)).peekToken().getOwner().equals(player)){
            throw new InvalidCoordinate("Invalid Coordinate: " + coordinate.toString()+ ". You cannot remove your own token!");
        }

        // check position is not empty
        if (checkPositionIsEmpty(coordinate)){
            throw new InvalidCoordinate("Invalid Coordinate: No token here. Please Choose again!");
        }

        // remove token
        try {
            // check remove is valid
            // token that have formed a mill cannot be removed from the board
            if (checkRemoveValid(player,coordinate)){
                // process the remove token behaviour
                Token token = boardModel.removeOneTokenByPosition(parsePosition(coordinate));

                // notify listeners through place event
                Event<Board> millEvent = new MillingEvent<>();
                millEvent.setEventSource(this);
                millEvent.setEventContext(token);
                notifyListeners(millEvent);

            } else {
                throw new InvalidCoordinate("Invalid Coordinate: You can not remove token that have formed a mill. Please Choose again!");
            }
        } catch (InvalidPosition e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Players can fly one token on the board
     *
     * @param from   start coordinate
     * @param to     destination coordinate
     * @throws InvalidCoordinate invalid coordinate exception
     */
    @Override
    public void flyToken(Coordinate from, Coordinate to) throws InvalidCoordinate {
        if (checkPositionValid(from) && checkPositionValid(to)
                && !checkPositionIsEmpty(from) && checkPositionIsEmpty(to)){
            Token token = boardModel.removeOneTokenByPosition(parsePosition(from));
            boardModel.addOneTokenIntoPosition(token, parsePosition(to));
        } else {
            throw new InvalidCoordinate("Invalid Coordinate: " + to.toString()+ " !");
        }

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
        if (!validMovesMap.get(parsePosition(from)).contains(parsePosition(to))){
            return false;
        }
        return true;
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
        try {
            if (boardModel.selectOnePosition(parsePosition(coordinate)).peekToken().getOwner().equals(player)){
                throw new InvalidCoordinate("Invalid Coordinate: " + coordinate.toString()+ ". You cannot remove your own token!");
            }

            List<Integer> list = triggerNodeMap.get(parsePosition(coordinate));
            for (Integer integer : list) {
                if(trigger.getTriggerNodeState(integer)){
                    return false;
                }
            }

        } catch (InvalidCoordinate e) {
            throw new RuntimeException(e);
        }

        return true;
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

    /**
     * Add one listener
     *
     * @param listener
     */
    @Override
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    /**
     * remove one listener
     *
     * @param listener
     */
    @Override
    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notify all listeners
     */
    @Override
    public void notifyListeners() {
        // noting to do for now
    }

    /**
     * Notify all listener and pass one Event
     *
     * @param event
     */
    @Override
    public void notifyListeners(Event event) {
        // notify
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    /**
     * Remove all listeners
     */
    @Override
    public void clearListeners() {
        listeners = new ArrayList<>();
    }
}
