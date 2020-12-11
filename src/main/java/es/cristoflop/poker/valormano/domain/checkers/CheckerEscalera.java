package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerEscalera extends Checker {

    public CheckerEscalera() {
        this.valorJugada = ValorJugada.ESCALERA;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<Carta> stair = new ArrayList<>();
        boolean found = false;
        int i = cartas.size() - 1;
        while (i > 3 && !found) {
            List<Carta> posibleStair = new ArrayList<>();
            posibleStair.add(cartas.get(i));
            int j = i - 1;
            while (j >= 0 && posibleStair.size() < 5) {
                if (posibleStair.get(posibleStair.size() - 1).esSiguiente(cartas.get(j))) {
                    posibleStair.add(cartas.get(j));
                }
                j--;
            }
            if (posibleStair.size() == 5) {
                found = true;
                stair.addAll(posibleStair);
            }
            i--;
        }
        return stair;
    }

}
