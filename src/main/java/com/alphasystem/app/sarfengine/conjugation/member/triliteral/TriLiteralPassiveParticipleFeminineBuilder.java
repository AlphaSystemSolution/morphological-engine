package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;

;

/**
 * @author sali
 */
public class TriLiteralPassiveParticipleFeminineBuilder extends TriLiteralActiveParticipleFeminineBuilder {

    @AssistedInject
    public TriLiteralPassiveParticipleFeminineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord);
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_FEMININE;
    }
}
