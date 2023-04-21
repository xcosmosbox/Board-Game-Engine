package group.gan.mvc.view.impl;

import group.gan.mvc.view.View;
import group.gan.utils.Display;

import java.util.ResourceBundle;

/**
 * @author: fengyuxiang
 * @ClassName: IntroduceView
 * @version: 1.0
 * @description:
 * @create: 21/4/2023
 */
public class IntroduceView implements View {
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
        ResourceBundle boardViewBundle = ResourceBundle.getBundle("IntroduceView");

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

        String introduceViewString = stringBuilder.toString().replace("\"","");

        display.displayView(introduceViewString);
    }
}
