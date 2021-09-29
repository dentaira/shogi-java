package dentaira.shogi.ban;

import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.StandardKomaType;

import java.util.Objects;
import java.util.stream.IntStream;

public class ShogiBan {

    private Koma[][] field;

    public ShogiBan() {
        this.field = new Koma[9][9];
    }

    public Koma getKoma(int x, int y) {
        return field[y - 1][x - 1];
    }

    public Koma getKoma(Masu masu) {
        return getKoma(masu.x(), masu.y());
    }

    void setKoma(Koma koma, int x, int y) {
        field[y - 1][x - 1] = koma;
    }

    public void setKoma(Koma koma, Masu masu) {
        setKoma(koma, masu.x(), masu.y());
    }

    public Koma moveKoma(Masu from, Masu to) {
        var moveKoma = getKoma(from);
        var tookKoma = getKoma(to);
        setKoma(moveKoma, to);
        setKoma(null, from);
        return tookKoma;
    }

    public boolean isEnemyTerritory(Masu masu, Forward forward) {
        return switch (forward) {
            case LOWER -> masu.y() < 4;
            case HIGHER -> masu.y() > 6;
        };
    }

    public boolean has歩(int suji, Forward forward) {
        int index = suji - 1;
        return IntStream.range(0, 9)
                .mapToObj(i -> field[i][index])
                .filter(Objects::nonNull)
                .anyMatch(k -> k.getType() == StandardKomaType.歩兵 && k.getForward() == forward);
    }
}
