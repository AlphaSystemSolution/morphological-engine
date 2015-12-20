/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_ALIF;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_TATWEEL;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;

;

/**
 * @author sali
 */
public class Rule8Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule8Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{IMPERATIVE,
                    FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        boolean imperfect = PRESENT_TENSE.equals(sarfTermType)
                || PRESENT_PASSIVE_TENSE.equals(sarfTermType);
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        ArabicLetter firstLetter = result.getLetter(0);
        ArabicLetterType firstLetterType = firstLetter.getLetter();
        boolean firstLetterWeak = isWaw(firstLetterType)
                || (!imperfect && isYa(firstLetterType));
        WordStatus wordStatus = new WordStatus(baseRootWord);
        int maddaIndex = maddaIndex(result);
        boolean maddaExtra = isMaddaExtra(result, sarfTermType, maddaIndex);
        boolean passiveParticiple = PASSIVE_PARTICIPLE_MASCULINE
                .equals(sarfTermType)
                || PASSIVE_PARTICIPLE_FEMININE.equals(sarfTermType);
        if (!wordStatus.isWeak() || wordStatus.isAssimilated()
                || wordStatus.isDoublyWeak() || firstLetterWeak
                || (maddaExtra && !passiveParticiple)) {
            return baseRootWord;
        }

        int indexOfWeakLetter = -1;
        if (wordStatus.isAssimilated()) {
            indexOfWeakLetter = baseRootWord.getFirstRadicalIndex();
        } else if (wordStatus.isHollow()) {
            indexOfWeakLetter = baseRootWord.getSecondRadicalIndex();
        } else {
            indexOfWeakLetter = baseRootWord.getThirdRadicalIndex();
        }
        if (indexOfWeakLetter <= -1) {
            return baseRootWord;
        }
        ArabicLetter weakLetter = result.getLetter(indexOfWeakLetter);
        DiacriticType weakLetterDiacritic = getDiacritic(weakLetter);
        boolean activeWeakLetter = isMutaharikNoTanween(weakLetterDiacritic);
        if (!activeWeakLetter) {
            return baseRootWord;
        }

        int previousIndex = indexOfWeakLetter - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousLetterDiacritic = getDiacritic(previousLetter);
        boolean inactivePreviousLetter = isSakin(previousLetterDiacritic);
        ArabicLetterType letter = previousLetter.getLetter();
        if (!inactivePreviousLetter || isWaw(letter) || isYa(letter)) {
            return baseRootWord;
        }

        int nextIndex = (indexOfWeakLetter >= (result.getLength() - 1)) ? -1
                : indexOfWeakLetter + 1;

        result.replaceDiacritic(previousIndex, weakLetterDiacritic)
                .replaceDiacritic(indexOfWeakLetter, SUKUN);
        ArabicLetter replacementLetter = result.getLetter(indexOfWeakLetter);
        if (isFatha(weakLetterDiacritic)) {
            replacementLetter = LETTER_ALIF;
        }
        result.replaceLetter(indexOfWeakLetter, replacementLetter);
        if (nextIndex > -1) {
            ArabicLetter nextLetter = result.getLetter(nextIndex);
            DiacriticType nextLetterDiacritic = getDiacritic(nextLetter);
            if (isSakin(nextLetterDiacritic)) {
                replacementLetter = LETTER_TATWEEL;
                result.replaceLetter(indexOfWeakLetter, replacementLetter);
            }
        }
        if (wordStatus.isAssimilated()) {
            baseRootWord.setFirstRadical(replacementLetter);
            baseRootWord.setFirstRadicalIndex(-1);
        } else if (wordStatus.isHollow()) {
            baseRootWord.setSecondRadical(replacementLetter);
            baseRootWord.setSecondRadicalIndex(-1);
        } else {
            baseRootWord.setThirdRadical(replacementLetter);
            baseRootWord.setThirdRadicalIndex(-1);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }
}
