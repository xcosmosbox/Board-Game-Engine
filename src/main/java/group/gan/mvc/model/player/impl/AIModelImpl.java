package group.gan.mvc.model.player.impl;

import group.gan.events.*;
import group.gan.events.EventListener;
import group.gan.events.impl.FailedEvent;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.player.AIPlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;

import java.util.*;

/**
 * @author: fengyuxiang
 * @ClassName: AIModelImpl
 * @version: 1.0
 * @description:
 * @create: 25/5/2023
 */
public class AIModelImpl implements AIPlayerModel, EventListener, EventSource {


    // Initialize the player name
    private String playerName;
    // Instantiate the player states enum
    private PlayerStateEnum state;
//    // Initialize the player uid
//    public static Integer uid = 0;
    // Initialize the player uid
    private Integer playerUid;
    // Array of tokens owned by the player
    private Token[] tokens;

    // Set a counter for the number of tokens that the player has placed
    private int placeTokenCounter = 0;
    // Set a counter for the number of tokens that the player has been milled
    private int millTokenCounter = 0;
    // Define the number of milled tokens required to enable flying state
    private int flyCondition = 6;
    // Define the number of milled tokens required to reach the losing state
    private int loseCondition= 7;
    // Define the listener type for this class
    private ListenerType listenerType = ListenerType.PLAYER;

    private List<EventListener> listeners = new ArrayList<>();

    /**
     * using Map to store all valid moves for each position
     * for example, position 0 can move to position 1 or 9
     * Map< 0, set<1,9> >
     */
    private Map<Integer, Set<Integer>> validMovesMap;

    /**
     * Since this class completes the event monitoring,
     * we need to store a cache so that the AI can plan its own chess strategy on the latest board.
     */
    private Position[] positionsCache;


    public AIModelImpl(String playerName){
        this.playerName = playerName;
        this.playerUid = PlayerModelImpl.uid;
        this.state = PlayerStateEnum.PLACING;
        this.initValidMovesMap();
        PlayerModelImpl.uid++;
    }

