package src.mvc.model.game.state;


import src.events.EventListener;

public interface GameState extends EventListener {
    /**
     * check game is over
     * @return
     */
    Boolean isGameOver();
}
