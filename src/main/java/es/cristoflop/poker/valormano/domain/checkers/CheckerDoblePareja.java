package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckerDoblePareja extends Checker {

    public CheckerDoblePareja() {
        this.valorJugada = ValorJugada.DOBLE_PAREJA;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> dobles = new ArrayList<>();
        int i = cartas.size() - 1;
        int numPairs = 0;
        while (i > 0 && numPairs < 2) {
            if (cartas.get(i).getValor() == cartas.get(i - 1).getValor()) {
                numPairs++;
                dobles.add(cartas.get(i));
                dobles.add(cartas.get(i - 1));
                i--;
            }
            i--;
        }
        return numPairs == 2 ? dobles : Collections.emptyList();
    }

}
