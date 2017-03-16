package com.alphasystem.morphologicalengine.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NounConjugation {

    private RootWord nominative;

    private RootWord accusative;

    private RootWord genitive;

    public NounConjugation() {
    }

    public NounConjugation(RootWord nominative, RootWord accusative, RootWord genitive) {
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