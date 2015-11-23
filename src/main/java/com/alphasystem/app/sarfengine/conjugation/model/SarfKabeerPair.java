/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

/**
 * @author sali
 */
public class SarfKabeerPair {

    private final ConjugationStack rightSideStack;
    private final ConjugationStack leftSideStack;

    public SarfKabeerPair(ConjugationStack leftSideStack, ConjugationStack rightSideStack) {
        this.leftSideStack = leftSideStack;
        this.rightSideStack = rightSideStack;
    }

    public ConjugationStack getLeftSideStack() {
        return leftSideStack;
    }

    public ConjugationStack getRightSideStack() {
        return rightSideStack;
    }
}
