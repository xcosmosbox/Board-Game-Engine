package group.gan.mvc.controller.game;

import group.gan.mvc.model.game.GameModel;

public interface Game {
    void run();
    void quit();
    void quitForWin();
    void build(GameModel gameModel);

}
