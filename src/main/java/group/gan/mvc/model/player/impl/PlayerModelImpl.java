package group.gan.mvc.model.player.impl;

import group.gan.events.Event;
import group.gan.events.EventListener;
import group.gan.events.EventType;
import group.gan.events.ListenerType;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;

public class PlayerModelImpl implements PlayerModel, EventListener {

    private String playerName;
    private PlayerStateEnum state;
    public static Integer uid = 0;
    private Integer playerUid;
    private Token[] tokens;
//    private Command commandBuilder;
    private int placeTokenCounter = 0;
    private int millTokenCounter = 0;
    private int flyCondition = 6;
    private int loseCondition= 7;
    private ListenerType listenerType = ListenerType.PLAYER;


    public PlayerModelImpl(String playerName) {
        this.playerName = playerName;
        this.playerUid = PlayerModelImpl.uid;
        this.state = PlayerStateEnum.PLACING;
        PlayerModelImpl.uid++;
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
        return null;
    }

    public void setCommandBuilder(Command commandBuilder) {
        // nothing to do
    }

    @Override
    public Integer getUid() {
        return playerUid;
    }

    @Override
    public Token getOneToken() {

        Token unmodifiedToken = new TokenImpl();
        unmodifiedToken.setOwner(tokens[placeTokenCounter].getOwner());
        unmodifiedToken.setSymbol(tokens[placeTokenCounter].getSymbol());
        unmodifiedToken.setTokenID(tokens[placeTokenCounter].getTokenID());

        return unmodifiedToken;
    }

    @Override
    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public Token[] getTokens() {
        Token[] unmodifiedTokens = new TokenImpl[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            unmodifiedTokens[i] = new TokenImpl();
            unmodifiedTokens[i].setOwner(tokens[i].getOwner());
            unmodifiedTokens[i].setSymbol(tokens[i].getSymbol());
            unmodifiedTokens[i].setTokenID(tokens[i].getTokenID());
        }

        return unmodifiedTokens;
    }

    @Override
    public Integer getNumberOfTokens() {
        return tokens.length;
    }

    @Override
    public Token removeOneToken() {

        Token unmodifiedToken = new TokenImpl();
        unmodifiedToken.setOwner(tokens[placeTokenCounter].getOwner());
        unmodifiedToken.setSymbol(tokens[placeTokenCounter].getSymbol());
        unmodifiedToken.setTokenID(tokens[placeTokenCounter].getTokenID());

        return unmodifiedToken;
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