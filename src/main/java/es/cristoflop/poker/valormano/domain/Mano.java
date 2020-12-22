package es.cristoflop.poker.valormano.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    private final List<Carta> cartas;

    public Mano(List<Carta> cartas) {
        this.cartas = new ArrayList<>(cartas);
        this.cartas.sort(Comparator.comparing(Carta::getValor));
    }

    public Carta getCarta(int pos) {
        return this.cartas.get(pos);
    }

    public List<Carta> getCartas() {
        return this.cartas;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.cartas) {
            result.append(c);
        }
        return result.toString();
    }

}
