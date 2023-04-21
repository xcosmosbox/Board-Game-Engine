package group.gan.mvc.model.game;

import group.gan.mvc.controller.player.Player;

public interface GameModel {

    Player getWinner();
    Boolean isGameOver();
    void saveGame();
    void loadGame();
}
