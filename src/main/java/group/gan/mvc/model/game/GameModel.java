package group.gan.mvc.model.game;

import group.gan.mvc.controller.player.Player;

/**
 * GameModel is used to save some data in the game and verify the state of the game itself.
 * Since our team does not implement the advanced function of saving games.
 * Therefore, the functions of GameModel in this project are not rich.
 * However, it has the implementation of advanced functions with reserved interfaces. (our group didn't have to implement it)
 */
public interface GameModel {

    /**
     * return winner
     * @return winner
     */
    Player getWinner();

    /**
     * check game is win
     * @return
     */
    Boolean isGameOver();

    /**
     * save game data(Show interface Only, No implements)
     */
    void saveGame();

    /**
     * load game data(Show interface Only, No implements)
     */
    void loadGame();
}
