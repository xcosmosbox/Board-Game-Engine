package examples.mvc.controller.move.impl;

import examples.mvc.model.coordinate.impl.CoordinateImpl;
import src.exception.InvalidCoordinate;
import src.exception.InvalidTokenChoose;
import src.mvc.controller.board.Board;
import src.mvc.controller.move.MoveStrategy;
import src.mvc.controller.player.Player;
import src.mvc.model.coordinate.Coordinate;
import src.utils.Display;

/**
 * @author: fengyuxiang
 * @ClassName: FlyingStrategy
 * @version: 1.0
 * @description:
 * @create: 8/5/2023
 */
public class FlyingStrategy implements MoveStrategy {

    /**
     * Coordinate for from
     */
    private Coordinate from;
    /**
     * Coordinate for to
     */
    private Coordinate to;

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
            if (!board.getOnePositionFromBoard(from).isEmpty()){
                if(board.getOnePositionFromBoard(from).peekToken().getOwner() != player){
                    throw new InvalidTokenChoose("  This is not your token!");
                }
            }
            board.flyToken(from,to);
        } catch (InvalidTokenChoose e) {
            System.out.println(e);
            return false;
        } catch (InvalidCoordinate e) {
            System.out.println(e);
            return false;
        }


        return true;
    }

    @Override
    public void initDescription(Player player) {
        Display display = new Display();

        display.displayMessage("  Please enter the coordinate of the token you want to fly(separate two numbers with a comma): ");
        Integer[] firstInputs = player.requestIntegersInput();
        from = new CoordinateImpl(firstInputs[0],firstInputs[1]);

        display.displayMessage("  Please enter the destination coordinate of the token(separate two numbers with a comma): ");
        Integer[] secondInputs = player.requestIntegersInput();
        to = new CoordinateImpl(secondInputs[0],secondInputs[1]);
    }
}
