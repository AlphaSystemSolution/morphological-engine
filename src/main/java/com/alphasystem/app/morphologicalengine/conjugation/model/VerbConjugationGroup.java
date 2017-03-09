package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public final class VerbConjugationGroup extends ConjugationGroup {

    private ConjugationTuple masculineThirdPerson;

    private ConjugationTuple feminineThirdPerson;

    private ConjugationTuple masculineSecondPerson;

    private ConjugationTuple feminineSecondPerson;

    private ConjugationTuple firstPerson;

    public VerbConjugationGroup() {
    }

    public ConjugationTuple getMasculineThirdPerson() {
        return masculineThirdPerson;
    }

    public void setMasculineThirdPerson(ConjugationTuple masculineThirdPerson) {
        this.masculineThirdPerson = masculineThirdPerson;
    }

    public ConjugationTuple getFeminineThirdPerson() {
        return feminineThirdPerson;
    }

    public void setFeminineThirdPerson(ConjugationTuple feminineThirdPerson) {
        this.feminineThirdPerson = feminineThirdPerson;
    }

    public ConjugationTuple getMasculineSecondPerson() {
        return masculineSecondPerson;
    }

    public void setMasculineSecondPerson(ConjugationTuple masculineSecondPerson) {
        this.masculineSecondPerson = masculineSecondPerson;
    }

    public ConjugationTuple getFeminineSecondPerson() {
        return feminineSecondPerson;
    }

    public void setFeminineSecondPerson(ConjugationTuple feminineSecondPerson) {
        this.feminineSecondPerson = feminineSecondPerson;
    }

    public ConjugationTuple getFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(ConjugationTuple firstPerson) {
        this.firstPerson = firstPerson;
    }

    @Override
    public RootWord getDefaultValue() {
        return (masculineThirdPerson == null) ? null : masculineThirdPerson.getSingular();
    }

    @Override
    public boolean isEmpty() {
        return (masculineSecondPerson == null || masculineThirdPerson.isEmpty()) &&
                (feminineThirdPerson == null || feminineThirdPerson.isEmpty()) &&
                (masculineSecondPerson == null || masculineSecondPerson.isEmpty()) &&
                (feminineSecondPerson == null || feminineSecondPerson.isEmpty()) &&
                (firstPerson == null || firstPerson.isEmpty());
    }
}
