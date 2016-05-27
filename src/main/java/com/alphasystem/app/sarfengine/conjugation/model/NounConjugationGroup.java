package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public final class NounConjugationGroup {

    private RootWord nominative;

    private RootWord accusative;

    private RootWord genitive;

    public NounConjugationGroup() {
    }

    public NounConjugationGroup(RootWord nominative, RootWord accusative, RootWord genitive) {
        setNominative(nominative);
        setAccusative(accusative);
        setGenitive(genitive);
    }

    public RootWord getNominative() {
        return nominative;
    }

    public void setNominative(RootWord nominative) {
        this.nominative = nominative;
    }

    public RootWord getAccusative() {
        return accusative;
    }

    public void setAccusative(RootWord accusative) {
        this.accusative = accusative;
    }

    public RootWord getGenitive() {
        return genitive;
    }

    public void setGenitive(RootWord genitive) {
        this.genitive = genitive;
    }
}
