package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
            list.addAll(getMovingLine(8, i -> position.shift(0, i, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(0, -i, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(i, 0, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(-i, 0, forward), forward, shogiBan));
            return list;
        }
    },
    角行 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            list.addAll(getMovingLine(8, i -> position.shift(i, i, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(i, -i, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(-i, i, forward), forward, shogiBan));
            list.addAll(getMovingLine(8, i -> position.shift(-i, -i, forward), forward, shogiBan));
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
            return getMovingLine(8, i -> position.shift(0, i, forward), forward, shogiBan);
        }
    },
    歩兵(List.of(new MovingDistance(0, 1))),
    竜王 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            list.addAll(飛車.getMovingCandidates(position, forward, shogiBan));
            getMovingPoint(new MovingDistance(-1, -1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(1, -1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(1, 1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(-1, 1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            return list;
        }
    },
    竜馬 {
        @Override
        public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
            var list = new ArrayList<Masu>();
            list.addAll(角行.getMovingCandidates(position, forward, shogiBan));
            getMovingPoint(new MovingDistance(0, -1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(0, 1), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(1, 0), position, forward, shogiBan).ifPresent(m -> list.add(m));
            getMovingPoint(new MovingDistance(-1, 0), position, forward, shogiBan).ifPresent(m -> list.add(m));
            return list;
        }
    };

    private List<MovingDistance> movingDistances;

    MovingStrategy() {
        this.movingDistances = Collections.emptyList();
    }

    MovingStrategy(List<MovingDistance> movingDistances) {
        this.movingDistances = movingDistances;
    }

    public List<Masu> getMovingCandidates(Masu position, Forward forward, ShogiBan shogiBan) {
        return movingDistances.stream()
                .map(d -> getMovingPoint(d, position, forward, shogiBan))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    protected Optional<Masu> getMovingPoint(MovingDistance movingDistance, Masu position, Forward forward, ShogiBan shogiBan) {
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

    protected List<Masu> getMovingLine(int length, Function<Integer, Optional<Masu>> masuFunction, Forward forward, ShogiBan shogiBan) {
        var list = new ArrayList<Masu>();
        for (int i = 1; i <= length; i++) {
            var masu = masuFunction.apply(i);
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

}
