package dentaira.shogi.koma;

/**
 * TODO 金のくずし字をどうするか考える
 */
public enum PromotedKomaType implements KomaType {

    竜王("竜"),
    龍馬("馬"),
    成銀("全"),
    成桂("含"),
    成香("索"),
    と金("と");

    private String display;

    PromotedKomaType(String display) {
        this.display = display;
    }

    @Override
    public String display() {
        return display;
    }
}
