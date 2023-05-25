package group.gan.mvc.model.player;

import group.gan.mvc.model.coordinate.Coordinate;
import group.gan.mvc.model.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AIPlayerModel extends PlayerModel{
    public Position[] getPositionsCache();
    public List<Coordinate> getPlaceableOptions();
    public List<Coordinate[]> getMovableOptions();
    public List<Coordinate[]> getFlyableOptions();
    public List<Coordinate> getRemovableOptions();
//    public Map<Integer, Set<Integer>> getValidMovesMap();
//    public Map<Integer, Coordinate> getPositionCoordinateMapping();
//    public Map<Integer, List<Integer>> getTriggerNodeMap();

}
