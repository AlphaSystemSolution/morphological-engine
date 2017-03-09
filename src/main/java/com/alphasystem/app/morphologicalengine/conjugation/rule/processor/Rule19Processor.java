/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.NOUN_BASED_TYPES;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isLongAlif;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isWaw;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.isYa;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;

;

/**
 * @author sali
 */
public class Rule19Processor extends AbstractRuleProcessor {

    public Rule19Processor(final RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, NOUN_BASED_TYPES);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (!wordStatus.isDefective()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        ArabicLetterType lastLetter = result.getLastLetter().getLetter();
        ArabicLetterType secondLastLetter = result.getLetter(
                result.getLength() - 2).getLetter();
        boolean wawOrYa = isWaw(lastLetter) || isYa(lastLetter);
        boolean alif = isLongAlif(secondLastLetter);
        if (wawOrYa && alif) {
            result.replaceLetter(result.getLength() - 1, HAMZA);
            baseRootWord.setThirdRadicalIndex(-1);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
