/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.util.Objects.hash;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassiveLine {

    private RootWord pastPassiveTense;

    private RootWord presentPassiveTense;

    private RootWord[] verbalNouns;

    private RootWord passiveParticipleMasculine;

    public PassiveLine(RootWord pastPassiveTense, RootWord presentPassiveTense, RootWord passiveParticipleMasculine,
                       RootWord... verbalNouns) {
        this.pastPassiveTense = pastPassiveTense;
        this.presentPassiveTense = presentPassiveTense;
        this.verbalNouns = verbalNouns;
        this.passiveParticipleMasculine = passiveParticipleMasculine;
    }

    public RootWord getPassiveParticipleMasculine() {
        return passiveParticipleMasculine;
    }

    public void setPassiveParticipleMasculine(RootWord passiveParticipleMasculine) {
        this.passiveParticipleMasculine = passiveParticipleMasculine;
    }

    public RootWord[] getVerbalNouns() {
        return verbalNouns;
    }

    public void setVerbalNouns(RootWord[] verbalNouns) {
        this.verbalNouns = verbalNouns;
    }

    public RootWord getPastPassiveTense() {
        return pastPassiveTense;
    }

    public void setPastPassiveTense(RootWord pastPassiveTense) {
        this.pastPassiveTense = pastPassiveTense;
    }

    public RootWord getPresentPassiveTense() {
        return presentPassiveTense;
    }

    public void setPresentPassiveTense(RootWord presentPassiveTense) {
        this.presentPassiveTense = presentPassiveTense;
    }

    @Override
    public int hashCode() {
        return hash(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine, verbalNouns);
    }

    public boolean isNull() {
        return pastPassiveTense == null && presentPassiveTense == null
                && passiveParticipleMasculine == null;
    }

}
