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

    public List<Masu> getMovingCandidates(Masu position, ShogiBan shogiBan) {
        return type.getMovingStrategy().getMovingCandidates(position, forward, shogiBan);
    }

    public boolean canPromote() {
        if (type instanceof StandardKomaType t) {
            return t.canPromote();
        } else {
            return false;
        }
    }

    public void promote() {
        if (type instanceof StandardKomaType t) {
            this.type = t.promote();
        }
    }

    public void demote() {
        if (type instanceof PromotedKomaType t) {
            this.type = t.demote();
        }
    }
}
