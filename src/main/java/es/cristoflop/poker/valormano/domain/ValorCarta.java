package es.cristoflop.poker.valormano.domain;

public enum ValorCarta {
    NULL(' '),
    DOS('2'),
    TRES('3'),
    CUATRO('4'),
    CINCO('5'),
    SEIS('6'),
    SIETE('7'),
    OCHO('8'),
    NUEVE('9'),
    DIEZ('T'),
    JACK('J'),
    DAMA('Q'),
    REY('K'),
    AS('A');

    private final char valor;

    ValorCarta(char valor) {
        this.valor = valor;
    }

    public static ValorCarta parse(char valor) {
        for (ValorCarta valorCarta : ValorCarta.values()) {
            if (valor == valorCarta.valor)
                return valorCarta;
        }
        return ValorCarta.NULL;
    }

    @Override
    public String toString() {
        return "" + this.valor + "";
    }
}
