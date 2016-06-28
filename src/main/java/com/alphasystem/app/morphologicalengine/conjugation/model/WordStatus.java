/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.*;

;

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
     * @param baseRootWord
     */
    public WordStatus(final RootWord baseRootWord) {
        evaluateStatus(baseRootWord);
    }

    private void evaluateStatus(final RootWord baseRootWord) {
        ArabicLetterType firstRadicalLetter = null;
        ArabicLetter letter = null;

        letter = baseRootWord.getFirstRadical();
        if (letter != null) {
            firstRadicalLetter = letter.getLetter();
            firstRadicalWaw = firstRadicalLetter.equals(WAW);
            firstRadicalYa = firstRadicalLetter.equals(YA);
            firstRadicalHamza = firstRadicalLetter.equals(HAMZA);
        }

        letter = baseRootWord.getSecondRadical();
        ArabicLetterType secondRadicalLetter = null;
        if (letter != null) {
            secondRadicalLetter = letter.getLetter();
            secondRadicalWaw = secondRadicalLetter.equals(WAW);
            secondRadicalYa = secondRadicalLetter.equals(YA);
            secondRadicalHamza = secondRadicalLetter.equals(HAMZA);
        }

        letter = baseRootWord.getThirdRadical();
        ArabicLetterType thirdRadicalLetter = null;
        if (letter != null) {
            thirdRadicalLetter = letter.getLetter();
            thirdRadicalWaw = thirdRadicalLetter.equals(WAW);
            thirdRadicalYa = thirdRadicalLetter.equals(YA);
            thirdRadicalHamza = thirdRadicalLetter.equals(HAMZA);
        }

        if (secondRadicalLetter != null) {
            doubledLettered = secondRadicalLetter.equals(thirdRadicalLetter);
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
