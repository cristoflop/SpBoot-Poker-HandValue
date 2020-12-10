package es.cristoflop.poker.valormano.domain;

import es.cristoflop.poker.valormano.domain.checkers.*;
import es.cristoflop.poker.valormano.exception.ParserException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    private final List<Carta> manoOrdenada;
    private final List<Carta> manoSinOrden;

    public Mano(List<Carta> cartas) {
        assert cartas.size() % 2 == 0;
        this.manoSinOrden = new ArrayList<>(cartas);
        this.manoOrdenada = new ArrayList<>(this.manoSinOrden);
        this.manoOrdenada.sort(Comparator.comparing(Carta::getValor));
    }

    public Mano(String cartas) {
        assert cartas.length() % 2 == 0;
        int max = cartas.length() / 2;
        int i = 0;
        this.manoSinOrden = new ArrayList<>();
        while (this.manoSinOrden.size() != max) {
            Carta carta = new Carta(cartas.charAt(i), cartas.charAt(i + 1));
            if (!carta.esCartaValida())
                throw new ParserException("Se ha encontrado al menos una carta que no es valida");
            manoSinOrden.add(carta);
            i += 2;
        }
        this.manoOrdenada = new ArrayList<>(this.manoSinOrden);
        this.manoOrdenada.sort(Comparator.comparing(Carta::getValor));
    }

    public Carta getCarta(int pos) {
        return this.manoSinOrden.get(pos);
    }

    public Carta getMejorCarta(int pos) {
        return this.manoOrdenada.get(pos);
    }

    public List<Carta> getManoSinOrden() {
        return this.manoSinOrden;
    }

    public Jugada getJugada() {
        Checker checker = new CheckerCartaAlta();
        checker.linkWith(new CheckerPareja())
                .linkWith(new CheckerDoblePareja())
                .linkWith(new CheckerTrio())
                .linkWith(new CheckerEscalera())
                .linkWith(new CheckerColor())
                .linkWith(new CheckerFull())
                .linkWith(new CheckerPoker());

        return checker.check(this.manoOrdenada);
    }

    /**
     * metodo que devuelve un array para la salida del apartado 2
     *
     * @param j array con la mejor jugada de la mano
     * @return el array con la mano de 5 cartas que el jugador juega
     */
    public Mano normaliza(ArrayList<Carta> j) {
        ArrayList<Carta> ord = new ArrayList<Carta>(this.manoOrdenada);
        ArrayList<Carta> desOrd = new ArrayList<Carta>(this.manoSinOrden);
        int i = ord.size() - 1;
        while (ord.size() > 5) {
            if (!j.contains(ord.get(i)))
                ord.remove(i);
            i--;
        }
        desOrd.retainAll(ord);
        return new Mano(desOrd);
    }

    public String toStringOrdenado(){
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoOrdenada) {
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoSinOrden) {
            result.append(c);
        }
        return result.toString();
    }

    /**************************************************************************************************/

    /*
    /**
     * Comprueba si la escalera es real
     *
     * @param e: Array de cartas a comprobar
     * @return True o False dependiendo de si la escalera es real o no
     *
    public static boolean esReal(ArrayList<Carta> e) {
        return esColor(e) && e.size() >= 2 && e.get(0).getFigura() == 'A' && e.get(1).getFigura() == 'K';
    }*/

    /**
     * Comprueba si el draw de escalera es el denominado Open Ended
     *
     * @param e: Array de cartas a comprobar
     * @return True o False dependiendo de si es o no open ended
     *
    public static boolean openEnded(ArrayList<Carta> e) {
        boolean loEs = true;
        if (e.get(0).getValor() == ValorCarta.AS) {
            if (e.get(1).getFigura() == '4' || e.get(1).getFigura() == 'K') {
                int i = 1;
                while (loEs && i < e.size() - 1) {
                    if (e.get(i).getValor() - 1 != e.get(i + 1).getValor()) loEs = false;
                    i++;
                }
            } else {
                loEs = false;
            }
        } else {
            int j = 0;
            while (loEs && j < e.size() - 1) {
                if (e.get(j).getValor() - 1 != e.get(j + 1).getValor()) loEs = false;
                j++;
            }
        }
        return loEs;
    }

    /**
     * Compara dos manos con escaleras para determinar la mejor
     *
     * @param e1: Array de cartas 1 a comprobar
     * @param e2: Array de cartas 2 a comprobar
     * @return True o False dependiendo de si e1 es una escalera mejor que e2
     *
    private boolean esMejorEscalera(ArrayList<Carta> e1, ArrayList<Carta> e2) {
        boolean esMejor = false;
        if (e1.size() > e2.size())
            esMejor = true;
        else if (e1.size() == e2.size()) {
            if (esReal(e1)) {
                if (!esReal(e2)) esMejor = true;
            } else if (esColor(e1)) {
                if (!esColor(e2)) esMejor = true;
            } else {
                if (!esColor(e2)) {
                    if (e1.get(0).getValor() > e2.get(0).getValor()) esMejor = true;
                    else if (e1.get(0).getValor() == e2.get(0).getValor()) {
                        if (e1.size() > 1 && e1.get(1).getValor() > e2.get(1).getValor()) esMejor = true;
                    }
                }
            }
        }
        return esMejor;
    }

    /**
     * Devuelve un array de cartas que forman una escalera
     *
     * @return mejorEscalera o null, dependiendo de si hay o no escalera
     *
    public ArrayList<Carta> escalera() {
        ArrayList<Carta> escalera = new ArrayList<Carta>();
        ArrayList<Carta> mejorEscalera = new ArrayList<Carta>();
        for (int i = 0; i < this.mano.size() - 3; ++i) {
            int gutshot = 1;
            int j = i + 1;
            escalera.add(mano.get(i));
            boolean parar = false;
            while (!parar && j < mano.size() && escalera.size() < 5) {
                Carta cola = escalera.get(escalera.size() - 1);
                Carta actual = mano.get(j);
                if (cola.getFigura() == 'A') { // tenemos un as en la cola
                    if (actual.getFigura() == '5') {
                        escalera.add(actual);
                    } else if (actual.getFigura() == '4') {
                        gutshot--;
                        escalera.add(actual);
                    } else {
                        int v = actual.getValor();
                        if (v + 1 == cola.getValor()) {
                            escalera.add(actual);
                        } else if (v + 2 == cola.getValor()) {
                            gutshot--;
                            escalera.add(actual);
                        }
                    }
                } else { // cualquier otra carta en la cola
                    if (cola.getValor() == actual.getValor()) { // 2 cartas iguales
                        if (escalera.size() > 1) {
                            if (actual.suited(escalera.get(escalera.size() - 2))) {
                                escalera.remove(escalera.size() - 1);
                                escalera.add(actual);
                            }
                        }
                    } else if (cola.getValor() == actual.getValor() + 1) { // siguiente carta
                        if (escalera.size() == 4 && gutshot == 0) { // si ya hay un gutshot de escalera
                            parar = true;
                        } else {
                            escalera.add(actual);
                        }
                    } else if (cola.getValor() == actual.getValor() + 2) { // siguiente de la siguiente
                        if (gutshot > 0) {
                            gutshot--;
                            escalera.add(actual);
                        } else {
                            parar = true;
                        }
                    }
                }
                j++;
            }
            if (esMejorEscalera(escalera, mejorEscalera)) {
                mejorEscalera.clear();
                mejorEscalera.addAll(escalera);
            }
            escalera.clear();
        }
        if (mejorEscalera.size() < 4)
            mejorEscalera = null;
        return mejorEscalera;
    }

    /**/

}
