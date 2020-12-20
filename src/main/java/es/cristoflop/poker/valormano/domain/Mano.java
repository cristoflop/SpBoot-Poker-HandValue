package es.cristoflop.poker.valormano.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    private final List<Carta> manoOrdenada;
    private final List<Carta> manoSinOrden;

    private final EvaluadorManos evaluadorManos;

    public Mano(List<Carta> cartas) {
        this.evaluadorManos = EvaluadorManos.getInstance();
        this.manoSinOrden = new ArrayList<>(cartas);
        this.manoOrdenada = new ArrayList<>(this.manoSinOrden);
        this.manoOrdenada.sort(Comparator.comparing(Carta::getValor));
    }

    public Carta getCarta(int pos) {
        return this.manoSinOrden.get(pos);
    }

    public Carta getMejorCarta(int pos) {
        return this.manoOrdenada.get(pos);
    }

    public List<Carta> getManoSinOrden() {
        return this.manoSinOrden;
    }

    public Jugada getJugada() {
        return this.evaluadorManos.check(this.manoOrdenada);
    }

    public String toStringOrdenado() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoOrdenada) {
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoSinOrden) {
            result.append(c);
        }
        return result.toString();
    }

}
