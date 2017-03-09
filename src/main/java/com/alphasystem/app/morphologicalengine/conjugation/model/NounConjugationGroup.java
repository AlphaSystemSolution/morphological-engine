package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public final class NounConjugationGroup extends ConjugationGroup {

    private ConjugationTuple nominative;

    private ConjugationTuple accusative;

    private ConjugationTuple genitive;

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

    @Override
    public RootWord getDefaultValue() {
        return (nominative == null) ? null : nominative.getSingular();
    }

    @Override
    public boolean isEmpty() {
        return (nominative == null || nominative.isEmpty()) && (accusative == null || accusative.isEmpty()) &&
                (genitive == null || genitive.isEmpty());
    }

}
