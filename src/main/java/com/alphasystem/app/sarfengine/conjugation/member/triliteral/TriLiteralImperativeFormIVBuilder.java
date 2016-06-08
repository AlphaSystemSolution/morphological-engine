package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

;

/**
 * @author sali
 */
public class TriLiteralImperativeFormIVBuilder extends TriLiteralImperativeBuilder {

    @AssistedInject
    public TriLiteralImperativeFormIVBuilder(@Assisted RuleProcessor ruleProcessor,
                                             @Assisted boolean skipRuleProcessing,
                                             @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord, ALIF_HAMZA_ABOVE_WITH_FATHA);
    }
}
