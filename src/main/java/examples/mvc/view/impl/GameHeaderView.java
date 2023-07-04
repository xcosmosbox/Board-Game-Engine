package examples.mvc.view.impl;

import java.util.ResourceBundle;
import src.mvc.view.View;
import src.utils.Display;
/**
 * @author: fengyuxiang
 * @ClassName: GameHeaderTitleView
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class GameHeaderView implements View {
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
        ResourceBundle gameHeaderViewBundle = ResourceBundle.getBundle("GameHeaderConfig");

        // read the number of lines
        int lines = Integer.parseInt(gameHeaderViewBundle.getString("lines"));

        // read each lines
        for (int i = 0; i < lines; i++) {
            String key = String.valueOf(i);
            if (gameHeaderViewBundle.containsKey(key)){
                String line = gameHeaderViewBundle.getString(key);
                stringBuilder.append("  " + line + display.getNewLine());
            }
        }


        display.displayView(stringBuilder.toString());
    }
}
