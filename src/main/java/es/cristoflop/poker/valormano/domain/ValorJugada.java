package es.cristoflop.poker.valormano.domain;

public enum ValorJugada {
    CARTA_ALTA,
    PAREJA,
    DOBLEPAREJA,
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
