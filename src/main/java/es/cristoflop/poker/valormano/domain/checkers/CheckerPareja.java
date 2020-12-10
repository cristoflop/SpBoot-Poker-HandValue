package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerPareja extends Checker {

    public CheckerPareja() {
        this.valorJugada = ValorJugada.PAREJA;
    }

    public List<Carta> cartasJugada(List<Carta> cartas) {
        // las cartas estan ordenadas de peor a mejor valor de la carta
        // mejor recorrer la lista al reves
        List<Carta> par = new ArrayList<>();
        int i = cartas.size() - 1;
        boolean found = false;
        while (i > 0 && !found) {
            if (cartas.get(i).getValor() == cartas.get(i - 1).getValor()) {
                found = true;
                par.add(cartas.get(i));
                par.add(cartas.get(i - 1));
            }
            i--;
        }
        return par;
    }

}
