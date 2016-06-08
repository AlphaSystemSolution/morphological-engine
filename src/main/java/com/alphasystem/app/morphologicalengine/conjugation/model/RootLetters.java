package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetterType;

/**
 * @author sali
 */
public final class RootLetters {

    protected final ArabicLetterType firstRadical;
    protected final ArabicLetterType secondRadical;
    protected final ArabicLetterType thirdRadical;
    protected final ArabicLetterType fourthRadical;

    public RootLetters(ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        this(firstRadical, secondRadical, thirdRadical, null);
    }

    public RootLetters(ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        this.firstRadical = firstRadical;
        this.secondRadical = secondRadical;
        this.thirdRadical = thirdRadical;
        this.fourthRadical = fourthRadical;
    }

    public ArabicLetterType getFirstRadical() {
        return firstRadical;
    }

    public ArabicLetterType getSecondRadical() {
        return secondRadical;
    }

    public ArabicLetterType getThirdRadical() {
        return thirdRadical;
    }

    public ArabicLetterType getFourthRadical() {
        return fourthRadical;
    }

    public boolean hasFourthRadical() {
        return fourthRadical != null;
    }

}
