package es.cristoflop.poker.valormano.domain;

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
        return new Mano(this.cartas.toString());
    }

}
