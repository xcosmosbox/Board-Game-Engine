package group.gan.mvc.controller.move;

import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.player.Player;

/**
 * Interface for implementing the strategy pattern
 */
public interface MoveStrategy {
    /**
     * Run a specific strategy
     * @param player one player object reference
     * @param board one board object reference
     * @return Criteria for successful operation
     */
    Boolean executeStrategy(Player player, Board board);

    void initDescription(Player player);
}
