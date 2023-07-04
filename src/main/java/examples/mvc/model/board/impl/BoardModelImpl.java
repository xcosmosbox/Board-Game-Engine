package examples.mvc.model.board.impl;

import examples.events.impl.BoardModelChangeEvent;
import examples.mvc.model.position.impl.PositionImpl;
import src.events.Event;
import src.events.EventListener;
import src.events.EventSource;
import src.mvc.model.board.BoardModel;
import src.mvc.model.position.Position;
import src.mvc.model.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: fengyuxiang
 * @ClassName: BoardModelImpl
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class BoardModelImpl implements BoardModel, EventSource {

    /**
     * private attribute
     * store all position
     */
    private Position[] positions;

    /**
     * private variable to store all event listener
     */
    private List<EventListener> eventListenerList = new ArrayList<>();


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

        // notify all listeners
        notifyListeners();
    }

    /**
     * Remove one token for the corresponding Position
     *
     * @param index Index is a map that stores the Position data structure
     * @return One token object reference
     */
    @Override
    public Token removeOneTokenByPosition(int index) {
        // get the remove token
        Token token = positions[index].removeToken();

        // notify all listeners
        notifyListeners();

        return token;
    }

    /**
     * Add one listener
     *
     * @param listener
     */
    @Override
    public void addListener(EventListener listener) {
        eventListenerList.add(listener);
    }

    /**
     * remove one listener
     *
     * @param listener
     */
    @Override
    public void removeListener(EventListener listener) {
        eventListenerList.remove(listener);
    }

    /**
     * Notify all listeners
     */
    @Override
    public void notifyListeners() {
        // create and init the event
        Event event = new BoardModelChangeEvent();
        event.setEventSource(this);
        event.setEventContext(this.selectAllPositions());
        notifyListeners(event);
    }

    /**
     * Notify all listener and pass one Event
     *
     * @param event
     */
    @Override
    public void notifyListeners(Event event) {
        for (EventListener listener : eventListenerList) {
            listener.onEvent(event);
        }
    }

    /**
     * Remove all listeners
     */
    @Override
    public void clearListeners() {
        eventListenerList = new ArrayList<>();
    }
}
