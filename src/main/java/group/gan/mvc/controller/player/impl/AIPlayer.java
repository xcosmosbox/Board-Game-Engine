package group.gan.mvc.controller.player.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.view.View;

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
     *
     * @return
     */
    @Override
    public Command play() {
        return null;
    }

    /**
     * set the state manager
     *
     * @param playerModel
     */
    @Override
    public void setStateManager(PlayerModel playerModel) {

    }

    /**
     * get the uid of the player
     *
     * @return
     */
    @Override
    public Integer getUid() {
        return null;
    }

    /**
     * get one token
     *
     * @return
     */
    @Override
    public Token getOneToken() {
        return null;
    }

    /**
     * get the player name
     *
     * @return
     */
    @Override
    public String getPlayerName() {
        return null;
    }

    /**
     * get player state
     *
     * @return
     */
    @Override
    public PlayerStateEnum getPlayerState() {
        return null;
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
}
