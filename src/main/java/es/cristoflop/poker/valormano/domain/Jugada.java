package es.cristoflop.poker.valormano.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity
public class Jugada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ValorJugada valorJugada;

    private String cartasJugada;

    public Jugada() {
    }

    public Jugada(ValorJugada valorJugada, List<Carta> cartasJugada) {
        this.valorJugada = valorJugada;
        StringBuilder builder = new StringBuilder();
        cartasJugada.forEach(carta -> {
            builder.append(carta.toString());
        });
        this.cartasJugada = builder.toString();
    }

    public static Jugada nullJugada() {
        return new Jugada(ValorJugada.NULL, Collections.emptyList());
    }

    public long getId() {
        return this.id;
    }

    public ValorJugada getValorJugada() {
        return this.valorJugada;
    }

    public String getCartasJugada() {
        return this.cartasJugada;
    }

    public boolean isNull() {
        return this.valorJugada == ValorJugada.NULL;
    }

}
