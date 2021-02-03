package es.cristoflop.poker.valormano.controller;

import es.cristoflop.poker.valormano.application.JugadaService;
import es.cristoflop.poker.valormano.application.dtos.JugadaDto;
import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JugadaController {

    private final JugadaService jugadaService;

    public JugadaController(JugadaService jugadaService) {
        this.jugadaService = jugadaService;
    }

    @GetMapping("/jugadas")
    public ResponseEntity<List<JugadaDto>> getJugadas() {
        return ResponseEntity.ok(this.jugadaService.getJugadas());
    }

    @PostMapping("/jugadas")
    public ResponseEntity<JugadaDto> saveJugadaFromMano(@RequestBody ManoDto mano) {
        JugadaDto jugadaDto = this.jugadaService.saveJugadaFromMano(mano);
        return ResponseEntity.ok(jugadaDto);
    }

}
