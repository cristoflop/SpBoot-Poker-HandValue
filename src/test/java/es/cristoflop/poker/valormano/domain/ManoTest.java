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
}
