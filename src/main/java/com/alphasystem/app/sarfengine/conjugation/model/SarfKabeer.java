/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

/**
 * @author sali
 */
public class SarfKabeer {

    private final SarfKabeerPair activeTensePair;

    private final SarfKabeerPair[] masdarPairs;

    private final SarfKabeerPair activeIsmPair;

    private final SarfKabeerPair passiveTensePair;

    private final SarfKabeerPair passiveIsmPair;

    private final SarfKabeerPair amrAndNahiPair;

    private final SarfKabeerPair[] zarfPairs;

    public SarfKabeer(SarfKabeerPair activeTensePair,
                      SarfKabeerPair[] masdarPairs, SarfKabeerPair activeIsmPair,
                      SarfKabeerPair passiveTensePair, SarfKabeerPair passiveIsmPair,
                      SarfKabeerPair amrAndNahiPair, SarfKabeerPair[] zarfPairs) {
        this.activeTensePair = activeTensePair;
        this.masdarPairs = masdarPairs;
        this.activeIsmPair = activeIsmPair;
        this.passiveTensePair = passiveTensePair;
        this.passiveIsmPair = passiveIsmPair;
        this.amrAndNahiPair = amrAndNahiPair;
        this.zarfPairs = zarfPairs;
    }

    public SarfKabeerPair getActiveIsmPair() {
        return activeIsmPair;
    }

    public SarfKabeerPair getActiveTensePair() {
        return activeTensePair;
    }

    public SarfKabeerPair getAmrAndNahiPair() {
        return amrAndNahiPair;
    }

    public SarfKabeerPair[] getMasdarPairs() {
        return masdarPairs;
    }

    public SarfKabeerPair getPassiveIsmPair() {
        return passiveIsmPair;
    }

    public SarfKabeerPair getPassiveTensePair() {
        return passiveTensePair;
    }

    public SarfKabeerPair[] getZarfPairs() {
        return zarfPairs;
    }
}
