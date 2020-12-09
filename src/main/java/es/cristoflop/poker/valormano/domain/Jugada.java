package es.cristoflop.poker.valormano.domain;

import java.util.ArrayList;
import java.util.List;

public class Jugada {

    private final ValorJugada valorJugada;
    private final List<Carta> cartasJugada;

    public Jugada(ValorJugada valorJugada, List<Carta> cartasJugada) {
        this.valorJugada = valorJugada;
        this.cartasJugada = new ArrayList<>(cartasJugada);
    }

    public ValorJugada getValorJugada() {
        return this.valorJugada;
    }

    public List<Carta> getCartasJugada() {
        return this.cartasJugada;
    }

}
