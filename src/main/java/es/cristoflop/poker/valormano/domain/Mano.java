package es.cristoflop.poker.valormano.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    private final List<Carta> manoOrdenada;

    private final EvaluadorManos evaluadorManos;

    public Mano(List<Carta> cartas) {
        this.evaluadorManos = EvaluadorManos.getInstance();
        this.manoOrdenada = new ArrayList<>(cartas);
        this.manoOrdenada.sort(Comparator.comparing(Carta::getValor));
    }

    public Carta getCarta(int pos) {
        return this.manoOrdenada.get(pos);
    }

    public Jugada getJugada() {
        return this.evaluadorManos.check(this.manoOrdenada);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoOrdenada) {
            result.append(c);
        }
        return result.toString();
    }

}
