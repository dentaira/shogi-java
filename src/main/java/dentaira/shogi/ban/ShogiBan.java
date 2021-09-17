package dentaira.shogi.ban;

import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.KomaType;

import static dentaira.shogi.koma.StandardKomaType.*;

public class ShogiBan {

    private static String[] sujiArray = { "１", "２", "３", "４", "５", "６", "７", "８", "９" };

    private static String[] danArray = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    private Koma[][] field;

    public ShogiBan() {
        this.field = new Koma[9][9];
        initField();
    }

    private void initField() {

        setKoma(new Koma(王将), 5, 1);
        setKoma(new Koma(玉将), 5, 9);

        setKoma(new Koma(飛車), 8, 2);
        setKoma(new Koma(飛車), 2, 8);

        setKoma(new Koma(角行), 2, 2);
        setKoma(new Koma(角行), 8, 8);

        setKoma(new Koma(金将), 6, 1);
        setKoma(new Koma(金将), 4, 1);
        setKoma(new Koma(金将), 6, 9);
        setKoma(new Koma(金将), 4, 9);

        setKoma(new Koma(銀将), 7, 1);
        setKoma(new Koma(銀将), 3, 1);
        setKoma(new Koma(銀将), 7, 9);
        setKoma(new Koma(銀将), 3, 9);

        setKoma(new Koma(桂馬), 2, 1);
        setKoma(new Koma(桂馬), 8, 1);
        setKoma(new Koma(桂馬), 2, 9);
        setKoma(new Koma(桂馬), 8, 9);

        setKoma(new Koma(香車), 1, 1);
        setKoma(new Koma(香車), 9, 1);
        setKoma(new Koma(香車), 1, 9);
        setKoma(new Koma(香車), 9, 9);

        setHorizontalKoma(歩兵, 3);
        setHorizontalKoma(歩兵, 7);
    }

    public Koma getKoma(int x, int y) {
        return field[y - 1][x - 1];
    }

    private void setKoma(Koma koma, int x, int y) {
        field[y - 1][x - 1] = koma;
    }

    private void setHorizontalKoma(KomaType komaType, int y) {
        Koma[] horizontal = field[y - 1];
        for (int i = 0; i < horizontal.length; i++) {
            horizontal[i] = new Koma(komaType);
        }
    }
    public void render() {
        for (int x = sujiArray.length; 0 < x; x--) {
            System.out.print(" ");
            System.out.print(sujiArray[x - 1]);
            System.out.print(" ");
        }
        System.out.println();
        for (int y = 1; y <= 9; y++) {
            for (int x = 9; 1 <= x; x--) {
                System.out.print(" ");
                var koma = getKoma(x, y);
                System.out.print(koma == null ? '　' : koma.getType().getAbbreviation());
                System.out.print(" ");
            }
            System.out.println(danArray[y - 1]);
        }
    }
}
