package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.player.PlayOrder;
import dentaira.shogi.player.Player;

import java.util.List;
import java.util.Scanner;

public class CommandLineShogiRunner {

    public static void main(String[] args) {

        var shogiBan = new ShogiBan();
        List<Player> players = initPlayers(shogiBan);

        try (var sc = new Scanner(System.in)) {

            int turn = 0;

            while (true) {
                shogiBan.render();

                var turnPlayer = players.get(turn % 2);
                var nonTurnPlayer = players.stream().filter(p -> p != turnPlayer).findFirst().get();
                System.out.println((turn + 1) + "手目 " + turnPlayer.getName() + " の番です。");

                var from = selectFrom(turnPlayer, shogiBan, sc);
                if (from == null) continue;

                var to = selectTo(shogiBan, from, sc);
                if (to == null) continue;

                if (!turnPlayer.canMoveTo(to, shogiBan)) {
                    System.out.println("自身のコマが存在するマスには新たにコマを置けません。");
                    continue;
                }

                var pickedKoma = shogiBan.moveKoma(from, to);
                if (pickedKoma != null) {
                    nonTurnPlayer.removeKoma(pickedKoma);
                    turnPlayer.addKoma(pickedKoma);
                    System.out.println(turnPlayer.getName() + " が " + pickedKoma.getType().name() + " を取得しました。");
                }

                if (!nonTurnPlayer.hasKing()) {
                    System.out.println(turnPlayer.getName() + " の勝利です！");
                    return;
                }

                turn++;
            }
        }
    }

    private static List<Player> initPlayers(ShogiBan shogiBan) {

        var player1 = new Player("プレイヤー１", PlayOrder.先手);
        player1.setUp(shogiBan);

        var player2 = new Player("プレイヤー２", PlayOrder.後手);
        player2.setUp(shogiBan);

        return List.of(player1, player2);
    }

    private static Masu selectFrom(Player player, ShogiBan shogiBan, Scanner sc) {

        System.out.println("コマを選択してください。");
        System.out.print("筋段:");

        var inputResult = scanInputMasu(sc);
        if (!inputResult.isValid()) {
            System.out.println("2桁の数字を入力してください。");
            return null;
        }

        var masu = inputResult.getInput();
        var koma = shogiBan.getKoma(masu);

        if (koma == null) {
            System.out.println("自分のコマが存在するマスを選択してください。");
            return null;
        }

        if (!player.hasKoma(koma)) {
            System.out.println("自分のコマを選択してください。");
            return null;
        }

        System.out.println(masu + " " + koma.getType().getAbbreviation());

        return masu;
    }

    private static Masu selectTo(ShogiBan shogiBan, Masu from, Scanner sc) {

        var koma = shogiBan.getKoma(from);
        var myForward = koma.getForward();
        List<Masu> movingCandidate = koma.getMovingCandidates(from, shogiBan);
        System.out.println("移動するマスを選択してください。（半角数字で入力）");
        movingCandidate.stream().forEach(System.out::println);
        System.out.print("筋段:");

        var inputResult = scanInputMasu(sc);
        if (!inputResult.isValid()) {
            System.out.println("2桁の数字を入力してください。");
            return null;
        }

        var inputMasu = inputResult.getInput();

        if (!movingCandidate.contains(inputMasu)) {
            System.out.println("そのマスには移動できません。");
            return null;
        }

        return inputMasu;
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
