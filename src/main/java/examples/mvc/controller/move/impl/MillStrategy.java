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
 * @ClassName: MillStrategy
 * @version: 1.0
 * @description:
 * @create: 14/5/2023
 */
public class MillStrategy implements MoveStrategy {

    /**
     * Coordinate for target who killed token
     */
    private Coordinate target;


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
            if(!board.getOnePositionFromBoard(target).isEmpty()){
                if (board.getOnePositionFromBoard(target).peekToken().getOwner() == player){
                    throw new InvalidTokenChoose("  You cannot choose you own token!");
                }
            } else {
              throw new InvalidTokenChoose("  No Token at this position!");
            }

            board.removeToken(player, target);

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

        display.displayMessage("  Please enter the coordinate of the token you want to kill (separate two numbers with a comma): ");
        Integer[] coordinate = player.requestIntegersInput();
        target = new CoordinateImpl(coordinate[0],coordinate[1]);


    }
}
