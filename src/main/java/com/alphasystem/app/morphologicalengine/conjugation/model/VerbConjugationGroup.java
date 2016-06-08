package com.alphasystem.app.morphologicalengine.conjugation.model;

/**
 * @author sali
 */
public final class VerbConjugationGroup {

    private VerbConjugation masculineThirdPerson;

    private VerbConjugation feminineThirdPerson;

    private VerbConjugation masculineSecondPerson;

    private VerbConjugation feminineSecondPerson;

    private VerbConjugation firstPerson;

    public VerbConjugationGroup() {
        this(null, null, null, null, null);
    }

    public VerbConjugationGroup(VerbConjugation masculineThirdPerson, VerbConjugation feminineThirdPerson,
                                VerbConjugation masculineSecondPerson, VerbConjugation feminineSecondPerson, VerbConjugation firstPerson) {
        setMasculineThirdPerson(masculineThirdPerson);
        setFeminineThirdPerson(feminineThirdPerson);
        setMasculineSecondPerson(masculineSecondPerson);
        setFeminineSecondPerson(feminineSecondPerson);
        setFirstPerson(firstPerson);
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
}
