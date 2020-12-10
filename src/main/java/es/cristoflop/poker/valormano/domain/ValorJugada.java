package es.cristoflop.poker.valormano.domain;

public enum ValorJugada {
    CARTA_ALTA,
    PAREJA,
    DOBLE_PAREJA,
    TRIO,
    ESCALERA,
    COLOR,
    FULL,
    POKER,
    ESCALERA_COLOR,
    ESCALERA_REAL;

    @Override
    public String toString() {
        return this.name();
    }
}
