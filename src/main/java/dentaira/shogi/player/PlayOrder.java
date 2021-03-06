package dentaira.shogi.player;

import dentaira.shogi.koma.Forward;

public enum PlayOrder {

    εζ(Forward.LOWER),
    εΎζ(Forward.HIGHER);

    private Forward forward;

    PlayOrder(Forward forward) {
        this.forward = forward;
    }

    public Forward getForward() {
        return forward;
    }
}
