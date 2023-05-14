package group.gan.mvc.model.game.state;

import group.gan.events.EventListener;

public interface GameState extends EventListener {
    /**
     * check game is over
     * @return
     */
    Boolean isGameOver();
}
