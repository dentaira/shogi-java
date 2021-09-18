package dentaira.shogi.ban;

import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.StandardKomaType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            var koma = new Koma(StandardKomaType.歩兵);
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
            var moveKoma = new Koma(StandardKomaType.歩兵);
            ban.setKoma(moveKoma, from);
            var placedKoma = new Koma(StandardKomaType.歩兵);
            ban.setKoma(placedKoma, to);
            var otherKoma = new Koma(StandardKomaType.歩兵);
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
}