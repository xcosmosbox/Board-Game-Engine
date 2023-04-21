package group.gan.mvc.view.factory.impl;

import group.gan.mvc.model.position.Position;
import group.gan.mvc.view.factory.ViewFactory;
import group.gan.mvc.view.impl.BoardView;
import group.gan.mvc.view.View;

/**
 * @author: fengyuxiang
 * @ClassName: BoardViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class BoardViewFactory implements ViewFactory {

    BoardView boardView;

    public BoardViewFactory(Position[] positions){
        boardView = new BoardView(positions);
    }

    /**
     * A function for generating View
     *
     * @return The specific implementation class of View
     */
    @Override
    public View createView() {
        return boardView;
    }
}
