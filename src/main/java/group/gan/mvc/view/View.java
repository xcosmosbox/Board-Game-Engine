package group.gan.mvc.view;

import group.gan.utils.Display;

/**
 * An interface for displaying view information
 */
public interface View {
    /**
     * Drawing methods implemented by subclasses
     * @param display
     */
    void draw(Display display);

}
