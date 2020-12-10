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
        assert cartas.size() > 0;
        Jugada mejorJugada = this.checkNext(cartas);
        if (mejorJugada == null) {
            List<Carta> cartasJugada = this.cartasJugada(cartas);
            if (cartasJugada != null && !cartasJugada.isEmpty()) {
                return new Jugada(this.valorJugada, cartasJugada);
            } else {
                return new Jugada(cartas.get(cartas.size() - 1));
            }
        } else {
            return mejorJugada;
        }
    }

    protected Jugada checkNext(List<Carta> cartas) {
        if (this.next == null) {
            return null;
        }
        return next.check(cartas);
    }

    protected abstract List<Carta> cartasJugada(List<Carta> cartas);

}
