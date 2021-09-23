package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
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
    歩兵(List.of(new MovingDistance(0, 1))),
    NOOP(Collections.emptyList());

    private List<MovingDistance> movingDistances;

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

    private Optional<Masu> getMovingCandidate(MovingDistance movingDistance, Masu position, Forward forward, ShogiBan shogiBan) {
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
