/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

;

/**
 * @author sali
 */
public class DoubleLetteredProcessor extends AbstractRuleProcessor {

    @AssistedInject
    public DoubleLetteredProcessor(@Assisted RuleInfo ruleInfo) {
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
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isDoubledLettered()) {
            return baseRootWord;
        }

        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        int firstLetterIndex = secondRadicalIndex;
        ArabicLetter firstLetter = result.getLetter(firstLetterIndex);
        ArabicLetter secondLetter = result.getLetter(baseRootWord
                .getThirdRadicalIndex());
        int indexOfLetterBeforeFirstLetter = firstLetterIndex - 1;
        ArabicLetter letterBeforeFirstLetter = result
                .getLetter(indexOfLetterBeforeFirstLetter);
        DiacriticType firstLetterDiacritic = getDiacritic(firstLetter);
        DiacriticType secondLetterDiacritic = getDiacritic(secondLetter);
        DiacriticType letterBeforeFirstLetterDiacritic = getDiacritic(letterBeforeFirstLetter);
        boolean activeFirstLetter = isMutaharik(firstLetterDiacritic);
        boolean activeSecondLetter = isMutaharik(secondLetterDiacritic);
        boolean activeLetterBeforeFirstLetter = isMutaharik(letterBeforeFirstLetterDiacritic);
        int maddaIndex = maddaIndex(result);
        boolean madda = maddaIndex == indexOfLetterBeforeFirstLetter;
        if (activeFirstLetter && activeSecondLetter) {
            if (activeLetterBeforeFirstLetter) {
                result.replaceDiacritic(firstLetterIndex, SUKUN);
            } else if (!activeLetterBeforeFirstLetter && !madda) {
                result.replaceDiacritic(firstLetterIndex, SUKUN)
                        .replaceDiacritic(indexOfLetterBeforeFirstLetter,
                                firstLetterDiacritic);
            } else if (madda) {
                result.replaceDiacritic(firstLetterIndex, SUKUN);
            }
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
