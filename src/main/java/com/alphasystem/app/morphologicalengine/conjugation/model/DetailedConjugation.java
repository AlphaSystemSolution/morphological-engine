/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

/**
 * @author sali
 */
public class DetailedConjugation {

    private final DetailedConjugationPair<VerbConjugationGroup> activeTensePair;

    private final DetailedConjugationPair<NounConjugationGroup>[] verbalNounPairs;

    private final DetailedConjugationPair<NounConjugationGroup> activeParticiplePair;

    private final DetailedConjugationPair<VerbConjugationGroup> passiveTensePair;

    private final DetailedConjugationPair<NounConjugationGroup> passiveParticiplePair;

    private final DetailedConjugationPair<VerbConjugationGroup> imperativeAndForbiddingPair;

    private final DetailedConjugationPair<NounConjugationGroup>[] adverbPairs;

    public DetailedConjugation(DetailedConjugationPair<VerbConjugationGroup> activeTensePair,
                               DetailedConjugationPair<NounConjugationGroup>[] verbalNounPairs,
                               DetailedConjugationPair<NounConjugationGroup> activeParticiplePair,
                               DetailedConjugationPair<VerbConjugationGroup> passiveTensePair,
                               DetailedConjugationPair<NounConjugationGroup> passiveParticiplePair,
                               DetailedConjugationPair<VerbConjugationGroup> imperativeAndForbiddingPair,
                               DetailedConjugationPair<NounConjugationGroup>[] adverbPairs) {
        this.activeTensePair = activeTensePair;
        this.verbalNounPairs = verbalNounPairs;
        this.activeParticiplePair = activeParticiplePair;
        this.passiveTensePair = passiveTensePair;
        this.passiveParticiplePair = passiveParticiplePair;
        this.imperativeAndForbiddingPair = imperativeAndForbiddingPair;
        this.adverbPairs = adverbPairs;
    }

    public DetailedConjugationPair<NounConjugationGroup> getActiveParticiplePair() {
        return activeParticiplePair;
    }

    public DetailedConjugationPair<VerbConjugationGroup> getActiveTensePair() {
        return activeTensePair;
    }

    public DetailedConjugationPair<VerbConjugationGroup> getImperativeAndForbiddingPair() {
        return imperativeAndForbiddingPair;
    }

    public DetailedConjugationPair<NounConjugationGroup>[] getVerbalNounPairs() {
        return verbalNounPairs;
    }

    public DetailedConjugationPair<NounConjugationGroup> getPassiveParticiplePair() {
        return passiveParticiplePair;
    }

    public DetailedConjugationPair<VerbConjugationGroup> getPassiveTensePair() {
        return passiveTensePair;
    }

    public DetailedConjugationPair<NounConjugationGroup>[] getAdverbPairs() {
        return adverbPairs;
    }
}
