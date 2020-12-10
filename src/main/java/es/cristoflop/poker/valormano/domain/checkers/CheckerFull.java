package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckerFull extends Checker {

    public CheckerFull() {
        this.valorJugada = ValorJugada.FULL;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> full = new ArrayList<>();
        int i = cartas.size() - 1;
        boolean pair = false;
        boolean trio = false;
        while (i > 0 && !(pair && trio)) {
            if (i > 1 && cartas.get(i).getValor() == cartas.get(i - 2).getValor()) { // comprobar trio
                trio = true;
                full.add(cartas.get(i));
                full.add(cartas.get(i - 1));
                full.add(cartas.get(i - 2));
                i = i - 2;
            } else if (cartas.get(i).getValor() == cartas.get(i - 1).getValor()) { // comprobar pareja
                pair = true;
                full.add(cartas.get(i));
                full.add(cartas.get(i - 1));
                i--;
            }
            i--;
        }
        return pair && trio ? full : Collections.emptyList();
    }
}
