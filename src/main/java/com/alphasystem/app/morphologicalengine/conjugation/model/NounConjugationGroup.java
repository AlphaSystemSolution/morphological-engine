package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public final class NounConjugationGroup {

    private ConjugationTuple nominative;

    private ConjugationTuple accusative;

    private ConjugationTuple genitive;

    private SarfTermType termType;

    public NounConjugationGroup() {
    }

    public ConjugationTuple getNominative() {
        return nominative;
    }

    public void setNominative(ConjugationTuple nominative) {
        this.nominative = nominative;
    }

    public ConjugationTuple getAccusative() {
        return accusative;
    }

    public void setAccusative(ConjugationTuple accusative) {
        this.accusative = accusative;
    }

    public ConjugationTuple getGenitive() {
        return genitive;
    }

    public void setGenitive(ConjugationTuple genitive) {
        this.genitive = genitive;
    }

    public SarfTermType getTermType() {
        return termType;
    }

    public void setTermType(SarfTermType termType) {
        this.termType = termType;
    }
}
