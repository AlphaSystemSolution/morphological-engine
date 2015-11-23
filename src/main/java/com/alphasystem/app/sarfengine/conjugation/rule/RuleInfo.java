package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;

/**
 * @author sali
 */
public class RuleInfo {

    private DiacriticType diacriticForWeakSecondRadicalWaw;
    private boolean pastTenseHasTransformed;
    private ArabicLetterType hamzahReplacement;

    public DiacriticType getDiacriticForWeakSecondRadicalWaw() {
        return diacriticForWeakSecondRadicalWaw;
    }

    public void setDiacriticForWeakSecondRadicalWaw(DiacriticType diacriticForWeakSecondRadicalWaw) {
        this.diacriticForWeakSecondRadicalWaw = diacriticForWeakSecondRadicalWaw;
    }

    public ArabicLetterType getHamzahReplacement() {
        return hamzahReplacement;
    }

    public void setHamzahReplacement(ArabicLetterType hamzahReplacement) {
        this.hamzahReplacement = hamzahReplacement;
    }

    public boolean isPastTenseHasTransformed() {
        return pastTenseHasTransformed;
    }

    public void setPastTenseHasTransformed(boolean pastTenseHasTransformed) {
        this.pastTenseHasTransformed = pastTenseHasTransformed;
    }
}
