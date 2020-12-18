package es.cristoflop.poker.valormano.domain.checkers;

import es.cristoflop.poker.valormano.domain.Carta;
import es.cristoflop.poker.valormano.domain.ValorJugada;

import java.util.ArrayList;
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

        for (int i = 0; i < cartas.size(); i++) { // posibles escaleras de tamaño 1
            List<Carta> aux = new ArrayList<>();
            aux.add(cartas.get(i));
            posiblesEscaleras.add(aux);
        }

        int tamEscaleras = 1;
        this.muestra(tamEscaleras, posiblesEscaleras);
        while (tamEscaleras < 5 && !posiblesEscaleras.isEmpty()) {
            posiblesEscaleras = this.addUnaCartaMasEnPosiblesEscalerasSiSePuede(posiblesEscaleras, cartas);
            tamEscaleras++;
            this.muestra(tamEscaleras, posiblesEscaleras);
        }

        List<Carta> bestStair = null;
        if (!posiblesEscaleras.isEmpty()) bestStair = posiblesEscaleras.get(0);

        return bestStair;
    }

    private List<List<Carta>> addUnaCartaMasEnPosiblesEscalerasSiSePuede(List<List<Carta>> posiblesEscaleras, List<Carta> cartas) {
        List<List<Carta>> nuevasEscaleras = new ArrayList<>();
        for (int i = 0; i < posiblesEscaleras.size(); i++) {
            List<Carta> posibleEscaleraActual = posiblesEscaleras.get(i);
            Carta ultimaCarta = posibleEscaleraActual.get(posibleEscaleraActual.size() - 1);
            for (int j = 0; j < cartas.size(); j++) {
                if (ultimaCarta.esSiguiente(cartas.get(j))) {
                    List<Carta> nuevaPosibleEscalera = new ArrayList<>(posibleEscaleraActual);
                    nuevaPosibleEscalera.add(cartas.get(j));
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

}
