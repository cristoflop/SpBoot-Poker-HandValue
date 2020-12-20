package es.cristoflop.poker.valormano.domain;

public enum ColorCarta {
    NULL(' '),
    DIAMANTES('d'), // diamonds
    CORAZONES('h'), // hearts
    TREBOLES('c'), // clubs
    PICAS('s'); // spades

    private final char color;

    ColorCarta(char color) {
        this.color = Character.toLowerCase(color);
    }

    public static ColorCarta parse(char color) {
        for (ColorCarta colorCarta : ColorCarta.values()) {
            if (color == colorCarta.color)
                return colorCarta;
        }
        return ColorCarta.NULL;
    }

    @Override
    public String toString() {
        return "" + this.color + "";
    }
}
