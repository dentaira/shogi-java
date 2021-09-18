package dentaira.shogi.ban;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MasuTest {

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