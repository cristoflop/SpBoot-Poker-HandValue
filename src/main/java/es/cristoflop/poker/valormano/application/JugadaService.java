package es.cristoflop.poker.valormano.application;

import es.cristoflop.poker.valormano.application.dtos.JugadaDto;
import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import es.cristoflop.poker.valormano.data.JugadaRepository;
import es.cristoflop.poker.valormano.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadaService {

    private final JugadaRepository jugadaRepository;
    private final EvaluadorManos evaluadorManos;

    public JugadaService(JugadaRepository jugadaRepository) {
        this.jugadaRepository = jugadaRepository;
        this.evaluadorManos = EvaluadorManos.getInstance();
    }

    public List<JugadaDto> getJugadas() {
        return this.jugadaRepository
                .findAll()
                .stream()
                .map(this::mapToJugadaDto)
                .collect(Collectors.toList());
    }

    public JugadaDto saveJugadaFromMano(ManoDto manoDto) {
        Mano mano = this.mapToMano(manoDto);
        Jugada jugada = this.evaluadorManos.evalua(mano);
        if (!jugada.isNull())
            this.jugadaRepository.save(jugada);
        return this.mapToJugadaDto(jugada);
    }

    private Mano mapToMano(ManoDto manoDto) {
        return new ManoBuilder()
                .add(manoDto
                        .getCartas()
                        .trim())
                .build();
    }

    private JugadaDto mapToJugadaDto(Jugada jugada) {
        return new JugadaDto(
                jugada.getValorJugada().toString(),
                jugada.getCartasJugada());
    }

}
