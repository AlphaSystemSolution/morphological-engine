package com.alphasystem.morphologicalengine.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
    public RootWord defaultValue() {
        RootWord defaultValue;
        if(SarfTermType.VERBAL_NOUN.equals(getTermType()))
            defaultValue = accusative == null ? null : accusative.getSingular();
        else {
            defaultValue = (nominative == null) ? null : nominative.getSingular();
        }
        return defaultValue;
    }

    @Override
    public boolean empty() {
        return (nominative == null || nominative.empty()) && (accusative == null || accusative.empty()) &&
                (genitive == null || genitive.empty());
    }

}
