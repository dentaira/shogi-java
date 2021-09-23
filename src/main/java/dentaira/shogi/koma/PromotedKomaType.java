package dentaira.shogi.koma;

/**
 * TODO 金のくずし字をどうするか考える
 */
public enum PromotedKomaType implements KomaType {

    竜王("竜", MovingStrategy.竜王),
    龍馬("馬", MovingStrategy.竜馬),
    成銀("全", MovingStrategy.金将),
    成桂("含", MovingStrategy.金将),
    成香("索", MovingStrategy.金将),
    と金("と", MovingStrategy.金将);

    private String abbreviation;

    private MovingStrategy movingStrategy;

    PromotedKomaType(String abbreviation, MovingStrategy movingStrategy) {
        this.abbreviation = abbreviation;
        this.movingStrategy = movingStrategy;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }
}
