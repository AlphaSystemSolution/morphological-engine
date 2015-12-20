/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;


import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class ActiveLine {

    private final RootWord pastTense;

    private final RootWord presentTense;

    private final RootWord[] verbalNouns;

    private final RootWord activeParticipleMasculine;

    public ActiveLine(RootWord pastTense, RootWord presentTense, RootWord activeParticipleMasculine, RootWord... verbalNouns) {
        this.pastTense = pastTense;
        this.presentTense = presentTense;
        this.verbalNouns = verbalNouns;
        this.activeParticipleMasculine = activeParticipleMasculine;
    }

    public RootWord getActiveParticipleMasculine() {
        return activeParticipleMasculine;
    }

    public RootWord[] getVerbalNouns() {
        return verbalNouns;
    }

    public RootWord getPastTense() {
        return pastTense;
    }

    public RootWord getPresentTense() {
        return presentTense;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), pastTense);
        hc = hash(hc, presentTense);
        hc = hash(hc, activeParticipleMasculine);
        if (!isEmpty(verbalNouns)) {
            for (RootWord aw : verbalNouns) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }

}
