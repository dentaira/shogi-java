package dentaira.shogi.ban;

import dentaira.shogi.koma.Koma;

import static dentaira.shogi.koma.StandardKomaType.*;

public class ShogiBan {

    private static String[] sujiArray = { "１", "２", "３", "４", "５", "６", "７", "８", "９" };

    private static String[] danArray = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    private Koma[][] field;

    private ShogiBan(Koma[][] field) {
        this.field = field;
    }

    public static ShogiBan create() {
        var field = new Koma[9][9];

        putKoma(field);

        return new ShogiBan(field);
    }

    private static void putKoma(Koma[][] field) {

        field[0][4] = new Koma(王将);
        field[8][4] = new Koma(玉将);

        field[1][2] = new Koma(飛車);
        field[7][6] = new Koma(飛車);

        field[1][6] = new Koma(角行);
        field[7][2] = new Koma(角行);

        field[0][3] = new Koma(金将);
        field[0][5] = new Koma(金将);
        field[8][3] = new Koma(金将);
        field[8][5] = new Koma(金将);

        field[0][2] = new Koma(銀将);
        field[0][6] = new Koma(銀将);
        field[8][2] = new Koma(銀将);
        field[8][6] = new Koma(銀将);

        field[0][1] = new Koma(桂馬);
        field[0][7] = new Koma(桂馬);
        field[8][1] = new Koma(桂馬);
        field[8][7] = new Koma(桂馬);

        field[0][0] = new Koma(香車);
        field[0][8] = new Koma(香車);
        field[8][0] = new Koma(香車);
        field[8][8] = new Koma(香車);

        put歩(field[2]);
        put歩(field[6]);
    }

    private static void put歩(Koma[] line) {
        for (int i = 0; i < line.length; i++) {
            line[i] = new Koma(歩兵);
        }
    }

    public void render() {
        for (int i = sujiArray.length; 0 < i; i--) {
            System.out.print(" ");
            System.out.print(sujiArray[i - 1]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" ");
                System.out.print(field[i][j] == null ? '　' : field[i][j].getType().getAbbreviation());
                System.out.print(" ");
            }
            System.out.println(danArray[i]);
        }
    }
}
