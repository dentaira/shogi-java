package dentaira.shogi.player;

import dentaira.shogi.ban.InitialKomaPositions;
import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.KomaType;
import dentaira.shogi.koma.StandardKomaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;

    private PlayOrder playOrder;

    private List<Koma> aliveKomas;

    private List<Koma> tookKomas;

    public Player(String name, PlayOrder playOrder) {
        this.name = name;
        this.playOrder = playOrder;
        this.aliveKomas = new ArrayList<>();
        this.tookKomas = new ArrayList<>();
    }

    public void setUp(ShogiBan shogiBan) {
        var forward = playOrder.getForward();
        for (Map.Entry<Masu, KomaType> entry : InitialKomaPositions.getPositions(playOrder).entrySet()) {
            var koma = new Koma(entry.getValue(), forward);
            aliveKomas.add(koma);
            shogiBan.setKoma(koma, entry.getKey());
        }
    }

    public String getName() {
        return name;
    }

    public PlayOrder getPlayOrder() {
        return playOrder;
    }

    public boolean hasAliveKoma(Koma koma) {
        return aliveKomas.contains(koma);
    }

    public void addToAlive(Koma koma) {
        aliveKomas.add(koma);
    }

    public void removeFromAlive(Koma koma) {
        aliveKomas.remove(koma);
    }

    public void addToTook(Koma koma) {
        tookKomas.add(koma);
    }

    public boolean hasKing() {
        return aliveKomas.stream().anyMatch(k -> k.getType() == StandardKomaType.玉将 || k.getType() == StandardKomaType.王将);
    }

    public boolean canMoveTo(Masu to, ShogiBan shogiBan) {
        var koma = shogiBan.getKoma(to);
        return !hasAliveKoma(koma);
    }
}
