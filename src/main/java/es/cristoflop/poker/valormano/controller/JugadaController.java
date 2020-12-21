package es.cristoflop.poker.valormano.controller;

import es.cristoflop.poker.valormano.application.JugadaService;
import es.cristoflop.poker.valormano.application.dtos.JugadaDto;
import es.cristoflop.poker.valormano.application.dtos.ManoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JugadaController {

    private JugadaService jugadaService;

    public JugadaController(JugadaService jugadaService) {
        this.jugadaService = jugadaService;
    }

    @GetMapping("/jugada")
    public JugadaDto getJugadaFromMano(@RequestBody ManoDto mano) {
        return this.jugadaService.getJugadaFromMano(mano);
    }

}
