package com.alphasystem.app.sarfengine.guice;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleEngine;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.conjugation.rule.processor.*;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class RuleProcessorModule extends AbstractModule {

    public static final String DOUBLE_LETTERED_PROCESSOR = "DoubleLetteredProcessor";
    public static final String FORM_VIII_PROCESSOR = "FormVIIIProcessor";
    public static final String HAMZAH_CHAIR_PROCESSOR = "HamzahChairProcessor";
    public static final String HAMZA_RULE_7_PROCESSOR = "HamzaRule7Processor";
    public static final String HAMZATED_FIRST_RADICAL_PROCESSOR = "HamzatedFirstRadicalProcessor";
    public static final String HAMZATED_THIRD_RADICAL_PROCESSOR = "HamzatedThirdRadicalProcessor";
    public static final String PREFIX_PROCESSOR = "PrefixProcessor";
    public static final String RULE_1_PROCESSOR = "Rule1Processor";
    public static final String RULE_7_PROCESSOR = "Rule7Processor";
    public static final String RULE_8_PROCESSOR = "Rule8Processor";
    public static final String RULE_9_PROCESSOR = "Rule9Processor";
    public static final String RULE_10_PROCESSOR = "Rule10Processor";
    public static final String RULE_11_AND_12_PROCESSOR = "Rule11And12Processor";
    public static final String RULE_13_PROCESSOR = "Rule13Processor";
    public static final String RULE_14_PROCESSOR = "Rule14Processor";
    public static final String RULE_16_PROCESSOR = "Rule16Processor";
    public static final String RULE_17_PROCESSOR = "Rule17Processor";
    public static final String RULE_19_PROCESSOR = "Rule19Processor";
    public static final String RULE_20_PROCESSOR = "Rule20Processor";
    public static final String RULE_ENGINE = "RuleEngine";

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(RuleProcessor.class, named(RULE_ENGINE), RuleEngine.class)
                .implement(RuleProcessor.class, named(DOUBLE_LETTERED_PROCESSOR), DoubleLetteredProcessor.class)
                .implement(RuleProcessor.class, named(FORM_VIII_PROCESSOR), FormVIIIProcessor.class)
                .implement(RuleProcessor.class, named(HAMZAH_CHAIR_PROCESSOR), HamzahChairProcessor.class)
                .implement(RuleProcessor.class, named(HAMZA_RULE_7_PROCESSOR), HamzaRule7Processor.class)
                .implement(RuleProcessor.class, named(HAMZATED_FIRST_RADICAL_PROCESSOR),
                        HamzatedFirstRadicalProcessor.class)
                .implement(RuleProcessor.class, named(HAMZATED_THIRD_RADICAL_PROCESSOR),
                        HamzatedThirdRadicalProcessor.class)
                .implement(RuleProcessor.class, named(PREFIX_PROCESSOR), PrefixProcessor.class)
                .implement(RuleProcessor.class, named(RULE_1_PROCESSOR), Rule1Processor.class)
                .implement(RuleProcessor.class, named(RULE_7_PROCESSOR), Rule7Processor.class)
                .implement(RuleProcessor.class, named(RULE_8_PROCESSOR), Rule8Processor.class)
                .implement(RuleProcessor.class, named(RULE_9_PROCESSOR), Rule9Processor.class)
                .implement(RuleProcessor.class, named(RULE_10_PROCESSOR), Rule10Processor.class)
                .implement(RuleProcessor.class, named(RULE_11_AND_12_PROCESSOR), Rule11And12Processor.class)
                .implement(RuleProcessor.class, named(RULE_13_PROCESSOR), Rule13Processor.class)
                .implement(RuleProcessor.class, named(RULE_14_PROCESSOR), Rule14Processor.class)
                .implement(RuleProcessor.class, named(RULE_16_PROCESSOR), Rule16Processor.class)
                .implement(RuleProcessor.class, named(RULE_17_PROCESSOR), Rule17Processor.class)
                .implement(RuleProcessor.class, named(RULE_19_PROCESSOR), Rule19Processor.class)
                .implement(RuleProcessor.class, named(RULE_20_PROCESSOR), Rule20Processor.class)
                .build(RuleProcessorFactory.class));
    }
}
