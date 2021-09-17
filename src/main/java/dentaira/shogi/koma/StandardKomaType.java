package dentaira.shogi.koma;

public enum StandardKomaType implements KomaType {

    玉将,
    王将,
    飛車,
    角行,
    金将,
    銀将,
    桂馬,
    香車,
    歩;
    
    private String abbreviation;

    StandardKomaType() {
        this.abbreviation = String.valueOf(name().charAt(0));
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }
}
