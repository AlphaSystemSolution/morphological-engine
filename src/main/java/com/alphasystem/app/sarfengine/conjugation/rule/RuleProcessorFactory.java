package com.alphasystem.app.sarfengine.conjugation.rule;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import static com.alphasystem.app.sarfengine.guice.RuleProcessorModule.*;

/**
 * @author sali
 */
public interface RuleProcessorFactory {

    @Named(RULE_ENGINE)
    RuleProcessor getRuleEngine(@Assisted RuleInfo ruleInfo);

    @Named(DOUBLE_LETTERED_PROCESSOR)
    RuleProcessor getDoubleLetteredProcessor(@Assisted RuleInfo ruleInfo);

    @Named(FORM_VIII_PROCESSOR)
    RuleProcessor getFormVIIIProcessor(@Assisted RuleInfo ruleInfo);

    @Named(HAMZAH_CHAIR_PROCESSOR)
    RuleProcessor getHamzahChairProcessor(@Assisted RuleInfo ruleInfo);

    @Named(HAMZA_RULE_7_PROCESSOR)
    RuleProcessor getHamzaRule7Processor(@Assisted RuleInfo ruleInfo);

    @Named(HAMZATED_FIRST_RADICAL_PROCESSOR)
    RuleProcessor getHamzatedFirstRadicalProcessor(@Assisted RuleInfo ruleInfo);

    @Named(HAMZATED_THIRD_RADICAL_PROCESSOR)
    RuleProcessor getHamzatedThirdRadicalProcessor(@Assisted RuleInfo ruleInfo);

    @Named(PREFIX_PROCESSOR)
    RuleProcessor getPrefixProcessor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_1_PROCESSOR)
    RuleProcessor getRule1Processor(@Assisted RuleInfo ruleInfo);

    // Rule 7 is setting "diacriticForWeakSecondRadicalWaw" and "pastTenseHasTransformed"
    @Named(RULE_7_PROCESSOR)
    RuleProcessor getRule7Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_8_PROCESSOR)
    RuleProcessor getRule8Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_9_PROCESSOR)
    RuleProcessor getRule9Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_10_PROCESSOR)
    RuleProcessor getRule10Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_11_AND_12_PROCESSOR)
    RuleProcessor getRule11And12Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_13_PROCESSOR)
    RuleProcessor getRule13Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_14_PROCESSOR)
    RuleProcessor getRule14Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_16_PROCESSOR)
    RuleProcessor getRule16Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_17_PROCESSOR)
    RuleProcessor getRule17Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_19_PROCESSOR)
    RuleProcessor getRule19Processor(@Assisted RuleInfo ruleInfo);

    @Named(RULE_20_PROCESSOR)
    RuleProcessor getRule20Processor(@Assisted RuleInfo ruleInfo);
}
