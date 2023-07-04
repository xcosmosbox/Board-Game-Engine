package examples.mvc.view.impl;
import src.mvc.view.View;
import src.utils.Display;
public class MillPhaseView implements View {
    @Override
    public void draw(Display display) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("       Congratulations You Formed A Mill!\n");
        stringBuilder.append("  ********************************************\n");
        stringBuilder.append("  1: Choose Token To Kill\n");
        stringBuilder.append("  2: Quit Game\n");
        stringBuilder.append("  Your select(integer): ");

        display.displayView(stringBuilder.toString());
    }



}
