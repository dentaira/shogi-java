package dentaira.shogi.ban;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MasuTest {

    @Nested
    class ConstructorTest {

        @ParameterizedTest
        @CsvSource({"1, 1", "9, 9", "1, 9", "9, 1", "5, 7", "2, 8"})
        void testValidValue(int x, int y) {
            var masu = new Masu(x, y);
            assertEquals(x, masu.x());
            assertEquals(y, masu.y());
        }

        @ParameterizedTest
        @CsvSource({"0, 1", "1, 0", "10, 9", "9, 10", "11, 1", "1, 11", "-1, 0", "0, -1"})
        void testInvalidValue(int x, int y) {
            assertThrows(IllegalArgumentException.class, () -> new Masu(x, y));
        }
    }

    @Nested
    class ShiftTest {

        @ParameterizedTest
        @CsvSource({
                "1, 1, 1, 0",
                "1, 1, 0, 1",
                "2, 2, -1, 0",
                "2, 2, 0, -1",
                "1, 1, 1, 5",
                "1, 1, 8, 7",
                "1, 5, 3, 4",
        })
        void testValidValue(int nowX, int nowY, int plusX, int plusY) {
            var now = new Masu(nowX, nowY);
            var actual = now.shift(plusX, plusY).get();
            assertEquals(new Masu(nowX + plusX, nowY + plusY), actual);
        }

        @ParameterizedTest
        @CsvSource({
                "1, 1, -1, 0",
                "1, 1, 0, -1",
        })
        void testInvalidValue(int nowX, int nowY, int plusX, int plusY) {
            var now = new Masu(nowX, nowY);
            var actual = now.shift(plusX, plusY);
            assertEquals(Optional.empty(), actual);
        }
    }

    @Nested
    class ToStringTest {

        @ParameterizedTest
        @CsvSource({
                "1, 1, １一",
                "2, 2, ２二",
                "3, 3, ３三",
                "4, 9, ４九",
                "5, 8, ５八",
                "6, 7, ６七",
                "7, 6, ７六",
                "8, 5, ８五",
                "9, 4, ９四"
        })
        void test(int x, int y, String expected) {
            var masu = new Masu(x, y);
            assertEquals(expected, masu.toString());
        }
    }
}
