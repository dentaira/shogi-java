package dentaira.shogi.ban;

import java.util.Optional;

public record Masu(int x, int y) {

    private static String[] sujiArray = {"１", "２", "３", "４", "５", "６", "７", "８", "９"};

    private static String[] danArray = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public Masu {
        if (x < 1 || 9 < x) {
            throw new IllegalArgumentException("xには1~9の数字を設定してください。x:" + x);
        }
        if (y < 1 || 9 < y) {
            throw new IllegalArgumentException("yには1~9の数字を設定してください。y:" + y);
        }
    }

    @Override
    public String toString() {
        return sujiArray[x - 1] + danArray[y - 1];
    }

    public static String getSujiSymbol(int x) {
        return sujiArray[x - 1];
    }

    public static String getDanSymbol(int y) {
        return danArray[y - 1];
    }

    public Optional<Masu> shift(int x, int y) {
        try {
            return Optional.of(new Masu(x() + x, y() + y));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
