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
 * @ClassName: MovingStrategy
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class MovingStrategy implements MoveStrategy {

    Coordinate from;
    Coordinate to;

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
            board.moveToken(from,to);
        } catch (InvalidCoordinate e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public void initDescription(Player player) {
        Display display = new Display();

        display.displayMessage("Please enter the coordinate of the token you want to move(separate two numbers with a comma): ");
        Integer[] firstInputs = player.requestIntegersInput();
        from = new CoordinateImpl(firstInputs[0],firstInputs[1]);

        display.displayMessage("Please enter the destination coordinate of the token(separate two numbers with a comma): ");
        Integer[] secondInputs = player.requestIntegersInput();
        to = new CoordinateImpl(secondInputs[0],secondInputs[1]);
    }
}
