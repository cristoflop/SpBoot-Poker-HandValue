package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public class CheckerEscalera extends Checker {

    public CheckerEscalera() {
        this.valorJugada = ValorJugada.ESCALERA;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        return null;
    }
}
