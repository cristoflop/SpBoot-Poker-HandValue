package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public class CheckerColor extends Checker {

    public CheckerColor() {
        this.valorJugada = ValorJugada.COLOR;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        return null;
    }
}
