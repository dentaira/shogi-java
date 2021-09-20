package dentaira.shogi.koma;

import java.util.List;

public interface KomaType {

    String name();

    String getAbbreviation();

    List<MovingDistance> getMovingDistances();
}
