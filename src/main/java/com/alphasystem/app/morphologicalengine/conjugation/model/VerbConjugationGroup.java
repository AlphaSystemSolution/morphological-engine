package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public final class VerbConjugationGroup {

    private VerbConjugation masculineThirdPerson;

    private VerbConjugation feminineThirdPerson;

    private VerbConjugation masculineSecondPerson;

    private VerbConjugation feminineSecondPerson;

    private VerbConjugation firstPerson;

    private SarfTermType termType;

    public VerbConjugationGroup() {
    }

    public VerbConjugation getMasculineThirdPerson() {
        return masculineThirdPerson;
    }

    public void setMasculineThirdPerson(VerbConjugation masculineThirdPerson) {
        this.masculineThirdPerson = masculineThirdPerson;
    }

    public VerbConjugation getFeminineThirdPerson() {
        return feminineThirdPerson;
    }

    public void setFeminineThirdPerson(VerbConjugation feminineThirdPerson) {
        this.feminineThirdPerson = feminineThirdPerson;
    }

    public VerbConjugation getMasculineSecondPerson() {
        return masculineSecondPerson;
    }

    public void setMasculineSecondPerson(VerbConjugation masculineSecondPerson) {
        this.masculineSecondPerson = masculineSecondPerson;
    }

    public VerbConjugation getFeminineSecondPerson() {
        return feminineSecondPerson;
    }

    public void setFeminineSecondPerson(VerbConjugation feminineSecondPerson) {
        this.feminineSecondPerson = feminineSecondPerson;
    }

    public VerbConjugation getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(VerbConjugation firstPerson) {
        this.firstPerson = firstPerson;
    }


    public SarfTermType getTermType() {
        return termType;
    }

    public void setTermType(SarfTermType termType) {
        this.termType = termType;
    }
}
