package group.gan.mvc.model.game.state.impl;

import group.gan.events.Event;
import group.gan.events.EventType;
import group.gan.events.ListenerType;
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


    /**
     * Event listener interface
     *
     * @param event
     */
    @Override
    public void onEvent(Event event) {
        if (event.getEventType() == EventType.FAILED){
            gameStateEnum = GameStateEnum.QUIT_FOR_WIN;
        }
    }

    /**
     * Get the type of listener
     *
     * @return
     */
    @Override
    public ListenerType getListenerType() {
        return ListenerType.GAME_STATE;
    }
}
