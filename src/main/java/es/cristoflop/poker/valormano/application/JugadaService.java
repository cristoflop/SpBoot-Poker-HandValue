package es.cristoflop.poker.valormano.application;

import es.cristoflop.poker.valormano.application.dtos.JugadaDto;
import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.Jugada;
import es.cristoflop.poker.valormano.domain.Mano;
import es.cristoflop.poker.valormano.domain.ManoBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JugadaService {

    public JugadaDto getJugadaFromMano(ManoDto manoDto) {
        String cartas = manoDto.getMano().trim();
        Mano mano = new ManoBuilder()
                .add(cartas)
                .build();
        Jugada jugada = mano.getJugada();
        return this.mappedJugadaDto(jugada);
    }

    private JugadaDto mappedJugadaDto(Jugada jugada) {
        return new JugadaDto(
                jugada.getValorJugada().toString(),
                jugada.getCartasJugada().stream().map(Carta::toString).collect(Collectors.toList()));
    }

}
