package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static dentaira.shogi.koma.MovingStrategy.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MovingStrategyTest {

    @Nested
    class GetMovingCandidatesTest {

        @ParameterizedTest
        @MethodSource("testSource")
        void test(MovingStrategy strategy, List<Masu> expected) {
            var position = new Masu(5, 5);
            var shogiBan = new ShogiBan();

            var actual = strategy.getMovingCandidates(position, Forward.LOWER, shogiBan);

            assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        }

        static Stream<Arguments> testSource() {
            return Stream.of(
                    arguments(玉将, List.of(
                            new Masu(4, 4),
                            new Masu(5, 4),
                            new Masu(6, 4),
                            new Masu(4, 5),
                            new Masu(6, 5),
                            new Masu(4, 6),
                            new Masu(5, 6),
                            new Masu(6, 6))
                    ),
                    arguments(飛車, List.of(
                            new Masu(5, 1),
                            new Masu(5, 2),
                            new Masu(5, 3),
                            new Masu(5, 4),
                            new Masu(5, 6),
                            new Masu(5, 7),
                            new Masu(5, 8),
                            new Masu(5, 9),
                            new Masu(1, 5),
                            new Masu(2, 5),
                            new Masu(3, 5),
                            new Masu(4, 5),
                            new Masu(6, 5),
                            new Masu(7, 5),
                            new Masu(8, 5),
                            new Masu(9, 5))),
                    arguments(角行, List.of(
                            new Masu(1, 1),
                            new Masu(2, 2),
                            new Masu(3, 3),
                            new Masu(4, 4),
                            new Masu(6, 6),
                            new Masu(7, 7),
                            new Masu(8, 8),
                            new Masu(9, 9),
                            new Masu(1, 9),
                            new Masu(2, 8),
                            new Masu(3, 7),
                            new Masu(4, 6),
                            new Masu(6, 4),
                            new Masu(7, 3),
                            new Masu(8, 2),
                            new Masu(9, 1))),
                    arguments(金将, List.of(
                            new Masu(4, 4),
                            new Masu(5, 4),
                            new Masu(6, 4),
                            new Masu(4, 5),
                            new Masu(6, 5),
                            new Masu(5, 6))
                    ),
                    arguments(銀将, List.of(
                            new Masu(4, 4),
                            new Masu(5, 4),
                            new Masu(6, 4),
                            new Masu(4, 6),
                            new Masu(6, 6))
                    ),
                    arguments(桂馬, List.of(
                            new Masu(4, 3),
                            new Masu(6, 3))
                    ),
                    arguments(香車, List.of(
                            new Masu(5, 1),
                            new Masu(5, 2),
                            new Masu(5, 3),
                            new Masu(5, 4))),
                    arguments(歩兵, List.of(
                            new Masu(5, 4))
                    )
            );
        }
    }
}