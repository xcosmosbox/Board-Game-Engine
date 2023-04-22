package group.gan.mvc.controller.command;

import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.game.Game;
import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.player.Player;

/**
 * Classes for implementing the command design pattern
 */
public interface Command {
    /**
     * A set of functions with execution
     * @param player
     * @param board
     * @return
     */
    Boolean execute(Player player, Board board);

    /**
     * A set of functions with execution
     * @return
     */
    Boolean execute();

    /**
     * A set of functions with initialization
     * @param game
     */
    void init(Game game);

    /**
     * A set of functions with initialization
     * @param player
     */
    void init(Player player);

    /**
     * A set of functions with initialization
     * @param board
     */
    void init(Board board);

    /**
     * A set of functions with initialization
     * @param game
     * @param player
     */
    void init(Game game, Player player);

    /**
     * A set of functions with initialization
     * @param board
     * @param player
     */
    void init(Board board, Player player);

    /**
     * A set of functions with initialization
     * @param game
     * @param board
     */
    void init(Game game, Board board);

    /**
     * A set of functions with initialization
     * @param game
     * @param player
     * @param board
     */
    void init(Game game, Player player, Board board);

    /**
     * What type of Command is used to return at this time
     * @return
     */
    CommandType getCommandType();

    /**
     * initialize move strategy
     * @param moveStrategy
     */
    void initMoveStrategy(MoveStrategy moveStrategy);
}
