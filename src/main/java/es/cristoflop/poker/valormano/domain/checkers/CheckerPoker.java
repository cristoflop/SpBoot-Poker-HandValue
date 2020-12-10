package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public class CheckerPoker extends Checker {

    public CheckerPoker() {
        this.valorJugada = ValorJugada.POKER;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        return null;
    }
}
