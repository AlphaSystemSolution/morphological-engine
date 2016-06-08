package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public final class VerbConjugation {

    private RootWord singular;

    private RootWord dual;

    private RootWord plural;

    public VerbConjugation() {
        this(null, null, null);
    }

    public VerbConjugation(RootWord singular, RootWord dual, RootWord plural) {
        setSingular(singular);
        setDual(dual);
        setPlural(plural);
    }

    public RootWord getSingular() {
        return singular;
    }

    public void setSingular(RootWord singular) {
        this.singular = singular;
    }

    public RootWord getDual() {
        return dual;
    }

    public void setDual(RootWord dual) {
        this.dual = dual;
    }

    public RootWord getPlural() {
        return plural;
    }

    public void setPlural(RootWord plural) {
        this.plural = plural;
    }
}
