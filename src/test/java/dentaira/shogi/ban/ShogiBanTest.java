package dentaira.shogi.ban;

import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.StandardKomaType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ShogiBanTest {

    @Nested
    class MoveTest {

        @Test
        void testMoveToEmptyMasu() {
            // given
            var ban = new ShogiBan();
            var from = new Masu(1, 1);
            var to = new Masu(1, 2);
            var koma = new Koma(StandardKomaType.歩兵, Forward.LOWER);
            ban.setKoma(koma, from);

            // when
            var actual = ban.moveKoma(from, to);

            // then
            assertEquals(null, actual);
            assertSame(koma, ban.getKoma(to));
            assertEquals(null, ban.getKoma(from));
        }

        @Test
        void testMoveAndPickExistingKoma() {
            // given
            var ban = new ShogiBan();
            var from = new Masu(7, 5);
            var to = new Masu(7, 4);
            var moveKoma = new Koma(StandardKomaType.歩兵, Forward.LOWER);
            ban.setKoma(moveKoma, from);
            var placedKoma = new Koma(StandardKomaType.歩兵, Forward.LOWER);
            ban.setKoma(placedKoma, to);
            var otherKoma = new Koma(StandardKomaType.歩兵, Forward.LOWER);
            ban.setKoma(otherKoma, 8, 4);

            // when
            var actual = ban.moveKoma(from, to);

            // then
            assertSame(placedKoma, actual);
            assertSame(moveKoma, ban.getKoma(to));
            assertEquals(null, ban.getKoma(from));
            assertSame(otherKoma, ban.getKoma(8, 4));
        }
    }

    @Nested
    class IsEnemyTerritoryTest {

        @ParameterizedTest
        @CsvSource({"9, 1, true", "9, 2, true", "9, 3, true", "9, 4, false", "9, 9, false"})
        void testLower(int x, int y, boolean expected) {
            assertEquals(expected, new ShogiBan().isEnemyTerritory(new Masu(x, y), Forward.LOWER));
        }

        @ParameterizedTest
        @CsvSource({"9, 7, true", "9, 8, true", "9, 9, true", "9, 6, false", "9, 1, false"})
        void testHigher(int x, int y, boolean expected) {
            assertEquals(expected, new ShogiBan().isEnemyTerritory(new Masu(x, y), Forward.HIGHER));
        }
    }
}