package group.gan.mvc.controller.move.impl;

import group.gan.exception.InvalidCoordinate;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.coordinate.impl.CoordinateImpl;
import group.gan.utils.Display;

/**
 * @author: fengyuxiang
 * @ClassName: PlacingStrategy
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class PlacingStrategy implements MoveStrategy {

    Coordinate coordinate;
    Player player;

    /**
     * Run a specific strategy
     *
     * @param player one player object reference
     * @param board  one board object reference
     * @return Criteria for successful operation
     */
    @Override
    public Boolean executeStrategy(Player player, Board board) {
        try {
            board.placeToken(this.player.getOneToken(), coordinate);
        } catch (InvalidCoordinate e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public void initDescription(Player player) {
        this.player = player;
        Display display = new Display();
        display.displayMessage("  Please enter the Coordinate where you want to place the token (separate two numbers with a comma): ");
        Integer[] inputs = player.requestIntegersInput();
        coordinate = new CoordinateImpl(inputs[0],inputs[1]);
    }
}
