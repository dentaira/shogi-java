package dentaira.shogi.player;

import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.StandardKomaType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @Nested
    class HasKingTest {

        @Test
        void testGyokuExists() {
            var player = new Player("name", PlayOrder.先手);
            player.addKoma(new Koma(StandardKomaType.玉将));
            player.addKoma(new Koma(StandardKomaType.金将));
            assertTrue(player.hasKing());
        }

        @Test
        void testOuExists() {
            var player = new Player("name", PlayOrder.先手);
            player.addKoma(new Koma(StandardKomaType.王将));
            player.addKoma(new Koma(StandardKomaType.金将));
            assertTrue(player.hasKing());
        }

        @Test
        void testGyokuAndOuDoesNotExist() {
            var player = new Player("name", PlayOrder.先手);
            player.addKoma(new Koma(StandardKomaType.金将));
            player.addKoma(new Koma(StandardKomaType.歩兵));
            assertFalse(player.hasKing());
        }

        @Test
        void testKomaDoesNotExist() {
            assertFalse(new Player("name", PlayOrder.先手).hasKing());
        }
    }
}