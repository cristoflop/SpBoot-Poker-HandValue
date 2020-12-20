package es.cristoflop.poker.valormano.domain;

import es.cristoflop.poker.valormano.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

public class ManoBuilder {

    private final StringBuilder cartas;

    public ManoBuilder() {
        this.cartas = new StringBuilder();
    }

    ManoBuilder add(String carta) {
        this.cartas.append(carta);
        return this;
    }

    Mano build() {
        return new Mano(this.parse());
    }

    List<Carta> parse() {
        List<Carta> cartas = new ArrayList<>();

        int size = this.cartas.length();
        if (size % 2 != 0) {
            throw new ParserException("Formato invalido de mano");
        }

        for (int i = 0; i < size; i = i + 2) {
            String cartaAux = this.cartas.substring(i, i + 2);
            Carta carta = new Carta(cartaAux.charAt(0), cartaAux.charAt(1));
            cartas.add(carta);
        }

        return cartas;
    }

}
