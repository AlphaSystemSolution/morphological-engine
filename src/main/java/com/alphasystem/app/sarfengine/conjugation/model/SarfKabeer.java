/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

/**
 * @author sali
 */
public class SarfKabeer {

    private final SarfKabeerPair activeTensePair;

    private final SarfKabeerPair[] verbalNounPairs;

    private final SarfKabeerPair activeParticiplePair;

    private final SarfKabeerPair passiveTensePair;

    private final SarfKabeerPair passiveParticiplePair;

    private final SarfKabeerPair imperativeAndForbiddingPair;

    private final SarfKabeerPair[] adverbPairs;

    public SarfKabeer(SarfKabeerPair activeTensePair,
                      SarfKabeerPair[] verbalNounPairs, SarfKabeerPair activeParticiplePair,
                      SarfKabeerPair passiveTensePair, SarfKabeerPair passiveParticiplePair,
                      SarfKabeerPair imperativeAndForbiddingPair, SarfKabeerPair[] adverbPairs) {
        this.activeTensePair = activeTensePair;
        this.verbalNounPairs = verbalNounPairs;
        this.activeParticiplePair = activeParticiplePair;
        this.passiveTensePair = passiveTensePair;
        this.passiveParticiplePair = passiveParticiplePair;
        this.imperativeAndForbiddingPair = imperativeAndForbiddingPair;
        this.adverbPairs = adverbPairs;
    }

    public SarfKabeerPair getActiveParticiplePair() {
        return activeParticiplePair;
    }

    public SarfKabeerPair getActiveTensePair() {
        return activeTensePair;
    }

    public SarfKabeerPair getImperativeAndForbiddingPair() {
        return imperativeAndForbiddingPair;
    }

    public SarfKabeerPair[] getVerbalNounPairs() {
        return verbalNounPairs;
    }

    public SarfKabeerPair getPassiveParticiplePair() {
        return passiveParticiplePair;
    }

    public SarfKabeerPair getPassiveTensePair() {
        return passiveTensePair;
    }

    public SarfKabeerPair[] getAdverbPairs() {
        return adverbPairs;
    }
}
