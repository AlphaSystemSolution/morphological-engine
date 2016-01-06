package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.sarfengine.util.PatternHelper.removeTatweel;

/**
 * @author sali
 */
public class RemoveTatweelProcessor extends AbstractRuleProcessor {

    @AssistedInject
    public RemoveTatweelProcessor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        String s = removeTatweel(baseRootWord.getRootWord().toBuckWalter());
        baseRootWord.setRootWord(ArabicWord.fromBuckWalterString(s));
        return baseRootWord;
    }
}
