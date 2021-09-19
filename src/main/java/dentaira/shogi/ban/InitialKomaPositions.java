package dentaira.shogi.ban;

import dentaira.shogi.koma.KomaType;
import dentaira.shogi.player.PlayOrder;

import java.util.Map;

import static dentaira.shogi.koma.StandardKomaType.*;

public class InitialKomaPositions {

    private static Map<Masu, KomaType> sentePositions = Map.ofEntries(
            Map.entry(new Masu(5, 9), 玉将),
            Map.entry(new Masu(2, 8), 飛車),
            Map.entry(new Masu(8, 8), 角行),
            Map.entry(new Masu(4, 9), 金将),
            Map.entry(new Masu(6, 9), 金将),
            Map.entry(new Masu(3, 9), 銀将),
            Map.entry(new Masu(7, 9), 銀将),
            Map.entry(new Masu(2, 9), 桂馬),
            Map.entry(new Masu(8, 9), 桂馬),
            Map.entry(new Masu(1, 9), 香車),
            Map.entry(new Masu(9, 9), 香車),
            Map.entry(new Masu(1, 7), 歩兵),
            Map.entry(new Masu(2, 7), 歩兵),
            Map.entry(new Masu(3, 7), 歩兵),
            Map.entry(new Masu(4, 7), 歩兵),
            Map.entry(new Masu(5, 7), 歩兵),
            Map.entry(new Masu(6, 7), 歩兵),
            Map.entry(new Masu(7, 7), 歩兵),
            Map.entry(new Masu(8, 7), 歩兵),
            Map.entry(new Masu(9, 7), 歩兵)
    );

    private static Map<Masu, KomaType> gotePositions = Map.ofEntries(
            Map.entry(new Masu(5, 1), 王将),
            Map.entry(new Masu(8, 2), 飛車),
            Map.entry(new Masu(2, 2), 角行),
            Map.entry(new Masu(4, 1), 金将),
            Map.entry(new Masu(6, 1), 金将),
            Map.entry(new Masu(3, 1), 銀将),
            Map.entry(new Masu(7, 1), 銀将),
            Map.entry(new Masu(2, 1), 桂馬),
            Map.entry(new Masu(8, 1), 桂馬),
            Map.entry(new Masu(1, 1), 香車),
            Map.entry(new Masu(9, 1), 香車),
            Map.entry(new Masu(1, 3), 歩兵),
            Map.entry(new Masu(2, 3), 歩兵),
            Map.entry(new Masu(3, 3), 歩兵),
            Map.entry(new Masu(4, 3), 歩兵),
            Map.entry(new Masu(5, 3), 歩兵),
            Map.entry(new Masu(6, 3), 歩兵),
            Map.entry(new Masu(7, 3), 歩兵),
            Map.entry(new Masu(8, 3), 歩兵),
            Map.entry(new Masu(9, 3), 歩兵)
    );

    private static Map<PlayOrder, Map<Masu, KomaType>> map = Map.of(
            PlayOrder.先手, sentePositions,
            PlayOrder.後手, gotePositions
    );

    public static Map<Masu, KomaType> getPositions(PlayOrder playOrder) {
        return map.get(playOrder);
    }
}
