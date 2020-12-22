package es.cristoflop.poker.valormano.controller;

import es.cristoflop.poker.valormano.application.JugadaService;
import es.cristoflop.poker.valormano.application.dtos.JugadaDto;
import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JugadaController {

    private final JugadaService jugadaService;

    public JugadaController(JugadaService jugadaService) {
        this.jugadaService = jugadaService;
    }

    @PostMapping("/jugada")
    public ResponseEntity<JugadaDto> getJugadaFromMano(@RequestBody ManoDto mano) {
        JugadaDto jugadaDto = this.jugadaService.getJugadaFromMano(mano);
        return ResponseEntity.ok(jugadaDto);
    }

}
