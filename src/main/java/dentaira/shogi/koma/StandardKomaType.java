package dentaira.shogi.koma;

public enum StandardKomaType implements KomaType {

    玉,
    王,
    飛車,
    角,
    金,
    銀,
    桂馬,
    香車,
    歩;
    
    private String display;

    StandardKomaType() {
        this.display = String.valueOf(name().charAt(0));
    }

    @Override
    public String display() {
        return display;
    }
}
