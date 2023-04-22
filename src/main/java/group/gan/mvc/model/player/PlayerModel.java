package group.gan.mvc.model.player;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.model.token.Token;

/**
 * in this interface we define the methods that the player model should have.
 * the player model is the model that the player controller will use to get
 * the user input and to get the player name.
 *
 */

public interface PlayerModel {

    /**
     * get the name of the player
     * @return
     */
    String getPlayerName();

    /**
     * set the name of the player
     * @param name
     */
    void setPlayerName(String name);

    /**
     * get the state of the player
     * @return
     */
    PlayerStateEnum getState();

    /**
     * set the state of the player
     * @param state
     */
    void setState(PlayerStateEnum state);

    /**
     * update the state of the player
     */
    void updateState();

    /**
     * update the state of the player
     * @param state
     */
    void updateState(PlayerStateEnum state);

    /**
     * get the user input
     * @return
     */
    String getUserInput();

    /**
     * get the command builder
     * @return
     */
    Command getCommandBuilder();

    /**
     * get the uid of the player
     * @return
     */
    Integer getUid();

    /**
     * get one token
     * @return
     */
    Token getOneToken();

    /**
     * set the tokens
     * @param tokens
     */
    void setTokens(Token[] tokens);

    /**
     * get the tokens
     * @return
     */
    Token[] getTokens();

    /**
     * get the number of tokens
     * @return
     */
    Integer getNumberOfTokens();

    /**
     * remove one token
     * @return
     */
    Token removeOneToken();











}
