package group.gan.mvc.view.impl;

import group.gan.events.EventListener;
import group.gan.events.EventSource;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.board.impl.BoardImpl;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.player.impl.PlayerImpl;
import group.gan.mvc.model.board.BoardModel;
import group.gan.mvc.model.board.impl.BoardModelImpl;
import group.gan.mvc.model.board.trigger.Trigger;
import group.gan.mvc.model.board.trigger.impl.BoardMillTriggerImpl;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.impl.PlayerModelImpl;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;
import group.gan.mvc.view.factory.impl.WinViewFactory;
import group.gan.utils.Display;
import org.junit.Test;

import static org.junit.Assert.*;

public class WinViewTest {

    @Test
    public void draw() {
        // init the BoardModel
        BoardModel boardModel = new BoardModelImpl();
        Trigger trigger = new BoardMillTriggerImpl();

        ((BoardModelImpl)boardModel).addListener(trigger);

        // init board
        Board board = new BoardImpl(boardModel,trigger);

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
        Display display = new Display();
        new WinViewFactory(player2).createView().draw(display);
    }
}