package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.player.Player;

public class CommandLineShogiRenderer {

    private ShogiBan shogiBan;

    private Player sentePlayer;

    private Player gotePlayer;

    public CommandLineShogiRenderer(ShogiBan shogiBan, Player sentePlayer, Player gotePlayer) {
        this.shogiBan = shogiBan;
        this.sentePlayer = sentePlayer;
        this.gotePlayer = gotePlayer;
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
                var koma = shogiBan.getKoma(x, y);
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
