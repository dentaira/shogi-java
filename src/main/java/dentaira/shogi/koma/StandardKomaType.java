package dentaira.shogi.koma;

import java.util.function.Supplier;

public enum StandardKomaType implements KomaType {

    玉将(MovingStrategy.玉将),
    王将(MovingStrategy.玉将),
    飛車(MovingStrategy.飛車, () -> PromotedKomaType.竜王),
    角行(MovingStrategy.角行, () -> PromotedKomaType.竜馬),
    金将(MovingStrategy.金将),
    銀将(MovingStrategy.銀将, () -> PromotedKomaType.成銀),
    桂馬(MovingStrategy.桂馬, () -> PromotedKomaType.成桂),
    香車(MovingStrategy.香車, () -> PromotedKomaType.成香),
    歩兵(MovingStrategy.歩兵, () -> PromotedKomaType.と金);

    private String abbreviation;

    private MovingStrategy movingStrategy;

    private Supplier<PromotedKomaType> promotedKomaType;

    StandardKomaType(MovingStrategy movingStrategy) {
        this(movingStrategy, null);
    }

    /**
     * @param movingStrategy
     * @param promotedKomaType {@link PromotedKomaType}と循環参照しているため、インスタンスそのものだと初期化前でnullになる可能性がある。
     */
    StandardKomaType(MovingStrategy movingStrategy, Supplier<PromotedKomaType> promotedKomaType) {
        this.abbreviation = String.valueOf(name().charAt(0));
        this.movingStrategy = movingStrategy;
        this.promotedKomaType = promotedKomaType;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public boolean canPromote() {
        return promotedKomaType != null;
    }

    public PromotedKomaType promote() {
        return promotedKomaType.get();
    }
}
