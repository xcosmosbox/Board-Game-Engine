package group.gan.mvc.view.impl;

import group.gan.mvc.view.View;
import group.gan.utils.Display;

public class MillPhaseView implements View {
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("  1: Choose Token\n");
        stringBuilder.append("  2: Quit Game\n");
        stringBuilder.append("  Your select(integer): ");

        display.displayView(stringBuilder.toString());
    }



}
