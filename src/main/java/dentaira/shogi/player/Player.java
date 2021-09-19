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

    private List<Koma> komas;

    public Player(String name, PlayOrder playOrder) {
        this.name = name;
        this.playOrder = playOrder;
        this.komas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public PlayOrder getPlayOrder() {
        return playOrder;
    }

    public void addKoma(Koma koma) {
        komas.add(koma);
    }

    public void removeKoma(Koma koma) {
        komas.remove(koma);
    }

    public void setUp(ShogiBan shogiBan) {
        for (Map.Entry<Masu, KomaType> entry : InitialKomaPositions.getPositions(playOrder).entrySet()) {
            var koma = new Koma(entry.getValue());
            komas.add(koma);
            shogiBan.setKoma(koma, entry.getKey());
        }
    }

    public boolean hasKoma(Koma koma) {
        return komas.contains(koma);
    }

    public boolean hasKing() {
        return komas.stream().anyMatch(k -> k.getType() == StandardKomaType.玉将 || k.getType() == StandardKomaType.王将);
    }

    public boolean canMoveTo(Masu to, ShogiBan shogiBan) {
        var koma = shogiBan.getKoma(to);
        return !hasKoma(koma);
    }
}
