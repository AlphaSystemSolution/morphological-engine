package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_PASSIVE_TENSE;

;

/**
 * @author sali
 */
public class TriLiteralPastPassiveBuilder extends TriLiteralPastTenseBuilder {

    @AssistedInject
    public TriLiteralPastPassiveBuilder(@Assisted RuleProcessor ruleProcessor,
                                        @Assisted boolean skipRuleProcessing,
                                        @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord);
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_PASSIVE_TENSE;
    }
}
