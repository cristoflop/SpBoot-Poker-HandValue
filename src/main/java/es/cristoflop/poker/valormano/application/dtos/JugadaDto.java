package es.cristoflop.poker.valormano.application.dtos;

public class JugadaDto {

    private String valorJugada;
    private String cartasJugada;

    public JugadaDto(String valorJugada, String cartasJugada) {
        this.valorJugada = valorJugada;
        this.cartasJugada = cartasJugada;
    }

    public String getValorJugada() {
        return this.valorJugada;
    }

    public String getCartasJugada() {
        return this.cartasJugada;
    }

}
