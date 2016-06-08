/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.concatenateWithSpace;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;

;

/**
 * @author sali
 */
public class PrefixProcessor extends AbstractRuleProcessor {

    private static final ArabicWord NEGATE = getWord(LAM, ALIF);

    @AssistedInject
    public PrefixProcessor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, new SarfTermType[]{FORBIDDING}, null);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }

        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        // make sure word doesn't have LA in front of it
        String n = NEGATE.toBuckWalter() + SPACE.toCode();
        if (!result.toBuckWalter().startsWith(n)) {
            result = concatenateWithSpace(NEGATE, result);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
