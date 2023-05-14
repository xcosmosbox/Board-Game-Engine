package group.gan.mvc.controller.game;

import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.game.GameModel;

/**
 * Game Controller. Game Controller is used to control the overall flow of the game.
 * It doesn't care about any superfluous game details.
 * It requests a Command from player through the turn manager at the game progresses.
 * And execute the obtained Command.
 * Output a different exit message after the game ends.
 */
public interface Game {
    /**
     * Game Start
     */
    void run();

    /**
     * Quit
     */
    void quit();

    /**
     * Quit for winning
     * @param winner game winner
     */
    void quitForWin(Player winner);

    /**
     * Build the Game model
     * @param gameModel game model
     */
    void build(GameModel gameModel);

}
