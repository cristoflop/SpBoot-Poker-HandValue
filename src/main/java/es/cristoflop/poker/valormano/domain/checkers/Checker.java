package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;

import java.util.List;

public abstract class Checker {

    private Checker next;

    public Checker linkWith(Checker next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(List<Carta> cartas);

    protected boolean checkNext(List<Carta> cartas) {
        if (next == null) {
            return true;
        }
        return next.check(cartas);
    }

}
