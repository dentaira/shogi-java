package dentaira.shogi.player;

import dentaira.shogi.koma.MovingDirection;

public enum PlayOrder {

    先手(MovingDirection.LOWER),
    後手(MovingDirection.HIGHER);

    private MovingDirection moveDirection;

    PlayOrder(MovingDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    public MovingDirection getMoveDirection() {
        return moveDirection;
    }
}
