package com.alphasystem.morphologicalengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ConjugationTuple {

    private String singular;

    private String dual;

    private String plural;

    public ConjugationTuple() {
        this(null, null, null);
    }

    public ConjugationTuple(String singular, String dual, String plural) {
        setSingular(singular);
        setDual(dual);
        setPlural(plural);
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public String getDual() {
        return dual;
    }

    public void setDual(String dual) {
        this.dual = dual;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public boolean empty() {
        return (singular == null) && (plural == null);
    }
}
