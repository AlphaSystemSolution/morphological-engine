package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
    public RootWord defaultValue() {
        RootWord defaultValue;
        if (SarfTermType.IMPERATIVE.equals(getTermType()) || SarfTermType.FORBIDDING.equals(getTermType())) {
            defaultValue = (masculineSecondPerson == null) ? null : masculineSecondPerson.getSingular();
        } else {
            defaultValue = (masculineThirdPerson == null) ? null : masculineThirdPerson.getSingular();
        }
        return defaultValue;
    }

    @Override
    public boolean empty() {
        return (masculineSecondPerson == null || masculineThirdPerson.empty()) &&
                (feminineThirdPerson == null || feminineThirdPerson.empty()) &&
                (masculineSecondPerson == null || masculineSecondPerson.empty()) &&
                (feminineSecondPerson == null || feminineSecondPerson.empty()) &&
                (firstPerson == null || firstPerson.empty());
    }
}
