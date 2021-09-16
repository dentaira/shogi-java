package dentaira.shogi.koma;

public class Koma {

    private StandardKomaType type;

    public Koma(StandardKomaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.display();
    }
}
