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
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.*;

;

/**
 * @author sali
 */
public class HamzaRule7Processor extends AbstractRuleProcessor {

    @AssistedInject
    public HamzaRule7Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        final WordStatus status = ruleInfo.getWordStatus();
        if (!status.isHamzatted()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());

        int indexOfHamza = status.isSecondRadicalHamza() ? baseRootWord
                .getSecondRadicalIndex() : baseRootWord.getThirdRadicalIndex();
        indexOfHamza = status.isFirstRadicalHamza() ? baseRootWord
                .getFirstRadicalIndex() : indexOfHamza;
        int previousIndex = indexOfHamza - 1;
        if (previousIndex < 0) {
            return baseRootWord;
        }

        ArabicLetter hamza = result.getLetter(indexOfHamza);
        DiacriticType diacriticOfHamza = getDiacritic(hamza);
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousDiacritic = getDiacritic(previousLetter);
        if (isMutaharik(diacriticOfHamza) && isSakin(previousDiacritic)) {
            int maddaIndex = maddaIndex(result);
            if (maddaIndex > -1 && maddaIndex < indexOfHamza) {
                System.out
                        .println("*************************** HERE ***************************");
                // TODO:
                // throw new RuntimeException(
                // format("Found madda {%s} at {%s} of the word {%s} of %s of %s, while hamza at %s}",
                // result.getLetter(maddaIndex).getLetter(),
                // maddaIndex, result,
                // baseRootWord.getMemberType(),
                // baseRootWord.getSarfTermType(), indexOfHamza));
            }
            result.replaceDiacritic(previousIndex, hamza.getDiacritics())
                    .replaceLetter(indexOfHamza, REMOVE_MARKER);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
