package group.gan.mvc.view.impl;

import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.view.View;
import group.gan.utils.Display;

import java.util.ResourceBundle;

/**
 * @author: fengyuxiang
 * @ClassName: WinView
 * @version: 1.0
 * @description:
 * @create: 15/5/2023
 */
public class WinView implements View {

    private Pollable winner;

    public WinView(Pollable winner){
        this.winner =  winner;
    }

    /**
     * Drawing methods implemented by subclasses
     *
     * @param display
     */
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();

        ResourceBundle winPhaseViewBundle = ResourceBundle.getBundle("WinPhaseView");
        int lines = Integer.parseInt(winPhaseViewBundle.getString("lines"));

        // read config
        for (int i = 0; i < lines; i++) {
            String key = String.valueOf(i);
            if (winPhaseViewBundle.containsKey(key)) {
                String line = winPhaseViewBundle.getString(key);
                stringBuilder.append("  " + line + "\n");
            }
        }

        stringBuilder.append("  ********************************************************************************\n");
        stringBuilder.append("                            Congratulations You Win!\n");
        stringBuilder.append("                       Winner: "+winner+"\n");
        stringBuilder.append("  ********************************************************************************\n");

        display.displayView(stringBuilder.toString());
    }
}
