package dentaira.shogi.run;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.StandardKomaType;
import dentaira.shogi.player.PlayOrder;
import dentaira.shogi.player.Player;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandLineShogiRunner {

    public static void main(String[] args) {

        var shogiBan = new ShogiBan();
        List<Player> players = initPlayers(shogiBan);

        var renderer = new CommandLineShogiRenderer(shogiBan, players.get(0), players.get(1));

        try (var sc = new Scanner(System.in)) {

            int turn = 0;

            while (true) {
                renderer.render();

                var turnPlayer = players.get(turn % 2);
                var nonTurnPlayer = players.stream().filter(p -> p != turnPlayer).findFirst().get();
                System.out.println((turn + 1) + "手目 " + turnPlayer.getName() + " の番です。");

                var fromOptional = selectFrom(sc);
                Masu from = null;
                Koma koma = null;
                if (fromOptional.isPresent()) {
                    // コマを移動する
                    from = fromOptional.get();
                    koma = getKoma(from, shogiBan, turnPlayer);
                    if (koma == null) {
                        continue;
                    }
                    var to = selectTo(koma, shogiBan, from, sc);
                    if (to == null) continue;

                    if (!turnPlayer.canMoveTo(to, shogiBan)) {
                        System.out.println("自身のコマが存在するマスには新たにコマを置けません。");
                        continue;
                    }

                    var tookKoma = shogiBan.moveKoma(from, to);
                    if (shogiBan.isEnemyTerritory(to, turnPlayer.getPlayOrder().getForward())) {
                        if (koma.canPromote()) {
                            boolean wantNarigoma = selectNarigoma(sc);
                            if (wantNarigoma) {
                                koma.promote();
                                System.out.println(to + " " + koma.getType().getAbbreviation());
                            }
                        }
                    }
                    if (tookKoma != null) {
                        nonTurnPlayer.removeFromAlive(tookKoma);
                        tookKoma.switchSides();
                        turnPlayer.addToTook(tookKoma);
                        System.out.println(turnPlayer.getName() + " が " + tookKoma.getType().name() + " を取得しました。");
                    }

                } else {
                    // 持ち駒を打つ
                    // TODO 持ち駒はありません。
                    System.out.println("持ち駒を選択してください。");
                    List<Koma> tookKomas = turnPlayer.getTookKomas();
                    var tookKomaLine = IntStream.range(0, tookKomas.size())
                            .mapToObj(i -> i + "." + tookKomas.get(i).getType())
                            .collect(Collectors.joining(", "));
                    System.out.println(tookKomaLine);
                    System.out.print("番号：");
                    var input = sc.nextLine();
                    int komaIndex;
                    try {
                        komaIndex = Integer.parseInt(input);
                        koma = tookKomas.get(komaIndex);
                        if (koma == null) {
                            System.out.println("持ち駒の番号を入力してください。");
                            continue;
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("持ち駒の番号を入力してください。");
                        continue;
                    }
                    System.out.println("コマを置くマスを入力してください。");
                    System.out.print("筋段：");
                    var inputResult = scanInputMasu(sc);
                    if (!inputResult.isValid()) {
                        System.out.println("2桁の数字を入力してください。");
                        continue;
                    }
                    Masu to = inputResult.getInput();
                    if (shogiBan.getKoma(to) != null) {
                        System.out.println("コマが存在するマスには置けません。");
                        continue;
                    }
                    if (koma.getType() == StandardKomaType.歩兵) {
                        if (shogiBan.has歩(to.x(), turnPlayer.getPlayOrder().getForward())) {
                            System.out.println("二歩です。");
                            continue;
                        }
                    }
                    shogiBan.setKoma(koma, to);
                    turnPlayer.removeFromTook(koma);
                    turnPlayer.addToAlive(koma);
                    System.out.println(to + " " + koma.getType().getAbbreviation());
                }

                if (!nonTurnPlayer.hasKing()) {
                    System.out.println(turnPlayer.getName() + " の勝利です！");
                    return;
                }

                turn++;
            }
        }
    }

    private static Koma getKoma(Masu masu, ShogiBan shogiBan, Player turnPlayer) {
        var koma = shogiBan.getKoma(masu);

        if (koma == null) {
            System.out.println("自分のコマが存在するマスを選択してください。");
            return null;
        }

        if (!turnPlayer.hasAliveKoma(koma)) {
            System.out.println("自分のコマを選択してください。");
            return null;
        }

        System.out.println(masu + " " + koma.getType().getAbbreviation());
        return koma;
    }

    private static boolean selectNarigoma(Scanner sc) {
        while (true) {
            System.out.print("成駒しますか？(y/n)：");
            var input = sc.nextLine();
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("y か n を入力してください。");
                    continue;
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

    private static Optional<Masu> selectFrom(Scanner sc) {

        while (true) {
            System.out.println("コマを選択してください。（持ち駒を打つ場合は00を入力してください。）");
            System.out.print("筋段:");

            var input = sc.nextLine();

            if (input.length() != 2) {
                System.out.println("2桁の数字を入力してください。");
            }

            if ("00".equals(input)) {
                return Optional.empty();
            }

            try {
                int x = Integer.parseInt(input.substring(0, 1));
                int y = Integer.parseInt(input.substring(1));
                return Optional.of(new Masu(x, y));
            } catch (NumberFormatException e) {
                System.out.println("2桁の数字を入力してください。");
            }
        }
    }

    private static Masu selectTo(Koma koma, ShogiBan shogiBan, Masu from, Scanner sc) {

        List<Masu> movingCandidate = koma.getMovingCandidates(from, shogiBan);
        if (movingCandidate.isEmpty()) {
            System.out.println("移動できるマスがありません。");
            return null;
        }

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
