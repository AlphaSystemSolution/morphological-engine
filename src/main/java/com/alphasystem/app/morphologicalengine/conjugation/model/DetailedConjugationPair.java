/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

/**
 * @author sali
 */
public abstract class DetailedConjugationPair<G extends ConjugationGroup> {

    private final G leftSideConjugations;
    private final G rightSideConjugations;

    public DetailedConjugationPair(G leftSideConjugations, G rightSideConjugations) {
        this.leftSideConjugations = leftSideConjugations;
        this.rightSideConjugations = rightSideConjugations;
    }

    public G getLeftSideConjugations() {
        return leftSideConjugations;
    }

    public G getRightSideConjugations() {
        return rightSideConjugations;
    }

}
