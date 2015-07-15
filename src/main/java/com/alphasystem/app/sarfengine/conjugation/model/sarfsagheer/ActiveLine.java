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
public class ActiveLine {

    private final ArabicWord pastTense;

    private final ArabicWord presentTense;

    private final ArabicWord[] masdars;

    private final ArabicWord ismFaailMasculine;

    public ActiveLine(ArabicWord pastTense, ArabicWord presentTense,
                      ArabicWord ismFaailMasculine, ArabicWord... masdars) {
        this.pastTense = pastTense;
        this.presentTense = presentTense;
        this.masdars = masdars;
        this.ismFaailMasculine = ismFaailMasculine;
    }

    public ArabicWord getIsmFaailMasculine() {
        return ismFaailMasculine;
    }

    public ArabicWord[] getMasdars() {
        return masdars;
    }

    public ArabicWord getPastTense() {
        return pastTense;
    }

    public ArabicWord getPresentTense() {
        return presentTense;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), pastTense);
        hc = hash(hc, presentTense);
        hc = hash(hc, ismFaailMasculine);
        if (!isEmpty(masdars)) {
            for (ArabicWord aw : masdars) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }

}
