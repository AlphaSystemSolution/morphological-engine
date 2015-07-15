/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.arabic.model.ArabicWord;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class PassiveLine {

    private ArabicWord pastPassiveTense;

    private ArabicWord presentPassiveTense;

    private ArabicWord[] masdars;

    private ArabicWord ismMafoolMasculine;

    public PassiveLine(ArabicWord pastPassiveTense,
                       ArabicWord presentPassiveTense, ArabicWord ismMafoolMasculine,
                       ArabicWord... masdars) {
        this.pastPassiveTense = pastPassiveTense;
        this.presentPassiveTense = presentPassiveTense;
        this.masdars = masdars;
        this.ismMafoolMasculine = ismMafoolMasculine;
    }

    public ArabicWord getIsmMafoolMasculine() {
        return ismMafoolMasculine;
    }

    public void setIsmMafoolMasculine(ArabicWord ismMafoolMasculine) {
        this.ismMafoolMasculine = ismMafoolMasculine;
    }

    public ArabicWord[] getMasdars() {
        return masdars;
    }

    public void setMasdars(ArabicWord[] masdars) {
        this.masdars = masdars;
    }

    public ArabicWord getPastPassiveTense() {
        return pastPassiveTense;
    }

    public void setPastPassiveTense(ArabicWord pastPassiveTense) {
        this.pastPassiveTense = pastPassiveTense;
    }

    public ArabicWord getPresentPassiveTense() {
        return presentPassiveTense;
    }

    public void setPresentPassiveTense(ArabicWord presentPassiveTense) {
        this.presentPassiveTense = presentPassiveTense;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), pastPassiveTense);
        hc = hash(hc, presentPassiveTense);
        hc = hash(hc, ismMafoolMasculine);
        if (!isEmpty(masdars)) {
            for (ArabicWord aw : masdars) {
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
