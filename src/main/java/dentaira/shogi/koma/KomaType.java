package dentaira.shogi.koma;

public interface KomaType {

    String name();

    String getAbbreviation();

    MovingStrategy getMovingStrategy();
}
