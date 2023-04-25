package group.gan;

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
import group.gan.mvc.model.game.GameModel;
import group.gan.mvc.model.game.impl.GameModelImpl;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.impl.PlayerModelImpl;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;

/**
 * @author: fengyuxiang
 * @ClassName: Application
 * @version: 1.0
 * @description:
 * @create: 14/4/2023
 */
public class Application {
    public static void main(String[] args) {
        BoardModel boardModel = new BoardModelImpl();
        Board board = new BoardImpl(boardModel);

        PlayerModel playerModel1 = new PlayerModelImpl("Player-1");
        Player player1 = new PlayerImpl(playerModel1);
        Token[] tokens1 = new Token[9];
        for (int i = 0; i < tokens1.length; i++) {
            tokens1[i] = new TokenImpl(player1,'○');
        }
        playerModel1.setTokens(tokens1);

        PlayerModel playerModel2 = new PlayerModelImpl("Player-2");
        Player player2 = new PlayerImpl(playerModel2);
        Token[] tokens2 = new Token[9];
        for (int i = 0; i < tokens2.length; i++) {
            tokens2[i] = new TokenImpl(player2,'●');
        }
        playerModel2.setTokens(tokens2);


        Turn turn = new TurnImpl();
        turn.registerPollableObject((Pollable) player1);
        turn.registerPollableObject((Pollable) player2);

        GameModel gameModel = new GameModelImpl();
        gameModel.setPlayers(player1,player2);
        gameModel.setTurn(turn);
        gameModel.setBoard(board);

        Game game = new GameFacade();
        game.build(gameModel);

        game.run();
    }
}
