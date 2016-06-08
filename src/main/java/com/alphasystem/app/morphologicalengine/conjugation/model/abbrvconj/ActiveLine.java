/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;


import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static java.util.Objects.hash;

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
        return hash(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

}
