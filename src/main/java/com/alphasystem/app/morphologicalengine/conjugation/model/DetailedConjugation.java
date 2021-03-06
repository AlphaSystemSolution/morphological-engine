/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

/**
 * @author sali
 */
public class DetailedConjugation {

    private final VerbDetailedConjugationPair activeTensePair;

    private final NounDetailedConjugationPair[] verbalNounPairs;

    private final NounDetailedConjugationPair activeParticiplePair;

    private final VerbDetailedConjugationPair passiveTensePair;

    private final NounDetailedConjugationPair passiveParticiplePair;

    private final VerbDetailedConjugationPair imperativeAndForbiddingPair;

    private final NounDetailedConjugationPair[] adverbPairs;

    public DetailedConjugation(VerbDetailedConjugationPair activeTensePair,
                               NounDetailedConjugationPair[] verbalNounPairs,
                               NounDetailedConjugationPair activeParticiplePair,
                               VerbDetailedConjugationPair passiveTensePair,
                               NounDetailedConjugationPair passiveParticiplePair,
                               VerbDetailedConjugationPair imperativeAndForbiddingPair,
                               NounDetailedConjugationPair[] adverbPairs) {
        this.activeTensePair = activeTensePair;
        this.verbalNounPairs = verbalNounPairs;
        this.activeParticiplePair = activeParticiplePair;
        this.passiveTensePair = passiveTensePair;
        this.passiveParticiplePair = passiveParticiplePair;
        this.imperativeAndForbiddingPair = imperativeAndForbiddingPair;
        this.adverbPairs = adverbPairs;
    }

    public VerbDetailedConjugationPair getActiveTensePair() {
        return activeTensePair;
    }

    public NounDetailedConjugationPair[] getVerbalNounPairs() {
        return verbalNounPairs;
    }

    public NounDetailedConjugationPair getActiveParticiplePair() {
        return activeParticiplePair;
    }

    public VerbDetailedConjugationPair getPassiveTensePair() {
        return passiveTensePair;
    }

    public NounDetailedConjugationPair getPassiveParticiplePair() {
        return passiveParticiplePair;
    }

    public VerbDetailedConjugationPair getImperativeAndForbiddingPair() {
        return imperativeAndForbiddingPair;
    }

    public NounDetailedConjugationPair[] getAdverbPairs() {
        return adverbPairs;
    }
}
