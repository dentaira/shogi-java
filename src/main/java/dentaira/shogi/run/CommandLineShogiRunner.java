package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Koma;

import java.util.Scanner;

public class CommandLineShogiRunner {

    public static void main(String[] args) {
        var shogiBan = new ShogiBan();

        try (var sc = new Scanner(System.in)) {
            while (true) {
                shogiBan.render();

                System.out.println("コマを選択してください。");
                System.out.print("筋段:");
                var inputMasu = scanInputMasu(sc);
                if (!inputMasu.isValid()) {
                    System.out.println("2桁の数字を入力してください。");
                    continue;
                }
                var masu = inputMasu.getInput();
                var koma = shogiBan.getKoma(masu);
                System.out.println(masu + " " + koma.getType().getAbbreviation());
            }
        }
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
