package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_PASSIVE_TENSE;

/**
 * @author sali
 */
public class TriLiteralPresentPassiveBuilder extends TriLiteralPresentTenseBuilder {

    @AssistedInject
    public TriLiteralPresentPassiveBuilder(@Assisted RuleProcessor ruleProcessor,
                                           @Assisted boolean skipRuleProcessing,
                                           @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord);
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_PASSIVE_TENSE;
    }
}
