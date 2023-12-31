package examples.mvc.controller.game.impl;


import examples.events.EventType;
import examples.events.ListenerType;
import examples.mvc.controller.command.CommandType;
import examples.mvc.controller.command.factory.impl.MillCommandFactory;
import examples.mvc.view.factory.impl.BoardViewFactory;
import examples.mvc.view.factory.impl.PlayerInfoViewFactory;
import examples.mvc.view.factory.impl.WinViewFactory;
import src.events.Event;
import src.events.EventListener;
import src.mvc.controller.command.Command;
import src.mvc.controller.game.Game;
import src.mvc.controller.player.Player;
import src.mvc.model.game.GameModel;
import src.mvc.view.View;
import src.utils.Display;


/**
 * @author Tianyi Liu
 * Class Name: GameImpl
 * @version 1.2
 * created by: 25/4/2023
 */
public class GameFacade implements Game, EventListener {
    /**
     * a private variable to store the game model
     */
    private GameModel gameModel;

    /**
     * a boolean value indicating whether the game needs to exit
     */
    private Boolean quit = false;
    /**
     * a boolean value checking if there's a Mill event
     */

    private Boolean onEventMill = false;

    /**
     * Starting game, this is the main game loop
     */
    @Override
    public void run() {
        Display display = new Display();
        while (!gameModel.isGameOver() && !quit) {
            // Render game board
            View boardView = new BoardViewFactory(gameModel.getBoard().getAllPositionsFromBoard()).createView();
            boardView.draw(display);
            //current player to act and token type
            View userInfoView = new PlayerInfoViewFactory(gameModel.getTurn().getPollableInstance()).createView();
            userInfoView.draw(display);


            //main game loop
            // Request a command from the player
            Command command = gameModel.getTurn().runTurn();

            // Issue & execute the command

            if (command.getCommandType() == CommandType.MOVE) {
                command.init(this, gameModel.getBoard());
            } else if (command.getCommandType() == CommandType.QUIT) {
                command.init(this);
            }
            Boolean result = command.execute();

            // check execution result, if result == false, retrieve coordinates
            while (!result) {
                Command refillCommand = gameModel.getTurn().refillCommand(command);
                // Issue & execute the command
                if (refillCommand.getCommandType() == CommandType.MOVE) {
                    refillCommand.init(this, gameModel.getBoard());
                } else if (refillCommand.getCommandType() == CommandType.QUIT) {
                    refillCommand.init(this);
                }
                result = refillCommand.execute();
            }

            //check if there's a mill event.
            if (onEventMill) {

                View newBoardView = new BoardViewFactory(gameModel.getBoard().getAllPositionsFromBoard()).createView();
                newBoardView.draw(display);

                View newPlayerInfoView = new PlayerInfoViewFactory(gameModel.getTurn().getPollableInstance()).createView();
                newPlayerInfoView.draw(display);

                //tell turn to ask for a command from player to remove a token on the board.
                Command playerNextCommand = gameModel.getTurn().continueRun();
                playerNextCommand.init(this, gameModel.getBoard());
                boolean isValidCommand = playerNextCommand.execute();
                while (!isValidCommand) {
                    MillCommandFactory millCommandFactory = new MillCommandFactory();
                    Command playerRefillCommand = gameModel.getTurn().refillCommand(millCommandFactory.createCommand(CommandType.MILL));
                    playerRefillCommand.init(gameModel.getBoard(), (Player) gameModel.getTurn().getPollableInstance());
                    isValidCommand = playerRefillCommand.execute();
                }
                onEventMill = false;
            }

            // Check if the game exits
            if (!quit) {
                //start next turn
                gameModel.getTurn().switchPollableObject();
            }
        }

        // get winner and quit
        if (gameModel.getWinner() != null){
            quitForWin(gameModel.getWinner());
        }


    }

    /**
     * Quit
     */
    @Override
    public void quit() {
        this.quit = true;
        Display display = new Display();
        display.displayMessage("  Game Quit!" + display.getNewLine());
    }

    /**
     * Quit after winning
     */
    @Override
    public void quitForWin(Player winner) {
        this.quit = true;
        Display display = new Display();
        new WinViewFactory(winner).createView().draw(display);
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

    /**
     * a method listen to trigger and toggle onEventMill
     *
     * @param event mill event
     */
    @Override
    public void onEvent(Event event) {
        if (event != null) {
            if (event.getEventType() == EventType.MILL) {
                onEventMill = true;
            }
        }
    }

    /**
     * a method returns ListenerType game
     * @return ListenerType
     */
    @Override
    public ListenerType getListenerType() {
        return ListenerType.GAME;
    }
}
