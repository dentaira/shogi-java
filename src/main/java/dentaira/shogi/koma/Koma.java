package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class Koma {

    private KomaType type;

    private Forward forward;

    private BiFunction<Masu, MovingDistance, Optional<Masu>> moveFunc;

    public Koma(KomaType type, Forward forward) {
        this.type = type;
        this.forward = forward;
        this.moveFunc = switch (forward) {
            case LOWER -> (m, d) -> m.shift(-d.x(), -d.y());
            case HIGHER -> (m, d) -> m.shift(d.x(), d.y());
        };
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
            var candidate = moveFunc.apply(placed, movingDistance);
            candidate.ifPresent(list::add);
        }
        return list;
    }
}
