package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.fill;

public class CheckerColor extends Checker {

    public CheckerColor() {
        this.valorJugada = ValorJugada.COLOR;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> color = new ArrayList<>();
        boolean[] marcados = new boolean[cartas.size()];
        fill(marcados, false);
        boolean found = false;
        int i = cartas.size() - 1;
        while (i > 3 && !found) {
            if (!marcados[i]) {
                List<Carta> posibleColor = new ArrayList<>();
                int j = i;
                while (j >= 0 && posibleColor.size() < 5) {
                    if (cartas.get(i).getColor() == cartas.get(j).getColor()) {
                        marcados[j] = true;
                        posibleColor.add(cartas.get(j));
                    }
                    j--;
                }
                if (posibleColor.size() == 5) {
                    found = true;
                    color.addAll(posibleColor);
                }
            }
            i--;
        }
        return color;
    }
}
