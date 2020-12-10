package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerPoker extends Checker {

    public CheckerPoker() {
        this.valorJugada = ValorJugada.POKER;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> poker = new ArrayList<>();
        int i = cartas.size() - 1;
        boolean found = false;
        while (i > 2 && !found) {
            if (cartas.get(i).getValor() == cartas.get(i - 3).getValor()) {
                found = true;
                poker.add(cartas.get(i));
                poker.add(cartas.get(i - 1));
                poker.add(cartas.get(i - 2));
                poker.add(cartas.get(i - 3));
            }
            i--;
        }
        return poker;
    }
}
