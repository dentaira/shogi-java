package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;

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

    public List<Masu> getMovingCandidates(Masu placed, ShogiBan shogiBan) {
        var strategy = type.getMovingStrategy();
        return strategy.getMovingCandidates(placed, forward, shogiBan);
    }
}
