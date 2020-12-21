package es.cristoflop.poker.valormano.application.dtos;

import java.util.List;

public class JugadaDto {

    private String valorJugada;
    private List<String> cartasJugada;

    public JugadaDto(String valorJugada, List<String> cartasJugada) {
        this.valorJugada = valorJugada;
        this.cartasJugada = cartasJugada;
    }

    public String getValorJugada() {
        return this.valorJugada;
    }

    public List<String> getCartasJugada() {
        return this.cartasJugada;
    }

}
