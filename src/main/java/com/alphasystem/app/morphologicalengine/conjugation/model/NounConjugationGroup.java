package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public final class NounConjugationGroup {

    private NounConjugation singular;

    private NounConjugation dual;

    private NounConjugation plural;

    private SarfTermType termType;

    public NounConjugationGroup() {
    }

    public NounConjugation getSingular() {
        return singular;
    }

    public void setSingular(NounConjugation singular) {
        this.singular = singular;
    }

    public NounConjugation getDual() {
        return dual;
    }

    public void setDual(NounConjugation dual) {
        this.dual = dual;
    }

    public NounConjugation getPlural() {
        return plural;
    }

    public void setPlural(NounConjugation plural) {
        this.plural = plural;
    }

    public SarfTermType getTermType() {
        return termType;
    }

    public void setTermType(SarfTermType termType) {
        this.termType = termType;
    }
}
