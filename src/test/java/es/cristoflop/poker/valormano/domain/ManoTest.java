package es.cristoflop.poker.valormano.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ManoTest {

    private Mano mano;

    @Before
    public void before() {
        this.mano = new ManoBuilder()
                .add("2s")
                .add("4s")
                .add("Ts")
                .add("2d")
                .build();
    }

    @Test
    public void testGivenHandWhenConvertToStringThenIsAscendOrdered() {
        String orderedHand = "2s2d4sTs";
        assertEquals(orderedHand, this.mano.toStringOrdenado());
    }

    @Test
    public void testHighCard() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("3s")
                .add("Kh")
                .add("4c")
                .add("5d")
                .build();
        assertEquals(mano.getJugada().getValorJugada(), ValorJugada.CARTA_ALTA);
    }

    @Test
    public void testPair() {
        assertEquals(this.mano.getJugada().getValorJugada(), ValorJugada.PAREJA);
    }

    @Test
    public void testTrio() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("4s")
                .add("2c")
                .add("2d")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.TRIO);
    }

    @Test
    public void testDoublePair() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("4s")
                .add("4c")
                .add("3d")
                .add("2d")
                .add("Th")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.DOBLE_PAREJA);
    }

    @Test
    public void testStair() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("3s")
                .add("Ah")
                .add("4c")
                .add("Ks")
                .add("Kd")
                .add("5d")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.ESCALERA);
    }

    @Test
    public void testColor() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("3s")
                .add("Ks")
                .add("4s")
                .add("8s")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.COLOR);
    }

    @Test
    public void testOtherColor() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("3s")
                .add("Ks")
                .add("4s")
                .add("8s")
                .add("Jd")
                .add("Ad")
                .add("6d")
                .add("9c")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.COLOR);
    }

    @Test
    public void testFull() {
        Mano mano = new ManoBuilder()
                .add("Ad")
                .add("As")
                .add("Ah")
                .add("4c")
                .add("4d")
                .add("2s")
                .add("6h")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.FULL);
    }

    @Test
    public void testPoker() {
        Mano mano = new ManoBuilder()
                .add("Ad")
                .add("As")
                .add("Ah")
                .add("Ac")
                .add("4d")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.POKER);
    }

    @Test
    public void testColorStair() {
        Mano mano = new ManoBuilder()
                .add("2s")
                .add("3s")
                .add("As")
                .add("4s")
                .add("5s")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.ESCALERA_COLOR);
    }

    @Test
    public void testRealStair() {
        Mano mano = new ManoBuilder()
                .add("As")
                .add("Ks")
                .add("Qs")
                .add("Js")
                .add("Ts")
                .build();
        Jugada jugada = mano.getJugada();
        assertEquals(jugada.getValorJugada(), ValorJugada.ESCALERA_REAL);
    }

}