    /**
     * Event listener interface
     *
     * @param event
     */
    @Override
    public void onEvent(Event event) {
        // Get the event type from the event object
        EventType type = event.getEventType();
        // Check if the event type is PLACE
        if (type == EventType.PLACE) {
            // Get the event context object from the event
            Object obj = event.getEventContext();
            // Check if the event context object is an instance of Token
            if (obj instanceof Token) {
                // Cast the event context object to a Token object
                Token t = (Token) obj;
                // Iterate through the tokens array
                for (Token token : tokens) {
                    // Check if the current token in the array is equal to the token from the event context
                    if (token.equals(t)) {
                        // Increment the placeTokenCounter
                        placeTokenCounter++;
                        // Check if the placeTokenCounter is equal to the total number of tokens
                        if (placeTokenCounter == tokens.length) {
                            // Set the player's state to MOVING
                            setState(PlayerStateEnum.MOVING);
                        }
                        // skip the move state
                        if (placeTokenCounter == tokens.length && millTokenCounter == flyCondition){
                            setState(PlayerStateEnum.FLYING);
                        }
                    }
                }
            }
            // Check if the event type is MILL
        } else if (type == EventType.TOKEN_DEATH) {
            // Get the event context object from the event
            Object obj = event.getEventContext();
            // Check if the event context object is an instance of Token
            if (obj instanceof Token) {
                // Cast the event context object to a Token object
                Token t = (Token) obj;
                // Iterate through the tokens array using an index
                for (int i = 0; i < tokens.length; i++) {
                    // Check if the current token in the array is equal to the token from the event context
                    if (tokens[i].equals(t)){
                        // Set the current token in the array to a "dead" state
                        tokens[i].goDie();
                        // Increment the millTokenCounter
                        millTokenCounter++;
                        // Check if the millTokenCounter is equal to the flyCondition value
                        if (millTokenCounter == flyCondition && placeTokenCounter == tokens.length) {
                            // Set the player's state to FLYING
                            setState(PlayerStateEnum.FLYING);
                            // Check if the millTokenCounter is equal to the loseCondition value
                        } else if (millTokenCounter == loseCondition) {
                            // Set the player's state to FAILED
                            setState(PlayerStateEnum.FAILED);

                            // create FAILED event
                            Event<PlayerModelImpl> failedEvent = new FailedEvent<>();
                            event.setEventSource(this);
                            event.setEventContext(this);
                            notifyListeners(failedEvent);
                        }
                    }
                }
            }
        }else if (type == EventType.BOARD_CHANGE) {

            if (event.getEventContext().getClass().isArray()
                    && Position.class.isAssignableFrom(event.getEventContext().getClass().getComponentType())
                    && getState() == PlayerStateEnum.MOVING) {
                // init the check point
                Boolean isDeadEnd = true;

                // init and casting event context to Position[] type
                Position[] positions = (Position[]) event.getEventContext();

                // store the position cache to help the AI player to decide the next step
                positionsCache = positions;

                // check all own tokens set in board
                int allTokensNum = 0;
                for (Token token : tokens) {
                    if (token.getOwner() != null){
                        allTokensNum += 1;
                    }
                }
                for (Position position : positions) {
                    if (!position.isEmpty()){
                        if(position.peekToken().getOwner().getUid().equals(getUid())){
                            allTokensNum -= 1;
                        }
                    }
                }

                if (allTokensNum == 0){
                    // using a counter to avoid the non-token end point
                    int counter = 0;
                    // check and modify the check dead end
                    for (int i = 0; i < positions.length; i++) {
                        if (!positions[i].isEmpty()){
                            if (positions[i].peekToken().getOwner().getUid().equals(getUid())){
                                counter += 1;
                                for (Integer integer : validMovesMap.get(i)) {
                                    if (positions[integer].isEmpty()){
                                        isDeadEnd = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (isDeadEnd == false){
                            break;
                        }
                    }

                    if (counter == 0){
                        isDeadEnd = false;
                    }


                    // check is deadEnd
                    if (isDeadEnd){
                        // Set the player's state to FAILED
                        setState(PlayerStateEnum.FAILED);

                        // create FAILED event
                        Event<PlayerModelImpl> failedEvent = new FailedEvent<>();
                        event.setEventSource(this);
                        event.setEventContext(this);
                        notifyListeners(failedEvent);
                    }
                }

            }

        }

    }

    /**
     * Get the type of listener
     *
     * @return
     */
    @Override
    public ListenerType getListenerType() {
        return listenerType;
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
        // nothing to do
    }

    /**
     * Notify all listener and pass one Event
     *
     * @param event
     */
    @Override
    public void notifyListeners(Event event) {
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

    /**
     * get the name of the player
     *
     * @return
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * set the name of the player
     *
     * @param name
     */
    @Override
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * get the state of the player
     *
     * @return
     */
    @Override
    public PlayerStateEnum getState() {
        return state;
    }

    /**
     * set the state of the player
     *
     * @param state
     */
    @Override
    public void setState(PlayerStateEnum state) {
        this.state = state;
    }

    /**
     * update the state of the player
     *
     * @param state
     */
    @Override
    public void updateState(PlayerStateEnum state) {
        this.state = state;
    }

    /**
     * get the user input
     *
     * @return
     */
    @Override
    public String getUserInput() {
        // Implement getUserInput() method if needed.
        return null;
    }

    /**
     * get the command builder
     *
     * @return
     */
    @Override
    public Command getCommandBuilder() {
        // Implement getCommandBuilder() method if needed.
        return null;
    }

    /**
     * get the uid of the player
     *
     * @return
     */
    @Override
    public Integer getUid() {
        return playerUid;
    }

    /**
     * get one token
     *
     * @return
     */
    @Override
    public Token getOneToken() {

        // Create a new unmodifiedToken object to hold the retrieved token's data
        Token unmodifiedToken = new TokenImpl();
        // Set the owner of the unmodifiedToken to match the owner of the token being retrieved
        unmodifiedToken.setOwner(tokens[placeTokenCounter].getOwner());
        // Set the symbol of the unmodifiedToken to match the symbol of the token being retrieved
        unmodifiedToken.setSymbol(tokens[placeTokenCounter].getSymbol());
        // Set the token ID of the unmodifiedToken to match the token ID of the token being retrieved
        unmodifiedToken.setTokenID(tokens[placeTokenCounter].getTokenID());

        // Return the unmodifiedToken object
        return unmodifiedToken;
    }

    /**
     * set the tokens
     *
     * @param tokens
     */
    @Override
    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }

    /**
     * get the tokens
     *
     * @return
     */
    @Override
    public Token[] getTokens() {
        // Create a new array of unmodifiedTokens with the same length as the player's tokens
        Token[] unmodifiedTokens = new TokenImpl[tokens.length];

        // Iterate through the player's tokens using an index
        for (int i = 0; i < tokens.length; i++) {
            // Create a new unmodifiedToken object for each token in the player's tokens
            unmodifiedTokens[i] = new TokenImpl();
            // Set the owner of the unmodifiedToken to match the owner of the current token in the player's tokens
            unmodifiedTokens[i].setOwner(tokens[i].getOwner());
            // Set the symbol of the unmodifiedToken to match the symbol of the current token in the player's tokens
            unmodifiedTokens[i].setSymbol(tokens[i].getSymbol());
            // Set the token ID of the unmodifiedToken to match the token ID of the current token in the player's tokens
            unmodifiedTokens[i].setTokenID(tokens[i].getTokenID());
        }

        // Return the array of unmodifiedTokens
        return unmodifiedTokens;
    }

    /**
     * get the number of tokens
     *
     * @return
     */
    @Override
    public Integer getNumberOfTokens() {
        return tokens.length;
    }

    /**
     * remove one token
     *
     * @return
     */
    @Override
    public Token removeOneToken() {

        // Create a new unmodifiedToken object to hold the removed token's data
        Token unmodifiedToken = new TokenImpl();
        // Set the owner of the unmodifiedToken to match the owner of the token being removed
        unmodifiedToken.setOwner(tokens[placeTokenCounter].getOwner());
        // Set the symbol of the unmodifiedToken to match the symbol of the token being removed
        unmodifiedToken.setSymbol(tokens[placeTokenCounter].getSymbol());
        // Set the token ID of the unmodifiedToken to match the token ID of the token being removed
        unmodifiedToken.setTokenID(tokens[placeTokenCounter].getTokenID());

        // Return the unmodifiedToken object
        return unmodifiedToken;
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

    @Override
    public Position[] getPositionsCache() {
        return new Position[0];
    }

    @Override
    public List<Coordinate> getPlaceableOptions() {
        return null;
    }

    @Override
    public List<Coordinate[]> getMovableOptions() {
        return null;
    }

    @Override
    public List<Coordinate[]> getFlyableOptions() {
        return null;
    }

    @Override
    public List<Coordinate> getRemovableOptions() {
        return null;
    }
}
