package dentaira.shogi.ban;

import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.Forward;

public class ShogiBan {

    private Koma[][] field;

    public ShogiBan() {
        this.field = new Koma[9][9];
    }

    Koma getKoma(int x, int y) {
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
        var pickedKoma = getKoma(to);
        setKoma(moveKoma, to);
        setKoma(null, from);
        return pickedKoma;
    }

    public void render() {
        for (int x = 9; 0 < x; x--) {
            System.out.print(" ");
            System.out.print(Masu.getSujiSymbol(x));
            System.out.print("  ");
        }
        System.out.println();
        for (int y = 1; y <= 9; y++) {
            for (int x = 9; 1 <= x; x--) {
                System.out.print(" ");
                var koma = getKoma(x, y);
                System.out.print(koma == null ? "　 " : koma.getType().getAbbreviation() + getMovingDirectionIcon(koma));
                System.out.print(" ");
            }
            System.out.println(" " + Masu.getDanSymbol(y));
        }
    }

    private String getMovingDirectionIcon(Koma koma) {
        return koma.getForward() == Forward.LOWER ? "↑" : "↓";
    }
}
