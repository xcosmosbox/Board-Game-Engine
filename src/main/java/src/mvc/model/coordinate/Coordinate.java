package src.mvc.model.coordinate;

/**
 * A wrapper class for encapsulating coordinate information
 */
public interface Coordinate {
    /**
     * Getter
     * @return integer value for x
     */
    Integer getX();

    /**
     * Getter
     * @return integer value for y
     */
    Integer getY();

    /**
     * Setter for x
     * @param x
     */
    void setX(Integer x);

    /**
     * Setter for y
     * @param y
     */
    void setY(Integer y);

}
