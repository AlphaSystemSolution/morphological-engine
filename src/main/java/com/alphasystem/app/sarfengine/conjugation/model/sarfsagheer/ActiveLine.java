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
public class ActiveLine {

    private final ConjugationMember pastTense;

    private final ConjugationMember presentTense;

    private final ConjugationMember[] masdars;

    private final ConjugationMember ismFaailMasculine;

    public ActiveLine(ConjugationMember pastTense, ConjugationMember presentTense,
                      ConjugationMember ismFaailMasculine, ConjugationMember... masdars) {
        this.pastTense = pastTense;
        this.presentTense = presentTense;
        this.masdars = masdars;
        this.ismFaailMasculine = ismFaailMasculine;
    }

    public ConjugationMember getIsmFaailMasculine() {
        return ismFaailMasculine;
    }

    public ConjugationMember[] getMasdars() {
        return masdars;
    }

    public ConjugationMember getPastTense() {
        return pastTense;
    }

    public ConjugationMember getPresentTense() {
        return presentTense;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), pastTense);
        hc = hash(hc, presentTense);
        hc = hash(hc, ismFaailMasculine);
        if (!isEmpty(masdars)) {
            for (ConjugationMember aw : masdars) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }

}
