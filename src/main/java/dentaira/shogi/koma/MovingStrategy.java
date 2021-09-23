package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum MovingStrategy {

    玉将(List.of(
            new MovingDistance(1, -1),
            new MovingDistance(0, -1),
            new MovingDistance(-1, -1),
            new MovingDistance(1, 0),
            new MovingDistance(-1, 0),
            new MovingDistance(1, 1),
            new MovingDistance(0, 1),
            new MovingDistance(-1, 1))),
    飛車 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(0, i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = -1; i > -9; i--) {
                var masu = position.shift(0, i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(i, 0, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = -1; i > -9; i--) {
                var masu = position.shift(i, 0, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            return list;
        }
    },
    角行 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(i, i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(-i, i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(i, -i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(-i, -i, forward);
                if (masu.isEmpty()) {
                    break;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    break;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    break;
                }
            }
            return list;
        }
    },
    金将(List.of(
            new MovingDistance(0, -1),
            new MovingDistance(1, 0),
            new MovingDistance(-1, 0),
            new MovingDistance(1, 1),
            new MovingDistance(0, 1),
            new MovingDistance(-1, 1))),
    銀将(List.of(
            new MovingDistance(1, -1),
            new MovingDistance(-1, -1),
            new MovingDistance(1, 1),
            new MovingDistance(0, 1),
            new MovingDistance(-1, 1))),
    桂馬(List.of(new MovingDistance(-1, 2), new MovingDistance(1, 2))),
    香車 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            for (int i = 1; i < 9; i++) {
                var masu = position.shift(0, i, forward);
                if (masu.isEmpty()) {
                    return list;
                }
                var koma = shogiBan.getKoma(masu.get());
                if (koma != null && koma.getForward() == forward) {
                    return list;
                }

                list.add(masu.get());

                if (koma != null && koma.getForward() != forward) {
                    return list;
                }
            }
            return list;
        }
    },
    歩兵(List.of(new MovingDistance(0, 1)));

    private List<MovingDistance> movingDistances;

    MovingStrategy() {
        this.movingDistances = Collections.emptyList();
    }

    MovingStrategy(List<MovingDistance> movingDistances) {
        this.movingDistances = movingDistances;
    }

    public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
        return movingDistances.stream()
                .map(d -> getMovingCandidate(d, position, forward, shogiBan))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    protected Optional<Masu> getMovingCandidate(MovingDistance movingDistance, Masu position, Forward forward, ShogiBan shogiBan) {
        var masu = position.shift(movingDistance.x(), movingDistance.y(), forward);
        if (masu.isEmpty()) {
            return masu;
        }
        var koma = shogiBan.getKoma(masu.get());
        if (koma != null && koma.getForward() == forward) {
            return Optional.empty();
        }
        return masu;
    }

}
