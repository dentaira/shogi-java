package dentaira.shogi.player;

import dentaira.shogi.koma.MoveDirection;

public enum PlayOrder {

    先手(MoveDirection.LOWER),
    後手(MoveDirection.HIGHER);

    private MoveDirection moveDirection;

    PlayOrder(MoveDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    public MoveDirection getMoveDirection() {
        return moveDirection;
    }
}
