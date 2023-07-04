package examples.mvc.view.impl;

import java.util.ResourceBundle;
import src.mvc.view.View;
import src.utils.Display;
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
        ResourceBundle introduceViewBundle = ResourceBundle.getBundle("IntroduceView");

        // read the number of lines
        int lines = Integer.parseInt(introduceViewBundle.getString("lines"));

        // read each lines
        for (int i = 0; i < lines; i++) {
            String key = String.valueOf(i);
            if (introduceViewBundle.containsKey(key)){
                String line = introduceViewBundle.getString(key);
                stringBuilder.append(line + display.getNewLine());
            }
        }

        String introduceViewString = stringBuilder.toString().replace("\"","");

        display.displayView(introduceViewString);
    }
}
