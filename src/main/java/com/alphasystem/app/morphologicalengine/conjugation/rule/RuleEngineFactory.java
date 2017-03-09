package com.alphasystem.app.morphologicalengine.conjugation.rule;

import org.springframework.beans.factory.annotation.Autowired;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.*;

/**
 * @author sali
 */
public class RuleEngineFactory implements RuleProcessorFactory {

    @Autowired
    @RuleProcessorType(DOUBLE_LETTERED_PROCESSOR)
    private RuleProcessorFactory doubleLetteredProcessorFactory;

    @Autowired
    @RuleProcessorType(FORM_VIII_PROCESSOR)
    private RuleProcessorFactory formVIIIProcessorFactory;

    @Autowired
    @RuleProcessorType(HAMZAH_CHAIR_PROCESSOR)
    private RuleProcessorFactory hamzahChairProcessorFactory;

    @Autowired
    @RuleProcessorType(HAMZA_RULE_7_PROCESSOR)
    private RuleProcessorFactory hamzaRule7ProcessorFactory;

    @Autowired
    @RuleProcessorType(HAMZATED_FIRST_RADICAL_PROCESSOR)
    private RuleProcessorFactory hamzatedFirstRadicalProcessorFactory;

    @Autowired
    @RuleProcessorType(HAMZATED_THIRD_RADICAL_PROCESSOR)
    private RuleProcessorFactory hamzatedThirdRadicalProcessorFactory;

    @Autowired
    @RuleProcessorType(IMPERATIVE_PROCESSOR)
    private RuleProcessorFactory imperativeProcessorFactory;

    @Autowired
    @RuleProcessorType(PATTERN_PROCESSOR)
    private RuleProcessorFactory patternProcessorFactory;

    @Autowired
    @RuleProcessorType(PREFIX_PROCESSOR)
    private RuleProcessorFactory prefixProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_1_PROCESSOR)
    private RuleProcessorFactory rule1ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_7_PROCESSOR)
    private RuleProcessorFactory rule7ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_8_PROCESSOR)
    private RuleProcessorFactory rule8ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_9_PROCESSOR)
    private RuleProcessorFactory rule9ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_10_PROCESSOR)
    private RuleProcessorFactory rule10ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_11_AND_12_PROCESSOR)
    private RuleProcessorFactory rule11And12ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_13_PROCESSOR)
    private RuleProcessorFactory rule13ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_14_PROCESSOR)
    private RuleProcessorFactory rule14ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_16_PROCESSOR)
    private RuleProcessorFactory rule15ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_17_PROCESSOR)
    private RuleProcessorFactory rule17ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_19_PROCESSOR)
    private RuleProcessorFactory rule19ProcessorFactory;

    @Autowired
    @RuleProcessorType(RULE_20_PROCESSOR)
    private RuleProcessorFactory rule20ProcessorFactory;

    @Override
    public RuleProcessor createRuleProcessor(RuleInfo ruleInfo) {
        return new RuleEngine(ruleInfo, doubleLetteredProcessorFactory, formVIIIProcessorFactory, hamzahChairProcessorFactory,
                hamzaRule7ProcessorFactory, hamzatedFirstRadicalProcessorFactory, hamzatedThirdRadicalProcessorFactory,
                imperativeProcessorFactory, patternProcessorFactory, prefixProcessorFactory, rule1ProcessorFactory,
                rule7ProcessorFactory, rule8ProcessorFactory, rule9ProcessorFactory, rule10ProcessorFactory,
                rule11And12ProcessorFactory, rule13ProcessorFactory, rule14ProcessorFactory, rule15ProcessorFactory,
                rule17ProcessorFactory, rule19ProcessorFactory, rule20ProcessorFactory);
    }
}
