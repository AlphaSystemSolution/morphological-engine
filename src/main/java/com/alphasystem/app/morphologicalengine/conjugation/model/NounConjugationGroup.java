package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

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
        RootWord defaultValue;
        if(SarfTermType.VERBAL_NOUN.equals(getTermType()))
            defaultValue = accusative == null ? null : accusative.getSingular();
        else {
            defaultValue = (nominative == null) ? null : nominative.getSingular();
        }
        return defaultValue;
    }

    @Override
    public boolean isEmpty() {
        return (nominative == null || nominative.isEmpty()) && (accusative == null || accusative.isEmpty()) &&
                (genitive == null || genitive.isEmpty());
    }

}
