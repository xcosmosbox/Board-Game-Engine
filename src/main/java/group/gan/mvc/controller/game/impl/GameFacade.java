package group.gan.mvc.controller.game.impl;


import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.game.Game;
import group.gan.mvc.model.game.GameModel;
import group.gan.mvc.view.View;
import group.gan.mvc.view.impl.BoardView;
import group.gan.utils.Display;


/**
 * @author Tianyi Liu
 * Class Name: GameImpl
 * @version 1.0
 * created by: 25/4/2023
 */
public class GameFacade implements Game {
    /**
     * a private variable to store the game model
     */
    private GameModel gameModel;

    /**
     * a boolean value indicating whether the game needs to exit
     */
    private Boolean quit = false;

    /**
     * Starting game
     */
    @Override
    public void run() {
        Display display = new Display();
        while (!gameModel.isGameOver()) {
            // Render game board
            View boardView = new BoardView(gameModel.getBoard().getAllPositionsFromBoard());
            boardView.draw(display);

            // Request a command from the player
            Command command = gameModel.getTurn().runTurn();

            // Issue & execute the command
            if (command.getCommandType() == CommandType.MOVE) {
                command.init(this, gameModel.getBoard());
            } else if (command.getCommandType() == CommandType.QUIT) {
                command.init(this);
            }
            command.execute();

            // =========  FUTURE  =========
            // In the following iteration, we will implement the operation of checking whether a mill has been formed
            // and the player has taken a token. We will not implement this function in sprint2 for the time being
            // =========  FUTURE  =========

            // Check if the game exits
            if (!quit) {
                //start next turn
                gameModel.getTurn().switchPollableObject();
            }

        }


    }

    /**
     * Quit
     */
    @Override
    public void quit() {
        this.quit = true;
        Display display = new Display();
        display.displayMessage("Game Quit!");
    }

    /**
     * Quit after winning
     */
    @Override
    public void quitForWin() {
        this.quit = true;
        Display display = new Display();
        display.displayMessage("You Won!");
    }

    /**
     * Build the game model
     *
     * @param gameModel game model
     */
    @Override
    public void build(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
