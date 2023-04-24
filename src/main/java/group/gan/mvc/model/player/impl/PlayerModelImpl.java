package group.gan.mvc.model.player.impl;

import group.gan.events.Event;
import group.gan.events.EventListener;
import group.gan.events.EventType;
import group.gan.events.ListenerType;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.token.Token;

public class PlayerModelImpl implements PlayerModel, EventListener {

    private String playerName;
    private PlayerStateEnum state;
    public static Integer uid = 0;
    private Integer playerUid;
    private Token[] tokens;
    private Command commandBuilder;
    private int placeTokenCounter = 0;
    private int millTokenCounter = 0;
    private int flyCondition = 6;
    private int loseCondition= 7;
    private ListenerType listenerType = ListenerType.PLAYER;


    public PlayerModelImpl(String playerName) {
        this.playerName = playerName;
        this.playerUid = uid;
        this.state = PlayerStateEnum.PLACING;
        this.tokens = new Token[0];
        uid++;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    @Override
    public PlayerStateEnum getState() {
        return state;
    }

    @Override
    public void setState(PlayerStateEnum state) {
        this.state = state;
        //onstate
    }

    @Override
    public void updateState(PlayerStateEnum state) {
        this.state = state;
    }

    @Override
    public String getUserInput() {
        // Implement getUserInput() method if needed.
        return null;
    }

    @Override
    public Command getCommandBuilder() {
        return commandBuilder;
    }

    public void setCommandBuilder(Command commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    @Override
    public Integer getUid() {
        return playerUid;
    }

    @Override
    public Token getOneToken() {

        if (tokens.length > 0) {
            Token token = tokens[0];
            removeOneToken();
            return token;
        }
        return null;
    }

    @Override
    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public Token[] getTokens() {
        Token[] unmodifiedToken = new Token[tokens.length];
        return unmodifiedToken;
    }

    @Override
    public Integer getNumberOfTokens() {
        return tokens.length;
    }

    @Override
    public Token removeOneToken() {
        Token[] unmodifiedTokens = new Token[tokens.length];

        if (unmodifiedTokens.length > 0) {
            Token removedToken = unmodifiedTokens[0];
            Token[] newTokens = new Token[unmodifiedTokens.length - 1];
            System.arraycopy(unmodifiedTokens, 1, newTokens, 0, unmodifiedTokens.length - 1);
            unmodifiedTokens = newTokens;
            return removedToken;
        }
        return null;
    }

    @Override
    public void onEvent(Event event) {
        // Implement onEvent() method if needed.
        EventType type = event.getEventType();
        if (type == EventType.PLACE) {
            Object obj = event.geEventContext();
            if (obj instanceof Token) {
                Token t = (Token) obj;
                for (Token token : tokens) {
                    if (token.equals(t)) {
                        placeTokenCounter++;
                        if (placeTokenCounter == tokens.length) {
                            setState(PlayerStateEnum.MOVING);
                        }
                    }
                }
            }
        } else if (type == EventType.MILL) {
            Object obj = event.geEventContext();
            if (obj instanceof Token) {
                Token t = (Token) obj;
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].equals(t)){
                        tokens[i].goDie();
                        tokens[i] = null;
                        millTokenCounter++;
                        if (millTokenCounter == flyCondition) {
                            setState(PlayerStateEnum.FLYING);
                        } else if (millTokenCounter == loseCondition) {
                            setState(PlayerStateEnum.FAILED);
                        }
                    }
                }
            }
        }
    }

    @Override
    public ListenerType getListenerType() {
        return listenerType;
    }
}