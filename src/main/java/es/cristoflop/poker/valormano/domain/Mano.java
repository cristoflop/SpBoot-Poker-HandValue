package es.cristoflop.poker.valormano.domain;

import es.cristoflop.poker.valormano.exception.ParserException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    private static final int MAX_CARTAS = 5;

    private final List<Carta> manoOrdenada;
    private final List<Carta> manoSinOrden;

    public Mano(List<Carta> cartas) {
        this.manoSinOrden = new ArrayList<>(cartas);
        this.manoOrdenada = new ArrayList<>(this.manoSinOrden);
        this.manoOrdenada.sort(Comparator.comparing(Carta::getValor));
    }

    public Mano(String cartas, int n) {
        assert cartas.length() % 2 == 0;
        int i = 0;
        this.manoSinOrden = new ArrayList<>();
        while (this.manoSinOrden.size() != Mano.MAX_CARTAS) {
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


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Carta c : this.manoSinOrden) {
            result.append(c);
        }
        return result.toString();
    }

    /**************************************************************************************************/


    public static boolean esColor(ArrayList<Carta> e) {
        boolean color = true;
        int i = 1;
        while (color && i < e.size()) {
            if (!e.get(0).suited(e.get(i))) color = false;
            i++;
        }
        return color;
    }

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


    /**
     * Devuelve un array de cartas que forman color
     *
     * @return mejorColor o null, dependiendo de si hay o no color
     *
    public ArrayList<Carta> color() {

        ArrayList<Carta> color = new ArrayList<Carta>();
        ArrayList<Carta> mejorColor = new ArrayList<Carta>();
        int i = 0;
        while (i < mano.size() - 3 && mejorColor.size() < 5) { // mientras no tengamos color encontrado
            int j = i + 1;
            color.add(mano.get(i)); // empezamos a mirar
            while (j < mano.size() && color.size() < 5) {
                if (mano.get(i).suited(mano.get(j))) // encontramos carta del mismo color
                    color.add(mano.get(j));

                j++;
            }
            if (color.size() > mejorColor.size()) { // si tenemos un array mejor que el que ya habiamos guardado
                mejorColor.clear(); // desechamos el antiguo
                mejorColor.addAll(color); // a�adimos el nuevo
            }
            color.clear(); // vaciamos el array actual
            i++;
        }

        if (mejorColor.size() < 4)
            mejorColor = null;
        return mejorColor;

    }


    /**
     * metodo que devuelve las parejas que hay en una mano, como maximo 2 (doble pareja)
     *
     * @param indice pos desde la que empezar a buscar
     * @return la pareja o doble pareja o null si no hay
     *
    public ArrayList<Carta> pareja(int indice) {
        ArrayList<Carta> par = new ArrayList<Carta>();
        int i = indice;
        boolean encontrado = false;
        while (!encontrado && i < mano.size() - 1) {
            if (mano.get(i).getFigura() == mano.get(i + 1).getFigura()) {
                encontrado = true;
                par.add(mano.get(i));
                par.add(mano.get(i + 1));
            }
            i++;
        }
        if (par.size() != 2) {
            par = null;
        }
        return par;
    }


    /**
     * metodo que devuelve la primera posicion de una figura en una mano
     *
     * @param c carta a buscar
     * @return la posicion que ocupa o -1 si no esta
     *
    private int buscaEnManoFigura(Carta c) {
        int pos = 0;
        while (pos < mano.size() && mano.get(pos).getFigura() != c.getFigura()) {
            pos++;
        }
        if (pos == mano.size())
            pos = -1;
        return pos;
    }


    /**
     * metodo que devuelve las parejas que hay en una mano, como maximo 2 (doble pareja)
     *
     * @param indice pos desde la que empezar a buscar
     * @return la pareja, doble pareja o null si no hay
     *
    public ArrayList<Carta> doblesParejas(int indice) {
        ArrayList<Carta> par = new ArrayList<Carta>();
        int i = indice;
        while (i < mano.size() - 1 && par.size() < 4) {
            if (mano.get(i).getFigura() == mano.get(i + 1).getFigura()) {
                par.add(mano.get(i));
                par.add(mano.get(i + 1));
            }
            i++;
        }
        if (par.size() == 0 || par.size() % 2 != 0) {
            par = null;
        }
        return par;
    }


    /**
     * metodo que devuelve el mejor trio que hay en una mano
     *
     * @return el trio o null si no hay
     *
    public ArrayList<Carta> trio() {
        ArrayList<Carta> trio = new ArrayList<Carta>();
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < mano.size() - 2) {
            if (mano.get(i).getFigura() == mano.get(i + 2).getFigura()) {
                encontrado = true;
                trio.add(mano.get(i));
                trio.add(mano.get(i + 1));
                trio.add(mano.get(i + 2));
            }
            i++;
        }
        if (trio.size() != 3) {
            trio = null;
        }
        return trio;
    }


    /**
     * metodo que devuelve el mejor, si hay, full en la mano
     *
     * @return el full o null si no hay
     *
    public ArrayList<Carta> full() {
        ArrayList<Carta> full = trio(); // buscamos trio
        if (full != null) { // tenemos trio ya

            ArrayList<Carta> pareja = this.pareja(0); // buscamos pareja
            if (pareja.get(0).getFigura() == full.get(0).getFigura()) { // si hemos encontrado la pareja del trio
                pareja.clear(); // limpiamos el array
                int pos = this.buscaEnManoFigura(full.get(0)); // buscas la posicion de la carta
                pareja = this.pareja(pos + 2); // buscas la pareja a partir del trio
                if (pareja != null) { // si has encontrado una nueva pareja
                    full.addAll(pareja); // ya tienes full

                } else { // no quedan mas parejas --> no hay full en la mano
                    full = null;
                }
            } else {
                full.addAll(pareja); // tienes full
            }
        }

        return full;
    }


    /**
     * metodo que comprueba que tengamos un poker en la mano
     *
     * @return el array que contiene las cartas que forman el poker o null si no hay poker
     *
    public ArrayList<Carta> poker() {
        ArrayList<Carta> poker = new ArrayList<Carta>();
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < mano.size() - 3) {
            if (mano.get(i).getFigura() == mano.get(i + 3).getFigura()) {
                encontrado = true;
                poker.add(mano.get(i));
                poker.add(mano.get(i + 1));
                poker.add(mano.get(i + 2));
                poker.add(mano.get(i + 3));
            }
            i++;
        }
        if (poker.size() != 4)
            poker = null;
        return poker;
    }

    /**/

}
