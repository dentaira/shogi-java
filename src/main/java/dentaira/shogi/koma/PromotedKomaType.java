package dentaira.shogi.koma;

import java.util.Collections;
import java.util.List;

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

    private String abbreviation;

    PromotedKomaType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public List<MovingDistance> getMovingDistances() {
        // TODO MovingStrategyが完成したら修正
        return Collections.emptyList();
    }
}
