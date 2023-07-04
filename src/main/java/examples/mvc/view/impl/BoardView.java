package examples.mvc.view.impl;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import src.mvc.model.position.Position;
import src.mvc.view.View;
import src.utils.Display;
/**
 * @author: fengyuxiang
 * @ClassName: BoardView
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class BoardView implements View {

    private static final String EMPTY_SLOT = "*";
    private String[] colors;

    public BoardView(Position[] positions){
        this.colors = new String[positions.length];
        for (int i = 0; i < positions.length; i++) {
            // init color
            colors[i] = positions[i].isEmpty() ? EMPTY_SLOT : String.valueOf(positions[i].peekToken().getSymbol());
        }
    }


    /**
     * Drawing methods implemented by subclasses
     *
     * @param display
     */
    @Override
    public void draw(Display display) {

        // build StringBuilder
        StringBuilder stringBuilder = new StringBuilder();

        // load resource file
        ResourceBundle boardViewBundle = ResourceBundle.getBundle("BoardView");

        // read the number of lines
        int lines = Integer.parseInt(boardViewBundle.getString("lines"));

        // read each lines
        for (int i = 0; i < lines; i++) {
            String key = String.valueOf(i);
            if (boardViewBundle.containsKey(key)){
                String line = boardViewBundle.getString(key);
                stringBuilder.append(line + display.getNewLine());
            }
        }

        String boardViewString = MessageFormat.format(stringBuilder.toString().replace("\"",""),colors);

        display.displayView(boardViewString);
    }
}
