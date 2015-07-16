/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class PassiveLine {

    private ConjugationMember pastPassiveTense;

    private ConjugationMember presentPassiveTense;

    private ConjugationMember[] masdars;

    private ConjugationMember ismMafoolMasculine;

    public PassiveLine(ConjugationMember pastPassiveTense,
                       ConjugationMember presentPassiveTense, ConjugationMember ismMafoolMasculine,
                       ConjugationMember... masdars) {
        this.pastPassiveTense = pastPassiveTense;
        this.presentPassiveTense = presentPassiveTense;
        this.masdars = masdars;
        this.ismMafoolMasculine = ismMafoolMasculine;
    }

    public ConjugationMember getIsmMafoolMasculine() {
        return ismMafoolMasculine;
    }

    public void setIsmMafoolMasculine(ConjugationMember ismMafoolMasculine) {
        this.ismMafoolMasculine = ismMafoolMasculine;
    }

    public ConjugationMember[] getMasdars() {
        return masdars;
    }

    public void setMasdars(ConjugationMember[] masdars) {
        this.masdars = masdars;
    }

    public ConjugationMember getPastPassiveTense() {
        return pastPassiveTense;
    }

    public void setPastPassiveTense(ConjugationMember pastPassiveTense) {
        this.pastPassiveTense = pastPassiveTense;
    }

    public ConjugationMember getPresentPassiveTense() {
        return presentPassiveTense;
    }

    public void setPresentPassiveTense(ConjugationMember presentPassiveTense) {
        this.presentPassiveTense = presentPassiveTense;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), pastPassiveTense);
        hc = hash(hc, presentPassiveTense);
        hc = hash(hc, ismMafoolMasculine);
        if (!isEmpty(masdars)) {
            for (ConjugationMember aw : masdars) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }

    public boolean isNull() {
        return pastPassiveTense == null && presentPassiveTense == null
                && ismMafoolMasculine == null;
    }

}
