package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;

/**
 * @author sali
 */
public class RuleInfo {

    private final NamedTemplate template;
    private final WordStatus wordStatus;
    private final boolean skipRuleProcessing;
    private DiacriticType diacriticForWeakSecondRadicalWaw;
    private boolean pastTenseHasTransformed;
    private ArabicLetterType hamzahReplacement;

    public RuleInfo(NamedTemplate template, RootLetters rootLetters) {
        this(template, rootLetters, false);
    }

    public RuleInfo(NamedTemplate template, RootLetters rootLetters, boolean skipRuleProcessing) {
        this.template = template;
        wordStatus = new WordStatus(rootLetters);
        this.skipRuleProcessing = skipRuleProcessing;
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public boolean isSkipRuleProcessing() {
        return skipRuleProcessing;
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

    public WordStatus getWordStatus() {
        return wordStatus;
    }
}
