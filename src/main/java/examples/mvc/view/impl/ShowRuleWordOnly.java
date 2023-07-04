package examples.mvc.view.impl;

import src.mvc.view.View;
import src.utils.Display;

import java.util.ResourceBundle;

/**
 * @author: fengyuxiang
 * @ClassName: ShowRuleWordOnly
 * @version: 1.0
 * @description:
 * @create: 26/4/2023
 */
public class ShowRuleWordOnly implements View {
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
        ResourceBundle wordOnlyGameRuleViewBundle = ResourceBundle.getBundle("WordOnlyGameRule");

        // read the number of lines
        int lines = Integer.parseInt(wordOnlyGameRuleViewBundle.getString("lines"));

        // read each lines
        for (int i = 0; i < lines; i++) {
            String key = String.valueOf(i);
            if (wordOnlyGameRuleViewBundle.containsKey(key)){
                String line = wordOnlyGameRuleViewBundle.getString(key);
                stringBuilder.append(line + display.getNewLine());
            }
        }

        String ruleViewString = stringBuilder.toString().replace("\"","");

        display.displayView(ruleViewString);
    }
}
