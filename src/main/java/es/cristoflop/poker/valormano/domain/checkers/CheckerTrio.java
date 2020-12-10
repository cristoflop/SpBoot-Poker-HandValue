package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public class CheckerTrio extends Checker {

    public CheckerTrio() {
        this.valorJugada = ValorJugada.TRIO;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        return null;
    }
}
