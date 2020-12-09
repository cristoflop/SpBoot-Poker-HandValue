package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.Jugada;

import java.util.ArrayList;
import java.util.List;

public class CheckerPareja extends Checker {

    @Override
    public Jugada check(List<Carta> cartas) {

        return null;
    }

    public List<Carta> pareja(List<Carta> cartas, int indice) {
        List<Carta> par = new ArrayList<>();
        int i = indice;
        boolean encontrado = false;
        while (!encontrado && i < cartas.size() - 1) {
            if (cartas.get(i).getValor() == cartas.get(i + 1).getValor()) {
                encontrado = true;
                par.add(cartas.get(i));
                par.add(cartas.get(i + 1));
            }
            i++;
        }
        if (par.size() != 2) {
            par = null;
        }
        return par;
    }

}
