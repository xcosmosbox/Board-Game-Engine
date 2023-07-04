package examples;

import examples.mvc.controller.board.impl.BoardImpl;
import examples.mvc.controller.game.impl.GameFacade;
import examples.mvc.controller.player.impl.AIPlayerImpl;
import examples.mvc.controller.player.impl.PlayerImpl;
import examples.mvc.controller.turn.impl.TurnImpl;
import examples.mvc.model.board.impl.BoardModelImpl;
import examples.mvc.model.board.trigger.impl.BoardMillTriggerImpl;
import examples.mvc.model.game.impl.GameModelImpl;
import examples.mvc.model.game.state.impl.GameStateImpl;
import examples.mvc.model.player.impl.AIModelImpl;
import examples.mvc.model.player.impl.PlayerModelImpl;
import examples.mvc.model.token.impl.TokenImpl;
import examples.mvc.view.factory.impl.GameHeaderViewFactory;
import examples.mvc.view.factory.impl.IntroViewFactory;
import examples.mvc.view.factory.impl.ShowRuleViewFactory;
import src.events.EventListener;
import src.events.EventSource;
import src.mvc.controller.board.Board;
import src.mvc.controller.game.Game;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Pollable;
import src.mvc.controller.turn.Turn;
import src.mvc.model.board.BoardModel;
import src.mvc.model.board.trigger.Trigger;
import src.mvc.model.game.GameModel;
import src.mvc.model.game.state.GameState;
import src.mvc.model.player.AIPlayerModel;
import src.mvc.model.player.PlayerModel;
import src.mvc.model.token.Token;
import src.mvc.view.View;
import src.utils.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author: fengyuxiang
 * @ClassName: Application
 * @version: 1.0
 * @description:
 * @create: 14/4/2023
 */
public class Application {

