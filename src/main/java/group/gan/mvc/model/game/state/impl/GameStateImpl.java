package group.gan.mvc.model.game.state.impl;

import group.gan.mvc.model.game.state.GameState;
import group.gan.mvc.model.game.state.GameStateEnum;

/**
 * @author Tianyi Liu
 * @className GameStateImpl
 * @version 1.0
 * @created 25/4/2023
 */

public class GameStateImpl implements GameState {
    /**
     * a private variable which stores game state enum
     */
    private GameStateEnum gameStateEnum;

    /**
     * no-parameter constructor
     */
    public GameStateImpl() {
        this.gameStateEnum = GameStateEnum.RUN;
    }

    /**
     * Check if the game is over
     *
     * @return boolean value indicating if the game is over
     */
    @Override
    public Boolean isGameOver() {
        return gameStateEnum != GameStateEnum.RUN;
    }


}
