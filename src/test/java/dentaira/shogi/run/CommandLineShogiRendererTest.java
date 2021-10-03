package dentaira.shogi.run;

import dentaira.shogi.ban.ShogiBan;
import dentaira.shogi.koma.Forward;
import dentaira.shogi.koma.Koma;
import dentaira.shogi.koma.StandardKomaType;
import dentaira.shogi.player.PlayOrder;
import dentaira.shogi.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CommandLineShogiRendererTest {

    @Nested
    class CreateGoteKomadaiLineTest {

        CommandLineShogiRenderer renderer;

        Player gote;

        Player sente;

        @BeforeEach
        void setUp() {
            var ban = new ShogiBan();
            sente = new Player("sente", PlayOrder.先手);
            gote = new Player("gote", PlayOrder.後手);
            renderer = new CommandLineShogiRenderer(ban, sente, gote);
        }

        @Test
        void test() {
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            gote.addToTook(new Koma(StandardKomaType.歩兵, Forward.HIGHER));
            var actual = renderer.createGoteKomadaiLine();

            actual.forEach(System.out::println);
        }

        @Test
        void testSente() {
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            var actual = renderer.createSenteKomadaiLine();

            actual.forEach(System.out::println);
        }


        @Test
        void testSente2() {
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));
            sente.addToTook(new Koma(StandardKomaType.歩兵, Forward.LOWER));

            var actual = renderer.createSenteKomadaiLine();

            actual.forEach(System.out::println);
        }
    }
}