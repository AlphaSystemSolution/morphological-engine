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

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.NOUN_BASED_TYPES;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.VERB_BASED_TYPES;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.getDiacritic;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isDamma;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static org.apache.commons.lang3.ArrayUtils.contains;

;

/**
 * @author sali
 */
public class Rule16Processor extends AbstractRuleProcessor {

    public Rule16Processor(final RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, VERB_BASED_TYPES);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (!wordStatus.isDefective()
                && !contains(NOUN_BASED_TYPES, sarfTermType)) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        int previousIndex = thirdRadicalIndex - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousDiacritic = getDiacritic(previousLetter);
        boolean previousDiacriticDamma = isDamma(previousDiacritic);
        if ((wordStatus.isThirdRadicalWaw() && previousDiacriticDamma)
                || (wordStatus.isThirdRadicalYa() && previousDiacriticDamma)) {
            result.replaceLetter(thirdRadicalIndex, REMOVE_MARKER)
                    .replaceDiacritic(previousIndex, KASRATAN);
            baseRootWord.setThirdRadical(REMOVE_MARKER);
            if (previousIndex == baseRootWord.getSecondRadicalIndex()) {
                baseRootWord.setSecondRadical(result.getLetter(previousIndex));
            }
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
