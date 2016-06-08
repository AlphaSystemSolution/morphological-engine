/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

/**
 * @author sali
 */
public class DetailedConjugation {

    private final DetailedConjugationPair activeTensePair;

    private final DetailedConjugationPair[] verbalNounPairs;

    private final DetailedConjugationPair activeParticiplePair;

    private final DetailedConjugationPair passiveTensePair;

    private final DetailedConjugationPair passiveParticiplePair;

    private final DetailedConjugationPair imperativeAndForbiddingPair;

    private final DetailedConjugationPair[] adverbPairs;

    public DetailedConjugation(DetailedConjugationPair activeTensePair,
                               DetailedConjugationPair[] verbalNounPairs, DetailedConjugationPair activeParticiplePair,
                               DetailedConjugationPair passiveTensePair, DetailedConjugationPair passiveParticiplePair,
                               DetailedConjugationPair imperativeAndForbiddingPair, DetailedConjugationPair[] adverbPairs) {
        this.activeTensePair = activeTensePair;
        this.verbalNounPairs = verbalNounPairs;
        this.activeParticiplePair = activeParticiplePair;
        this.passiveTensePair = passiveTensePair;
        this.passiveParticiplePair = passiveParticiplePair;
        this.imperativeAndForbiddingPair = imperativeAndForbiddingPair;
        this.adverbPairs = adverbPairs;
    }

    public DetailedConjugationPair getActiveParticiplePair() {
        return activeParticiplePair;
    }

    public DetailedConjugationPair getActiveTensePair() {
        return activeTensePair;
    }

    public DetailedConjugationPair getImperativeAndForbiddingPair() {
        return imperativeAndForbiddingPair;
    }

    public DetailedConjugationPair[] getVerbalNounPairs() {
        return verbalNounPairs;
    }

    public DetailedConjugationPair getPassiveParticiplePair() {
        return passiveParticiplePair;
    }

    public DetailedConjugationPair getPassiveTensePair() {
        return passiveTensePair;
    }

    public DetailedConjugationPair[] getAdverbPairs() {
        return adverbPairs;
    }
}
