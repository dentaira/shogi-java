package dentaira.shogi.koma;

public enum StandardKomaType implements KomaType {

    玉将(MovingStrategy.玉将),
    王将(MovingStrategy.玉将),
    飛車(MovingStrategy.飛車),
    角行(MovingStrategy.角行),
    金将(MovingStrategy.金将),
    銀将(MovingStrategy.銀将),
    桂馬(MovingStrategy.桂馬),
    香車(MovingStrategy.香車),
    歩兵(MovingStrategy.歩兵);

    private String abbreviation;

    private MovingStrategy movingStrategy;

    StandardKomaType(MovingStrategy movingStrategy) {
        this.abbreviation = String.valueOf(name().charAt(0));
        this.movingStrategy = movingStrategy;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

}
