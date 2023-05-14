package group.gan;

import group.gan.events.EventListener;
import group.gan.events.EventSource;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.board.impl.BoardImpl;
import group.gan.mvc.controller.game.Game;
import group.gan.mvc.controller.game.impl.GameFacade;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.player.impl.PlayerImpl;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.controller.turn.Turn;
import group.gan.mvc.controller.turn.impl.TurnImpl;
import group.gan.mvc.model.board.BoardModel;
import group.gan.mvc.model.board.impl.BoardModelImpl;
import group.gan.mvc.model.board.trigger.Trigger;
import group.gan.mvc.model.board.trigger.impl.BoardMillTriggerImpl;
import group.gan.mvc.model.game.GameModel;
import group.gan.mvc.model.game.impl.GameModelImpl;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.impl.PlayerModelImpl;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;
import group.gan.mvc.view.View;
import group.gan.mvc.view.factory.impl.ShowRuleViewFactory;
import group.gan.mvc.view.impl.IntroduceView;
import group.gan.mvc.view.impl.ShowRuleWordOnly;
import group.gan.utils.Display;

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
     * @param args
     */
    public static void main(String[] args) {
        // print introduce view page
        Display display = new Display();
        View introView = new IntroduceView();

        // promote user input
        Scanner scanner = new Scanner(System.in);
        int selection;
        do {
            introView.draw(display);
            display.displayMessage("  Your selection: ");
            selection = scanner.nextInt();
            switch (selection){
                case 1:
                    newGame(); // game start
                    break;
                case 2:
                    showRule(); // show rule (Word Only)
                    break;
                case 3:
                    display.displayMessage("  See you next time!"); // print exit message
                    break;
            }
        } while (selection != 3);
    }

    /**
     * assemble a new game
     */
    public static void newGame(){
        // init the BoardModel
        BoardModel boardModel = new BoardModelImpl();
        Trigger trigger = new BoardMillTriggerImpl();

        ((BoardModelImpl)boardModel).addListener(trigger);

        // init board
        Board board = new BoardImpl(boardModel,trigger);

        // init the player-1 Model
        PlayerModel playerModel1 = new PlayerModelImpl("Player-1");
        // init the player-1 Controller
        Player player1 = new PlayerImpl(playerModel1);
        // init the 9 tokens for player-1
        Token[] tokens1 = new Token[9];
        for (int i = 0; i < tokens1.length; i++) {
            tokens1[i] = new TokenImpl(player1,'○');

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
            tokens2[i] = new TokenImpl(player2,'●');

        }
        // injection token into player-2 model
        playerModel2.setTokens(tokens2);
        // add the player-2 model to the event listener list of the BoardModel
        ((EventSource) board).addListener((EventListener) playerModel2);


        // init Turn
        Turn turn = new TurnImpl();
        // add the player 1 & 2 into the pollable list
        turn.registerPollableObject((Pollable) player1);
        turn.registerPollableObject((Pollable) player2);

        // init Game model
        GameModel gameModel = new GameModelImpl();
        // set players, turn and board into game model
        gameModel.setPlayers(player1,player2);
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
    public static void showRule(){
        Display display = new Display();
        View showRuleWordOnly = new ShowRuleViewFactory().createView();
        showRuleWordOnly.draw(display);
        display.displayMessage(display.getNewLine() + "  Press enter key to continue..." + display.getNewLine());
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
