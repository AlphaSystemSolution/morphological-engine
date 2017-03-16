package com.alphasystem.morphologicalengine.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public class ConjugationGroup {

    private SarfTermType termType;

    public SarfTermType getTermType() {
        return termType;
    }

    public void setTermType(SarfTermType termType) {
        this.termType = termType;
    }

    public String defaultValue() {
        return null;
    }

    public boolean empty() {
        return false;
    }
}
