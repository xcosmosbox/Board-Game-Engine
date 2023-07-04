package examples.mvc.model.coordinate.impl;

import src.mvc.model.coordinate.Coordinate;

import java.util.Objects;

/**
 * @author: fengyuxiang
 * @ClassName: CoordinateImpl
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class CoordinateImpl implements Coordinate {

    /**
     * store x value
     */
    private Integer x;
    /**
     * store y value
     */
    private Integer y;

    public CoordinateImpl(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter
     *
     * @return integer value for x
     */
    @Override
    public Integer getX() {
        return x;
    }

    /**
     * Getter
     *
     * @return integer value for y
     */
    @Override
    public Integer getY() {
        return y;
    }

    /**
     * Setter for x
     *
     * @param x
     */
    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Setter for y
     *
     * @param y
     */
    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateImpl that = (CoordinateImpl) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
