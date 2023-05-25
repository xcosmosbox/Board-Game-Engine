package group.gan.mvc.controller.player.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.view.View;

import java.util.Objects;

/**
 * @author: fengyuxiang
 * @ClassName: AIPlayer
 * @version: 1.0
 * @description:
 * @create: 25/5/2023
 */
public class AIPlayer implements Player, Pollable {

    // Declare a PlayerModel instance
    private PlayerModel playerModel;

    public AIPlayer(PlayerModel playerModel){
        // Assign the provided playerModel to the class variable
        this.playerModel = playerModel;
    }

    /**
     * play the game
     * just add this for the further use
     *
     * @return
     */
    @Override
    public Command play() {
        return null;
    }

    /**
     * set the state manager
     * Injection interface for setting PlayerModel
     * @param playerModel
     */
    @Override
    public void setStateManager(PlayerModel playerModel) {
        // an injection interface for player model
        this.playerModel = playerModel;
    }

    /**
     * get the uid of the player
     *
     * @return
     */
    @Override
    public Integer getUid() {
        return playerModel.getUid();
    }

    /**
     * get one token
     *
     * @return
     */
    @Override
    public Token getOneToken() {
        return playerModel.getOneToken();
    }

    /**
     * get the player name
     *
     * @return
     */
    @Override
    public String getPlayerName() {
        return playerModel.getPlayerName();
    }

    /**
     * get player state
     *
     * @return
     */
    @Override
    public PlayerStateEnum getPlayerState() {
        return playerModel.getState();
    }

    /**
     * The player returns a number according to the prompt
     *
     * @return
     */
    @Override
    public Integer requestOneIntegerInput() {
        return null;
    }

    /**
     * The player returns a list numbers according to the prompt
     *
     * @return
     */
    @Override
    public Integer[] requestIntegersInput() {
        return new Integer[0];
    }

    /**
     * get the command type from the view
     *
     * @param view
     * @return
     */
    @Override
    public CommandType poll(View view) {
        return null;
    }

    /**
     * fill the command with the command type
     *
     * @param command
     * @return
     */
    @Override
    public Command fillCommand(Command command) {
        return null;
    }

    /** Override the equals method for comparing PlayerImpl objects
     * @param o
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIPlayer aiPlayer = (AIPlayer) o;
        return Objects.equals(playerModel, aiPlayer.playerModel);
    }

    /** Override the hashCode method for PlayerImpl objects
     * @return the hash code of the player
     */
    @Override
    public int hashCode() {
        return Objects.hash(playerModel);
    }

    /** Override the toString method for the PlayerImpl class
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        // Get all tokens of the player
        Token[] allTokens = playerModel.getTokens();
        // Get the symbol of the first token
        String symbol = String.valueOf(allTokens[0].getSymbol());
        // Return a string representation of the player with their name and token color
        return getPlayerName() + " / Token Color: " + symbol;
    }
}
