/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
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
        int hc = hash(super.hashCode(), pastPassiveTense);
        hc = hash(hc, presentPassiveTense);
        hc = hash(hc, passiveParticipleMasculine);
        if (!isEmpty(verbalNouns)) {
            for (RootWord aw : verbalNouns) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }

    public boolean isNull() {
        return pastPassiveTense == null && presentPassiveTense == null
                && passiveParticipleMasculine == null;
    }

}
