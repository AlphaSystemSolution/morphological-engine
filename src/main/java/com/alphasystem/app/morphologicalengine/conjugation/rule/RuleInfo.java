package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;

/**
 * @author sali
 */
public class RuleInfo {

    private final NamedTemplate template;
    private final RootLetters rootLetters;
    private DiacriticType diacriticForWeakSecondRadicalWaw;
    private boolean pastTenseHasTransformed;
    private ArabicLetterType hamzahReplacement;

    public RuleInfo(NamedTemplate template, RootLetters rootLetters) {
        this.template = template;
        this.rootLetters = rootLetters;
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public RootLetters getRootLetters() {
        return rootLetters;
    }

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
