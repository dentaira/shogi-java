package dentaira.shogi.koma;

public enum Forward {
    LOWER, HIGHER;

    public static Forward reverse(Forward forward) {
        return switch (forward) {
            case LOWER -> HIGHER;
            case HIGHER -> LOWER;
        };
    }
}
