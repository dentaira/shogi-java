package dentaira.shogi.koma;

public class Koma {

    private KomaType type;

    private MoveDirection moveDirection;

    public Koma(KomaType type, MoveDirection moveDirection) {
        this.type = type;
        this.moveDirection = moveDirection;
    }

    public KomaType getType() {
        return type;
    }

    public MoveDirection getMoveDirection() {
        return moveDirection;
    }
}
