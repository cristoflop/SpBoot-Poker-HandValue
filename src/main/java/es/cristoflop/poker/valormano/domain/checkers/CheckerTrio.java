package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerTrio extends Checker {

    public CheckerTrio() {
        this.valorJugada = ValorJugada.TRIO;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> trio = new ArrayList<>();
        int i = cartas.size() - 1;
        boolean found = false;
        while (i > 1 && !found) {
            if (cartas.get(i).getValor() == cartas.get(i - 2).getValor()) {
                found = true;
                trio.add(cartas.get(i));
                trio.add(cartas.get(i - 1));
                trio.add(cartas.get(i - 2));
            }
            i--;
        }
        return trio;
    }

}
