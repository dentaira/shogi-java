package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandLineShogiRenderer {

    private static final int VERTICAL_SIZE = 10;

    private static final int CHUNK_SIZE = 6;

    private ShogiBan shogiBan;

    private Player sentePlayer;

    private Player gotePlayer;

    public CommandLineShogiRenderer(ShogiBan shogiBan, Player sentePlayer, Player gotePlayer) {
        this.shogiBan = shogiBan;
        this.sentePlayer = sentePlayer;
        this.gotePlayer = gotePlayer;
    }

    public void render() {
        List<String> goteKomadaiLine = createGoteKomadaiLine();
        List<String> senteKomadaiLine = createSenteKomadaiLine();
        List<String> shogiBanLine = createShogiBanLine();

        for (int i = 0; i < VERTICAL_SIZE; i++) {
            System.out.print(goteKomadaiLine.get(i));
            System.out.print(shogiBanLine.get(i));
            System.out.print("  ");
            System.out.println(senteKomadaiLine.get(i));
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

    List<String> createSenteKomadaiLine() {
        var lines = new ArrayList<String>();
        ArrayList<Koma[]> chunks = devideKomas(sentePlayer.getTookKomas());

        for (int i = VERTICAL_SIZE - 1; i >= 0; i--) {
            String line = "";
            for (int j = 0; j < chunks.size(); j++) {
                var koma = chunks.get(j)[i];
                if (koma == null) {
                    line += "　 ";
                } else {
                    line += koma.getType().getAbbreviation() + getMovingDirectionIcon(koma);
                }
                line += " ";
            }
            lines.add(line);
        }

        return lines;
    }

    List<String> createGoteKomadaiLine() {
        var lines = new ArrayList<String>();
        ArrayList<Koma[]> chunks = devideKomas(gotePlayer.getTookKomas());

        for (int i = 0; i < VERTICAL_SIZE; i++) {
            String line = "";
            for (int j = chunks.size() - 1; j >= 0; j--) {
                var koma = chunks.get(j)[i];
                if (koma == null) {
                    line += "　 ";
                } else {
                    line += koma.getType().getAbbreviation() + getMovingDirectionIcon(koma);
                }
                line += " ";
            }
            lines.add(line);
        }

        return lines;
    }

    private ArrayList<Koma[]> devideKomas(List<Koma> tookKomas) {
        var chunks = new ArrayList<Koma[]>();
        for (int i = 0; i < tookKomas.size(); i += CHUNK_SIZE) {
            var chunk = tookKomas.subList(i, Math.min(i + CHUNK_SIZE, tookKomas.size()))
                    .toArray(new Koma[VERTICAL_SIZE]);
            chunks.add(chunk);
        }
        return chunks;
    }

    private String getMovingDirectionIcon(Koma koma) {
        return koma.getForward() == Forward.LOWER ? "↑" : "↓";
    }
}
