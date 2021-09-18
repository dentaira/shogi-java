package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;

import java.util.Scanner;

public class CommandLineShogiRunner {

    public static void main(String[] args) {
        var shogiBan = ShogiBan.setup();

        try (var sc = new Scanner(System.in)) {
            while (true) {
                shogiBan.render();

                var from = selectFrom(shogiBan, sc);
                if (from == null) continue;

                var to = selectTo(sc);
                if (to == null) continue;

                var pickedKoma = shogiBan.moveKoma(from, to);
                if (pickedKoma != null) {
                    System.out.println("コマを取得しました。" + pickedKoma.getType().name());
                }

            }
        }
    }

    private static Masu selectTo(Scanner sc) {

        System.out.println("移動するマスを選択してください。");

        var inputResult = scanInputMasu(sc);
        if (!inputResult.isValid()) {
            System.out.println("2桁の数字を入力してください。");
            return null;
        }

        return inputResult.getInput();
    }

    private static Masu selectFrom(ShogiBan shogiBan, Scanner sc) {

        System.out.println("コマを選択してください。");
        System.out.print("筋段:");

        var inputResult = scanInputMasu(sc);
        if (!inputResult.isValid()) {
            System.out.println("2桁の数字を入力してください。");
            return null;
        }

        var masu = inputResult.getInput();
        var koma = shogiBan.getKoma(masu);

        System.out.println(masu + " " + koma.getType().getAbbreviation());

        return masu;
    }

    private static ScanResult<Masu> scanInputMasu(Scanner sc) {

        var input = sc.nextLine();

        if (input.length() != 2) {
            return new ScanResult<>(false, null);
        }

        try {
            int x = Integer.parseInt((input.substring(0, 1)));
            int y = Integer.parseInt(input.substring(1));
            return new ScanResult<>(true, new Masu(x, y));
        } catch (NumberFormatException e) {
            return new ScanResult<>(false, null);
        }
    }

    private static class ScanResult<T> {
        private boolean valid;
        private T input;

        public ScanResult(boolean valid, T input) {
            this.valid = valid;
            this.input = input;
        }

        public boolean isValid() {
            return valid;
        }

        public T getInput() {
            return input;
        }
    }
}
