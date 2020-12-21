package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.Jugada;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.List;

public abstract class Checker {

    protected Checker next;
    protected ValorJugada valorJugada;

    public Checker linkWith(Checker next) {
        this.next = next;
        return next;
    }

    public Jugada check(List<Carta> cartas) {
        Jugada mejorJugada = this.checkNext(cartas);
        if (mejorJugada.isNull()) {
            List<Carta> cartasJugada = this.cartasJugada(cartas);
            if (cartasJugada != null && !cartasJugada.isEmpty()) {
                return new Jugada(this.valorJugada, cartasJugada);
            }
        } else {
            return mejorJugada;
        }
        return Jugada.nullJugada();
    }

    protected Jugada checkNext(List<Carta> cartas) {
        if (this.next == null) {
            return Jugada.nullJugada();
        }
        return next.check(cartas);
    }

    protected abstract List<Carta> cartasJugada(List<Carta> cartas);

}
