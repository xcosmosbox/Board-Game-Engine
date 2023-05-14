package group.gan.mvc.controller.move.impl;

import group.gan.exception.InvalidCoordinate;
import group.gan.exception.InvalidTokenChoose;
import group.gan.mvc.controller.board.Board;
import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.coordinate.impl.CoordinateImpl;
import group.gan.utils.Display;

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
