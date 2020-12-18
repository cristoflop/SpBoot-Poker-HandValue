package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorCarta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckerEscalera extends Checker {

    private final ValorJugada regular = ValorJugada.ESCALERA;
    private final ValorJugada color = ValorJugada.ESCALERA_COLOR;
    private final ValorJugada royal = ValorJugada.ESCALERA_REAL;

    public CheckerEscalera() {
        this.valorJugada = this.regular;
    }

    @Override
    protected List<Carta> cartasJugada(List<Carta> cartas) {
        List<List<Carta>> posiblesEscaleras = new ArrayList<>();

        for (Carta carta : cartas) { // posibles escaleras de tamaño 1
            List<Carta> aux = new ArrayList<>();
            aux.add(carta);
            posiblesEscaleras.add(aux);
        }

        int tamEscaleras = 1;
        while (tamEscaleras < 5 && !posiblesEscaleras.isEmpty()) {
            posiblesEscaleras = this.addUnaCartaMasEnPosiblesEscalerasSiSePuede(posiblesEscaleras, cartas);
            tamEscaleras++;
        }

        return posiblesEscaleras.isEmpty() ? Collections.emptyList() : this.mejorEscalera(posiblesEscaleras);
    }

    private List<List<Carta>> addUnaCartaMasEnPosiblesEscalerasSiSePuede(List<List<Carta>> posiblesEscaleras, List<Carta> cartas) {
        List<List<Carta>> nuevasEscaleras = new ArrayList<>();
        for (List<Carta> posibleEscaleraActual : posiblesEscaleras) {
            Carta ultimaCarta = posibleEscaleraActual.get(posibleEscaleraActual.size() - 1);
            for (Carta carta : cartas) {
                if (ultimaCarta.esSiguiente(carta)) {
                    List<Carta> nuevaPosibleEscalera = new ArrayList<>(posibleEscaleraActual);
                    nuevaPosibleEscalera.add(carta);
                    nuevasEscaleras.add(nuevaPosibleEscalera);
                }
            }
        }
        return nuevasEscaleras;
    }

    private void muestra(int iteracion, List<List<Carta>> manos) {
        System.out.println("Iteracion numero " + iteracion + " - POSIBLES ESCALERAS");
        for (List<Carta> cartas : manos) {
            for (Carta c : cartas) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("---------------------------");
        System.out.println();
    }

    private List<Carta> mejorEscalera(List<List<Carta>> escaleras) {
        assert !escaleras.isEmpty();
        List<Carta> mejorEscalera = escaleras.get(0);
        this.valorJugada = this.valorEscalera(mejorEscalera);
        for (List<Carta> escalera : escaleras) {
            ValorJugada valorJugada = this.valorEscalera(escalera);
            if (valorJugada.ordinal() > this.valorJugada.ordinal()) {
                mejorEscalera = escalera;
                this.valorJugada = valorJugada;
            }
        }
        return mejorEscalera;
    }

    private ValorJugada valorEscalera(List<Carta> cartas) {
        assert !cartas.isEmpty();
        if (this.esEscaleraReal(cartas))
            return this.royal;
        else if (this.esEscaleraDeColor(cartas))
            return this.color;
        else
            return this.regular;
    }

    private boolean esEscaleraDeColor(List<Carta> cartas) {
        assert !cartas.isEmpty();
        for (int i = 1; i < cartas.size(); i++) {
            if (cartas.get(i - 1).suited(cartas.get(i)))
                return false;
        }
        return true;
    }

    private boolean esEscaleraReal(List<Carta> cartas) {
        return this.esEscaleraDeColor(cartas) &&
                cartas.get(0).getValor() == ValorCarta.AS &&
                cartas.get(1).getValor() == ValorCarta.REY;
    }

}
