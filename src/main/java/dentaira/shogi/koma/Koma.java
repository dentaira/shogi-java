package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;

import java.util.ArrayList;
import java.util.List;

public class Koma {

    private KomaType type;

    private MovingDirection movingDirection;

    public Koma(KomaType type, MovingDirection movingDirection) {
        this.type = type;
        this.movingDirection = movingDirection;
    }

    public KomaType getType() {
        return type;
    }

    public MovingDirection getMovingDirection() {
        return movingDirection;
    }

    public List<Masu> getMovingCandidate(Masu placed) {
        var list = new ArrayList<Masu>();
        for (var movingDistance : getType().getMovingDistances()) {
            var candidate = placed.shift(movingDistance.x(), movingDistance.y());
            candidate.ifPresent(list::add);
        }
        return list;
    }
}
