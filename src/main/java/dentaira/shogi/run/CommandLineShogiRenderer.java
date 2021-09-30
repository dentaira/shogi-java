package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.player.Player;

import java.util.ArrayList;
import java.util.List;

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
        List<String> goteTookKomaLine = createGotePlayerTookKomaLine();
        List<String> senteTookKomaLine = createSentePlayerTookKomaLine();
        List<String> shogiBanLine = createShogiBanLine();

        for (int i = 0; i < 10; i++) {
            System.out.print(goteTookKomaLine.get(i));
            System.out.print(shogiBanLine.get(i));
            System.out.print("  ");
            System.out.println(senteTookKomaLine.get(i));
        }

    }

    List<String> createShogiBanLine() {
        var lines = new ArrayList<String>();
        var fsb = new StringBuilder();
        for (int x = 9; 0 < x; x--) {
            fsb.append(" ");
            fsb.append(Masu.getSujiSymbol(x));
            fsb.append("  ");
        }
        fsb.append("　");
        lines.add(fsb.toString());

        for (int y = 1; y <= 9; y++) {
            var ssb = new StringBuilder();
            for (int x = 9; 1 <= x; x--) {
                ssb.append(" ");
                var koma = shogiBan.getKoma(x, y);
                ssb.append(koma == null ? "　 " : koma.getType().getAbbreviation() + getMovingDirectionIcon(koma));
                ssb.append(" ");
            }
            lines.add(ssb + " " + Masu.getDanSymbol(y));
        }

        return lines;
    }

    List<String> createSentePlayerTookKomaLine() {
        var lines = new ArrayList<String>();
        var tookKomas = sentePlayer.getTookKomas();
        int chunkSize = 10;
        var chunked = new ArrayList<List<Koma>>();
        for (int i = 0; i < tookKomas.size(); i += chunkSize) {
            List<Koma> chunk = new ArrayList<>(tookKomas.subList(i, Math.min(i + chunkSize, tookKomas.size())));
            chunked.add(chunk);
        }
        for (int i = 9; i >= 0; i--) {
            String line = "";
            for (var chunk : chunked) {
                if (chunk.size() > i) {
                    var koma = chunk.get(i);
                    line += koma.getType().getAbbreviation() + getMovingDirectionIcon(koma);
                } else {
                    line += "　 ";
                }
                line += " ";
            }
            lines.add(line);
        }
        return lines;
    }

    List<String> createGotePlayerTookKomaLine() {
        var lines = new ArrayList<String>();
        var tookKomas = gotePlayer.getTookKomas();
        int chunkSize = 10;
        var chunked = new ArrayList<List<Koma>>();
        for (int i = 0; i < tookKomas.size(); i += chunkSize) {
            List<Koma> chunk = new ArrayList<>(tookKomas.subList(i, Math.min(i + chunkSize, tookKomas.size())));
            chunked.add(chunk);
        }
        for (int i = 0; i < 10; i++) {
            String line = "";
            for (var chunk : chunked) {
                if (chunk.size() > i) {
                    var koma = chunk.get(i);
                    line += koma.getType().getAbbreviation() + getMovingDirectionIcon(koma);
                } else {
                    line += "　 ";
                }
                line += " ";
            }
            lines.add(line);
        }
        return lines;
    }

    private String getMovingDirectionIcon(Koma koma) {
        return koma.getForward() == Forward.LOWER ? "↑" : "↓";
    }
}
