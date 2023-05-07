package group.gan.mvc.controller.command.impl;

import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.game.Game;
import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.player.Player;

/**
 * @author: fengyuxiang
 * @ClassName: MillCommand
 * @version: 1.0
 * @description:
 * @create: 8/5/2023
 */
public class MillCommand implements Command {

    private CommandType commandType = CommandType.MILL;
    private Game game;
    private Player player;
    private Board board;
    private MoveStrategy moveStrategy;

    /**
     * A set of functions with execution
     *
     * @param player
     * @param board
     * @return
     */
    @Override
    public Boolean execute(Player player, Board board) {
        return moveStrategy.executeStrategy(player, board);
    }

    /**
     * A set of functions with execution
     *
     * @return
     */
    @Override
    public Boolean execute() {
        return moveStrategy.executeStrategy(player, board);
    }

    /**
     * A set of functions with initialization
     *
     * @param game
     */
    @Override
    public void init(Game game) {
        this.game = game;
    }

    /**
     * A set of functions with initialization
     *
     * @param player
     */
    @Override
    public void init(Player player) {
        this.player = player;
    }

    /**
     * A set of functions with initialization
     *
     * @param board
     */
    @Override
    public void init(Board board) {
        this.board = board;
    }

    /**
     * A set of functions with initialization
     *
     * @param game
     * @param player
     */
    @Override
    public void init(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    /**
     * A set of functions with initialization
     *
     * @param board
     * @param player
     */
    @Override
    public void init(Board board, Player player) {
        this.player = player;
        this.board = board;
    }

    /**
     * A set of functions with initialization
     *
     * @param game
     * @param board
     */
    @Override
    public void init(Game game, Board board) {
        this.game = game;
        this.board = board;
    }

    /**
     * A set of functions with initialization
     *
     * @param game
     * @param player
     * @param board
     */
    @Override
    public void init(Game game, Player player, Board board) {
        this.game = game;
        this.player = player;
        this.board = board;
    }

    /**
     * What type of Command is used to return at this time
     *
     * @return
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * initialize move strategy
     *
     * @param moveStrategy
     */
    @Override
    public void initMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
}
