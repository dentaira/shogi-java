package dentaira.shogi.koma;

/**
 * TODO 金のくずし字をどうするか考える
 */
public enum PromotedKomaType {

    竜王("竜"),
    龍馬("馬"),
    成銀("全"),
    成桂("含"),
    成香("索"),
    と金("と");

    private String abbreviation;

    PromotedKomaType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public MovingStrategy getMovingStrategy() {
        return null;
    }
}
