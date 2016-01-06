package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class AddTatweelProcessor extends AbstractRuleProcessor {

    @AssistedInject
    public AddTatweelProcessor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());

        if (!result.toBuckWalter().contains(ArabicLetterType.TATWEEL.toCode())) {
            // if we already has tatweel then we don't need to add again
            result = ArabicWord.appendTatweel(result);
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }
}