    /**
     * Initialize the classes that the entire game needs to use by assembling.
     * And inject some objects as dependencies into other objects.
     *
     * @param args
     */
    public static void main(String[] args) {
        // print introduce view page
        Display display = new Display();

        // Display the game title
        new GameHeaderViewFactory().createView().draw(display);

        // promote user input
        Scanner scanner = new Scanner(System.in);
        int selection;
        do {
            new IntroViewFactory().createView().draw(display);
            display.displayMessage("  Your selection: ");
            try {
                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        newGameWithHuman(); // game start
                        break;
                    case 2:
                        newGameWithAI();
                        break;
                    case 3:
                        showRule(); // show rule (Word Only)
                        break;
                    case 4:
                        display.displayMessage("  See you next time!"); // print exit message
                        break;
                    default:
                        display.displayMessage("  Invalid selection. Please try again." + display.getNewLine());
                        break;
                }
            } catch (InputMismatchException e) {
                display.displayMessage("  Invalid input. Please enter a valid integer." + display.getNewLine());
                scanner.nextLine(); // clean cache
                selection = 0; // continue loop
            }

        } while (selection != 4);
    }

    /**
     * assemble a new game with human
     */
    public static void newGameWithHuman() {
        // init the BoardModel
        BoardModel boardModel = new BoardModelImpl();
        Trigger trigger = new BoardMillTriggerImpl();

        ((BoardModelImpl) boardModel).addListener(trigger);

        // init board
        Board board = new BoardImpl(boardModel, trigger);

        // init the player-1 Model
        PlayerModel playerModel1 = new PlayerModelImpl("Player-1");
        // init the player-1 Controller
        Player player1 = new PlayerImpl(playerModel1);
        // init the 9 tokens for player-1
        Token[] tokens1 = new Token[9];
        for (int i = 0; i < tokens1.length; i++) {
            tokens1[i] = new TokenImpl(player1, '○');

        }
        // injection token into player-1 model
        playerModel1.setTokens(tokens1);
        // add the player-1 model to the event listener list of the BoardModel
        ((EventSource) board).addListener((EventListener) playerModel1);

        // init the player-2 mode
        PlayerModel playerModel2 = new PlayerModelImpl("Player-2");
        // init the player-2 Controller
        Player player2 = new PlayerImpl(playerModel2);
        // init the 9 tokens for player-2
        Token[] tokens2 = new Token[9];
        for (int i = 0; i < tokens2.length; i++) {
            tokens2[i] = new TokenImpl(player2, '●');

        }
        // injection token into player-2 model
        playerModel2.setTokens(tokens2);
        // add the player-2 model to the event listener list of the BoardModel
        ((EventSource) board).addListener((EventListener) playerModel2);

        // boardModel add listener
        ((BoardModelImpl) boardModel).addListener((EventListener) playerModel1);
        ((BoardModelImpl) boardModel).addListener((EventListener) playerModel2);

        // init Turn
        Turn turn = new TurnImpl();
        // add the player 1 & 2 into the pollable list
        turn.registerPollableObject((Pollable) player1);
        turn.registerPollableObject((Pollable) player2);

        // init Game State
        GameState gameState = new GameStateImpl();
        ((EventSource) playerModel1).addListener(gameState);
        ((EventSource) playerModel2).addListener(gameState);

        // init Game model
        GameModel gameModel = new GameModelImpl(gameState);
        // set players, turn and board into game model
        gameModel.setPlayers(player1, player2);
        gameModel.setTurn(turn);
        gameModel.setBoard(board);

        // init Game Facade
        Game game = new GameFacade();
        // inject GameModel into Game Facade
        game.build(gameModel);
        trigger.addListener((EventListener) game);
        // start game
        game.run();
    }

    /**
     * assemble a new game with AI
     */
    public static void newGameWithAI() {
        // init the BoardModel
        BoardModel boardModel = new BoardModelImpl();
        Trigger trigger = new BoardMillTriggerImpl();

        ((BoardModelImpl) boardModel).addListener(trigger);

        // init board
        Board board = new BoardImpl(boardModel, trigger);

        // init the player-1 Model
        PlayerModel playerModel1 = new PlayerModelImpl("Player-1");
        // init the player-1 Controller
        Player player1 = new PlayerImpl(playerModel1);
        // init the 9 tokens for player-1
        Token[] tokens1 = new Token[9];
        for (int i = 0; i < tokens1.length; i++) {
            tokens1[i] = new TokenImpl(player1, '○');

        }
        // injection token into player-1 model
        playerModel1.setTokens(tokens1);
        // add the player-1 model to the event listener list of the BoardModel
        ((EventSource) board).addListener((EventListener) playerModel1);

        // init the player-2 mode
        AIPlayerModel playerModel2 = new AIModelImpl("AI-Player", trigger);
        // init the player-2 Controller
        Player player2 = new AIPlayerImpl(playerModel2);
        // init the 9 tokens for player-2
        Token[] tokens2 = new Token[9];
        for (int i = 0; i < tokens2.length; i++) {
            tokens2[i] = new TokenImpl(player2, '●');

        }
        // injection token into player-2 model
        playerModel2.setTokens(tokens2);
        // add the player-2 model to the event listener list of the BoardModel
        ((EventSource) board).addListener((EventListener) playerModel2);

        // boardModel add listener
        ((BoardModelImpl) boardModel).addListener((EventListener) playerModel1);
        ((BoardModelImpl) boardModel).addListener((EventListener) playerModel2);

        // init Turn
        Turn turn = new TurnImpl();
        // add the player 1 & 2 into the pollable list
        turn.registerPollableObject((Pollable) player1);
        turn.registerPollableObject((Pollable) player2);

        // init Game State
        GameState gameState = new GameStateImpl();
        ((EventSource) playerModel1).addListener(gameState);
        ((EventSource) playerModel2).addListener(gameState);

        // init Game model
        GameModel gameModel = new GameModelImpl(gameState);
        // set players, turn and board into game model
        gameModel.setPlayers(player1, player2);
        gameModel.setTurn(turn);
        gameModel.setBoard(board);

        // init Game Facade
        Game game = new GameFacade();
        // inject GameModel into Game Facade
        game.build(gameModel);
        trigger.addListener((EventListener) game);
        // start game
        game.run();
    }

    /**
     * Print the game rule (Word only)
     */
    public static void showRule() {
        Display display = new Display();
        View showRuleWordOnly = new ShowRuleViewFactory().createView();
        showRuleWordOnly.draw(display);
        display.displayMessage(display.getNewLine() + "  Press enter key to continue..." + display.getNewLine());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
