package examples.mvc.view.factory.impl;

import examples.mvc.view.impl.BoardView;
import src.mvc.model.position.Position;
import src.mvc.view.View;
import src.mvc.view.factory.ViewFactory;
/**
 * @author: fengyuxiang
 * @ClassName: BoardViewFactory
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class BoardViewFactory implements ViewFactory {

    private BoardView boardView;

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
