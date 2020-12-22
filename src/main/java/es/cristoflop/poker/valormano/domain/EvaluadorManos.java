package es.cristoflop.poker.valormano.domain;

import es.cristoflop.poker.valormano.domain.checkers.*;

public class EvaluadorManos {

    private static EvaluadorManos evaluadorManos;

    private final Checker checker;

    private EvaluadorManos() {
        this.checker = new CheckerCartaAlta();
        this.checker.linkWith(new CheckerPareja())
                .linkWith(new CheckerDoblePareja())
                .linkWith(new CheckerTrio())
                .linkWith(new CheckerEscalera())
                .linkWith(new CheckerColor())
                .linkWith(new CheckerFull())
                .linkWith(new CheckerPoker());
    }

    public static EvaluadorManos getInstance() {
        if (evaluadorManos == null)
            evaluadorManos = new EvaluadorManos();
        return evaluadorManos;
    }

    public Jugada evalua(Mano mano) {
        return this.checker.check(mano.getCartas());
    }

}
