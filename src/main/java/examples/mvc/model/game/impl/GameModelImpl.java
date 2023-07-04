package examples.mvc.model.game.impl;

import examples.mvc.model.player.PlayerStateEnum;
import src.mvc.controller.board.Board;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Turn;
import src.mvc.model.game.GameModel;
import src.mvc.model.game.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameModelImpl implements GameModel {
    /**
     * private variable to store Turn
     */
    private Turn turn;
    /**
     * private variable to store Board
     */
    private Board board;
    /**
     * A list to store all players
     */
    private List<Player> playerList = new ArrayList<>();

    private GameState gameState;

    /**
     * no-parameter constructor
     */
    public GameModelImpl(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * setter for Turn manager
     *
     * @param turn
     */
    @Override
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * getter for Turn manager
     *
     * @return
     */
    @Override
    public Turn getTurn() {
        return turn;
    }

    /**
     * setter for players list
     *
     * @param players
     */
    @Override
    public void setPlayers(Player... players) {
        for (Player player : players) {
            playerList.add(player);
        }
    }

    /**
     * setter for player
     *
     * @param player
     */
    @Override
    public void setPlayer(Player player) {
        playerList.add(player);
    }

    /**
     * getter for player
     *
     * @return
     */
    @Override
    public Player getPlayer() {
        for (Player player : playerList) {
            if (player.equals(turn.getPollableInstance())) {
                return player;
            }
        }
        return null;
    }

    /**
     * getter for player list
     *
     * @return
     */
    @Override
    public Player[] getPlayers() {
        return playerList.toArray((new Player[0]));
    }

    /**
     * setter for board
     *
     * @param board
     */
    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * getter for board
     *
     * @return
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * @return winner
     */
    @Override
    public Player getWinner() {
        if (gameState.isGameOver() && turn.getPollableInstance() instanceof Player) {
            if (((Player) turn.getPollableInstance()).getPlayerState() != PlayerStateEnum.FAILED) {
                return (Player) turn.getPollableInstance();
            } else {
                turn.switchPollableObject();
                return (Player) turn.getPollableInstance();
            }
        }
        return null;
    }

    /**
     * check game is over
     *
     * @return
     */
    @Override
    public Boolean isGameOver() {
        return gameState.isGameOver();
    }

    /**
     * save game data(SHOW INTERFACE ONLY, NO IMPLEMENTATION)
     */
    @Override
    public void saveGame() {
        // Advanced features we did not opt for
        // this is to demonstrate the extensibility of our design
    }

    /**
     * load game data(SHOW INTERFACE ONLY, NO IMPLEMENTATION)
     */
    @Override
    public void loadGame() {
        // Advanced features we did not opt for
        // this is to demonstrate the extensibility of our design
    }
}
