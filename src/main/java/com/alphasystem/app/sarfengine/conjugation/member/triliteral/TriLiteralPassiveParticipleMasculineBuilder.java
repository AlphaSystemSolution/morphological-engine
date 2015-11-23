package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class TriLiteralPassiveParticipleMasculineBuilder extends TriLiteralActiveParticipleMasculineBuilder {

    @AssistedInject
    public TriLiteralPassiveParticipleMasculineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                       @Assisted NamedTemplate template,
                                                       @Assisted boolean skipRuleProcessing,
                                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical) {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical);
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_MASCULINE;
    }
}
