package dentaira.shogi.koma;

import dentaira.shogi.ban.Masu;
import dentaira.shogi.ban.ShogiBan;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static dentaira.shogi.koma.StandardKomaType.玉将;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KomaTest {

    @Nested
    class GetMovingCandidateTest {

        @ParameterizedTest
        @MethodSource("testSource")
        void test(StandardKomaType komaType, int x, int y, List<Masu> expected) {
            var koma = new Koma(komaType, Forward.HIGHER);
            var placed = new Masu(x, y);
            var shogiBan = new ShogiBan();

            var actual = koma.getMovingCandidates(placed, shogiBan);

            assertThat(actual).containsExactlyElementsOf(expected);
        }

        static Stream<Arguments> testSource() {
            return Stream.of(
                    arguments(玉将, 1, 1, List.of(
                            new Masu(2, 1),
                            new Masu(2, 2),
                            new Masu(1, 2))),
                    arguments(玉将, 5, 1, List.of(
                            new Masu(6, 1),
                            new Masu(4, 1),
                            new Masu(6, 2),
                            new Masu(5, 2),
                            new Masu(4, 2))
                    ),
                    arguments(玉将, 2, 2, List.of(
                            new Masu(3, 1),
                            new Masu(2, 1),
                            new Masu(1, 1),
                            new Masu(3, 2),
                            new Masu(1, 2),
                            new Masu(3, 3),
                            new Masu(2, 3),
                            new Masu(1, 3))
                    )
            );
        }
    }
}