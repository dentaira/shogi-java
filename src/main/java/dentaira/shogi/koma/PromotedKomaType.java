package dentaira.shogi.koma;

import java.util.function.Supplier;

/**
 * TODO 金のくずし字をどうするか考える
 */
public enum PromotedKomaType implements KomaType {

    竜王("竜", MovingStrategy.竜王, () -> StandardKomaType.飛車),
    竜馬("馬", MovingStrategy.竜馬, () -> StandardKomaType.角行),
    成銀("全", MovingStrategy.金将, () -> StandardKomaType.銀将),
    成桂("含", MovingStrategy.金将, () -> StandardKomaType.桂馬),
    成香("索", MovingStrategy.金将, () -> StandardKomaType.香車),
    と金("と", MovingStrategy.金将, () -> StandardKomaType.歩兵);

    private String abbreviation;

    private MovingStrategy movingStrategy;

    private Supplier<StandardKomaType> demotedKomaType;

    /**
     *
     * @param abbreviation
     * @param movingStrategy
     * @param demotedKomaType {@link StandardKomaType}と循環参照しているため、インスタンスそのものだと初期化前でnullになる可能性がある。
     */
    PromotedKomaType(String abbreviation, MovingStrategy movingStrategy, Supplier<StandardKomaType> demotedKomaType) {
        this.abbreviation = abbreviation;
        this.movingStrategy = movingStrategy;
        this.demotedKomaType = demotedKomaType;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public StandardKomaType demote() {
        return demotedKomaType.get();
    }
}
