package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;

import java.util.ArrayList;
import java.util.List;

public class Koma {

    private KomaType type;

    private Forward forward;

    public Koma(KomaType type, Forward forward) {
        this.type = type;
        this.forward = forward;
    }

    public KomaType getType() {
        return type;
    }

    public Forward getForward() {
        return forward;
    }

    public List<Masu> getMovingCandidate(Masu placed) {
        var list = new ArrayList<Masu>();
        for (var movingDistance : getType().getMovingDistances()) {
            var candidate = placed.shift(movingDistance.x(), movingDistance.y(), forward);
            candidate.ifPresent(list::add);
        }
        return list;
    }
}
