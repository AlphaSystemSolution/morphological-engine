package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;

/**
 * @author sali
 */
public final class WordStatus {

    private boolean firstRadicalWaw;

    private boolean firstRadicalYa;

    private boolean secondRadicalWaw;

    private boolean secondRadicalYa;

    private boolean thirdRadicalWaw;

    private boolean thirdRadicalYa;

    private boolean firstRadicalHamza;

    private boolean secondRadicalHamza;

    private boolean thirdRadicalHamza;

    private boolean doubledLettered;

    /**
     * @param rootLetters
     */
    public WordStatus(final RootLetters rootLetters) {
        evaluateStatus(rootLetters);
    }

    private void evaluateStatus(final RootLetters rootLetters) {
        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        firstRadicalWaw = WAW.equals(firstRadical);
        firstRadicalYa = YA.equals(firstRadical);
        firstRadicalHamza = HAMZA.equals(firstRadical);

        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        secondRadicalWaw = WAW.equals(secondRadical);
        secondRadicalYa = YA.equals(secondRadical);
        secondRadicalHamza = HAMZA.equals(secondRadical);

        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        thirdRadicalWaw = WAW.equals(thirdRadical);
        thirdRadicalYa = YA.equals(thirdRadical);
        thirdRadicalHamza = HAMZA.equals(thirdRadical);

        if (secondRadical != null) {
            doubledLettered = secondRadical.equals(thirdRadical);
        }
    }

    public boolean isAssimilated() {
        return firstRadicalWaw || firstRadicalYa;
    }

    public boolean isDefective() {
        return thirdRadicalWaw || thirdRadicalYa;
    }

    public boolean isDoublyWeak() {
        return twoSeparateLettersWeak() || twoConsecutiveLettersWeak();
    }

    public boolean isDoubledLettered() {
        return doubledLettered;
    }

    public boolean isFirstRadicalHamza() {
        return firstRadicalHamza;
    }

    public boolean isFirstRadicalWaw() {
        return firstRadicalWaw;
    }

    public boolean isFirstRadicalYa() {
        return firstRadicalYa;
    }

    public boolean isHamzatted() {
        return firstRadicalHamza || secondRadicalHamza || thirdRadicalHamza;
    }

    public boolean isHollow() {
        return secondRadicalWaw || secondRadicalYa;
    }

    public boolean isSecondRadicalHamza() {
        return secondRadicalHamza;
    }

    public boolean isSecondRadicalWaw() {
        return secondRadicalWaw;
    }

    public boolean isSecondRadicalYa() {
        return secondRadicalYa;
    }

    public boolean isThirdRadicalHamza() {
        return thirdRadicalHamza;
    }

    public boolean isThirdRadicalWaw() {
        return thirdRadicalWaw;
    }

    public boolean isThirdRadicalYa() {
        return thirdRadicalYa;
    }

    public boolean isWeak() {
        return isAssimilated() || isHollow() || isDefective();
    }

    public boolean isWeakWaw() {
        return isFirstRadicalWaw() || isSecondRadicalWaw()
                || isThirdRadicalWaw();
    }

    public boolean isWeakYa() {
        return isFirstRadicalYa() || isSecondRadicalYa() || isThirdRadicalYa();
    }

    public boolean twoConsecutiveLettersWeak() {
        return (isHollow() && isDefective()) || (isAssimilated() && isHollow());
    }

    public boolean twoSeparateLettersWeak() {
        return isAssimilated() && isDefective();
    }
}
