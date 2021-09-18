package dentaira.shogi.ban;

public record Masu(int x, int y) {

    public Masu {
        if (x < 1 || 9 < x) {
            throw new IllegalArgumentException("xには1~9の数字を設定してください。x:" + x);
        }
        if (y < 1 || 9 < y) {
            throw new IllegalArgumentException("yには1~9の数字を設定してください。y:" + y);
        }
    }
}
