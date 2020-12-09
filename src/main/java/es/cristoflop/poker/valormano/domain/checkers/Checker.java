package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.Jugada;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public abstract class Checker {

    protected Checker next;
    protected Jugada jugada;

    public Checker linkWith(Checker next) {
        this.next = next;
        return next;
    }

    public abstract Jugada check(List<Carta> cartas);

    protected Jugada checkNext(List<Carta> cartas) {
        if (this.next == null) {
            return null;
        }
        return next.check(cartas);
    }

    public Jugada getJugada() {
        return this.jugada;
    }
}
