package group.gan.mvc.view.factory;

import group.gan.mvc.view.View;

/**
 * A View factory interface for implementing the factory pattern
 */
public interface ViewFactory {
    /**
     * A function for generating View
     * @return The specific implementation class of View
     */
    View createView();
}
