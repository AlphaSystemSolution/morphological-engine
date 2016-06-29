/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

;

/**
 * @author sali
 */
public class Rule17Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule17Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, ACTIVE_PARTICIPLE_MASCULINE,
                    ACTIVE_PARTICIPLE_FEMININE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (!wordStatus.isHollow() || !(ruleInfo.isPastTenseHasTransformed())) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        result.replaceLetter(secondRadicalIndex, HAMZA);
        baseRootWord.setSecondRadical(result.getLetter(secondRadicalIndex));
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
