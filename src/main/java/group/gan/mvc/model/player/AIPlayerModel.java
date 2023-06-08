package group.gan.mvc.model.player;

import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.position.Position;

import java.util.List;

/**
 * An interface that generates valid coordinates in a list for computer player
 */
public interface AIPlayerModel extends PlayerModel {
    /**
     * a getter for positionCache
     *
     * @return returns positionCache
     */
    public Position[] getPositionsCache();

    /**
     * @return list of valid coordinates for Placing
     */
    public List<Coordinate> getPlaceableOptions();

    /**
     * @return list of valid coordinates for Moving
     */
    public List<Coordinate[]> getMovableOptions();

    /**
     * @return list of valid coordinates for Flying
     */
    public List<Coordinate[]> getFlyableOptions();

    /**
     * @return list of valid coordinates for Killing a token after Mill
     */
    public List<Coordinate> getRemovableOptions();
//    public Map<Integer, Set<Integer>> getValidMovesMap();
//    public Map<Integer, Coordinate> getPositionCoordinateMapping();
//    public Map<Integer, List<Integer>> getTriggerNodeMap();
}
