package src.mvc.model.game;

import src.mvc.controller.board.Board;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Turn;

/**
 * GameModel is used to save some data in the game and verify the state of the game itself.
 * Since our team does not implement the advanced function of saving games.
 * Therefore, the functions of GameModel in this project are not rich.
 * However, it has the implementation of advanced functions with reserved interfaces. (our group didn't have to implement it)
 */
public interface GameModel {

    /**
     * setter for Turn manager
     * @param turn
     */
    void setTurn(Turn turn);

    /**
     * getter for Turn manager
     * @return
     */
    Turn getTurn();

    /**
     * setter for players
     * @param players
     */
    void setPlayers(Player... players);

    /**
     * setter for player
     * @param player
     */
    void setPlayer(Player player);

    /**
     * getter for player
     * @return
     */
    Player getPlayer();

    /**
     * getter for players
     * @return
     */
    Player[] getPlayers();

    /**
     * setter for board
     * @param board
     */
    void setBoard(Board board);

    /**
     * getter for board
     * @return
     */
    Board getBoard();


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
