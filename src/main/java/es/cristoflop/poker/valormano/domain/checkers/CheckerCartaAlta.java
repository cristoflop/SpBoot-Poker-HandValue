package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerCartaAlta extends Checker {

    public CheckerCartaAlta() {
        this.valorJugada = ValorJugada.CARTA_ALTA;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> cartaAlta = new ArrayList<>();
        cartaAlta.add(cartaAlta.get(cartaAlta.size() - 1));
        return cartaAlta;
    }
}
