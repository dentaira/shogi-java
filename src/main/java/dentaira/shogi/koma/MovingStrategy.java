package dentaira.shogi.koma;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

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
    NOOP(Collections.emptyList());

    private List<MovingDistance> movingDistances;

    private MovingStrategy(List<MovingDistance> movingDistances) {
        this.movingDistances = movingDistances;
    }

    private MovingStrategy(Supplier<List<MovingDistance>> supplier) {
        this.movingDistances = supplier.get();
    }

    public List<MovingDistance> getMovingDistances() {
        return movingDistances;
    }
}
