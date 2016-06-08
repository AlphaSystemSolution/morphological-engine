package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralImperativeAndForbiddingBuilder;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class TriLiteralForbiddingBuilder extends AbstractTriLiteralImperativeAndForbiddingBuilder {

    @AssistedInject
    public TriLiteralForbiddingBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord, null, true);
    }

}
