package es.cristoflop.poker.valormano.domain;

import es.cristoflop.poker.valormano.exception.ParserException;

import java.util.Objects;

public class Carta {

    private final ValorCarta valor;
    private final ColorCarta color;

    public Carta(char valor, char color) {
        this.valor = ValorCarta.parse(valor);
        this.color = ColorCarta.parse(color);
        if (this.valor == ValorCarta.NULL || this.color == ColorCarta.NULL)
            throw new ParserException("El formato de la carta " + this.toString() + " no es valido");
    }

    private boolean esColorValido() {
        return this.color != ColorCarta.NULL;
    }

    private boolean esFiguraValida() {
        return this.valor != ValorCarta.NULL;
    }

    public boolean esCartaValida() {
        return this.esColorValido() && this.esFiguraValida();
    }

    public boolean suited(Carta other) {
        return this.color == other.getColor();
    }

    public boolean esSiguiente(Carta other) {
        return this.getValor().esSiguiente(other.getValor());
    }

    public ColorCarta getColor() {
        return this.color;
    }

    public ValorCarta getValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return this.valor.toString() + this.color.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return valor == carta.valor && color == carta.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, color);
    }
}
	

	

