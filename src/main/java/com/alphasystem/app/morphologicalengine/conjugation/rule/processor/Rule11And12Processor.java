/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.getDiacritic;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isDamma;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isKasra;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isWaw;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isYa;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

;

/**
 * @author sali
 */
public class Rule11And12Processor extends AbstractRuleProcessor {

    public Rule11And12Processor(final RuleInfo ruleInfo) {
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
       final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (!wordStatus.isDefective()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        ArabicLetterType thirdRadicalLetter = thirdRadical.getLetter();
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        int previousIndex = thirdRadicalIndex - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousLetterDiacritic = getDiacritic(previousLetter);
        if (isWaw(thirdRadicalLetter) && isKasra(previousLetterDiacritic)) {
            result.replaceLetter(thirdRadicalIndex, YA);
        } else if (isYa(thirdRadicalLetter) && isDamma(previousLetterDiacritic)) {
            result.replaceLetter(thirdRadicalIndex, WAW);
        }

        thirdRadical = result.getLetter(thirdRadicalIndex);
        baseRootWord.setThirdRadical(thirdRadical);
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
