package dentaira.shogi.run;

import dentaira.shogi.ban.ShogiBan;

public class CommandLineShogiRunner {

    public static void main(String[] args) {
        var shogiBan = new ShogiBan();
        shogiBan.render();
    }
}
