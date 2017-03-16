package com.alphasystem.morphologicalengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NounConjugation {

    private String nominative;

    private String accusative;

    private String genitive;

    public NounConjugation() {
    }

    public NounConjugation(String nominative, String accusative, String genitive) {
        setNominative(nominative);
        setAccusative(accusative);
        setGenitive(genitive);
    }

    public String getNominative() {
        return nominative;
    }

    public void setNominative(String nominative) {
        this.nominative = nominative;
    }

    public String getAccusative() {
        return accusative;
    }

    public void setAccusative(String accusative) {
        this.accusative = accusative;
    }

    public String getGenitive() {
        return genitive;
    }

    public void setGenitive(String genitive) {
        this.genitive = genitive;
    }
}
