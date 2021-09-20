package dentaira.shogi.player;

import dentaira.shogi.koma.Forward;

public enum PlayOrder {

    先手(Forward.LOWER),
    後手(Forward.HIGHER);

    private Forward forward;

    PlayOrder(Forward forward) {
        this.forward = forward;
    }

    public Forward getForward() {
        return forward;
    }
}
