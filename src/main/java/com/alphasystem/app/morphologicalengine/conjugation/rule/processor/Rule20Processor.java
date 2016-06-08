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
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;

;

/**
 * @author sali
 */
public class Rule20Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule20Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{VERBAL_NOUN,
                    NOUN_OF_PLACE_AND_TIME, IMPERATIVE, FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isFirstRadicalWaw() && !wordStatus.isSecondRadicalWaw()
                && !wordStatus.isThirdRadicalWaw()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int indexOfWaw = -1;
        for (int i = 0; i < result.getLength(); i++) {
            if (isWaw(result.getLetter(i).getLetter())) {
                indexOfWaw = i;
                break;
            }
        }
        if (indexOfWaw < 3) {
            return baseRootWord;
        }
        int previousIndex = indexOfWaw - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousDiacritic = getDiacritic(previousLetter);
        if (isDamma(previousDiacritic) || isSakin(previousDiacritic)) {
            return baseRootWord;
        }
        result.replaceLetter(indexOfWaw, YA);
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
