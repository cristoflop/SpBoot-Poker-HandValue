package es.cristoflop.poker.valormano.application;

import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import es.cristoflop.poker.valormano.domain.EvaluadorManos;
import es.cristoflop.poker.valormano.domain.Mano;
import es.cristoflop.poker.valormano.domain.ManoBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JugadaServiceTest {

    private JugadaService jugadaService;

    @Before
    public void before() {
        this.jugadaService = new JugadaService();
    }

    @Test
    public void testGivenStringWithCardWhenGetPlayThenReturnPlay() {
        ManoDto manoDto = new ManoDto("AsAdTd");
        Mano mano = new ManoBuilder()
                .add(manoDto.getMano())
                .build();

        String fromModel = EvaluadorManos.getInstance().evalua(mano).getValorJugada().toString();
        String fromService = this.jugadaService.getJugadaFromMano(manoDto).getValorJugada();

        assertEquals(fromModel, fromService);
    }

}
