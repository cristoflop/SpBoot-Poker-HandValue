package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public class CheckerDoblePareja extends Checker {

    public CheckerDoblePareja() {
        this.valorJugada = ValorJugada.DOBLE_PAREJA;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        return null;
    }
}
