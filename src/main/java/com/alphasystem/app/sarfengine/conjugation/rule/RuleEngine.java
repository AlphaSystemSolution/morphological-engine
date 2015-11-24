package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.app.sarfengine.conjugation.rule.processor.HamzahChairProcessor;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class RuleEngine extends AbstractRuleProcessor {

    private final RuleProcessor doubleLetteredProcessor;
    private final RuleProcessor formVIIIProcessor;
    private final RuleProcessor hamzahChairProcessor;
    private final RuleProcessor hamzaRule7Processor;
    private final RuleProcessor hamzatedFirstRadicalProcessor;
    private final RuleProcessor hamzatedThirdRadicalProcessor;
    private final RuleProcessor prefixProcessor;
    private final RuleProcessor rule1Processor;
    private final RuleProcessor rule7Processor;
    private final RuleProcessor rule8Processor;
    private final RuleProcessor rule9Processor;
    private final RuleProcessor rule10Processor;
    private final RuleProcessor rule11And12Processor;
    private final RuleProcessor rule13Processor;
    private final RuleProcessor rule14Processor;
    private final RuleProcessor rule16Processor;
    private final RuleProcessor rule17Processor;
    private final RuleProcessor rule19Processor;
    private final RuleProcessor rule20Processor;

    @AssistedInject
    public RuleEngine(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
        RuleProcessorFactory ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();
        doubleLetteredProcessor = ruleProcessorFactory.getDoubleLetteredProcessor(ruleInfo);
        formVIIIProcessor = ruleProcessorFactory.getFormVIIIProcessor(ruleInfo);
        hamzahChairProcessor = ruleProcessorFactory.getHamzahChairProcessor(ruleInfo);
        hamzaRule7Processor = ruleProcessorFactory.getHamzaRule7Processor(ruleInfo);
        hamzatedFirstRadicalProcessor = ruleProcessorFactory.getHamzatedFirstRadicalProcessor(ruleInfo);
        hamzatedThirdRadicalProcessor = ruleProcessorFactory.getHamzatedThirdRadicalProcessor(ruleInfo);
        prefixProcessor = ruleProcessorFactory.getPrefixProcessor(ruleInfo);
        rule1Processor = ruleProcessorFactory.getRule1Processor(ruleInfo);
        rule7Processor = ruleProcessorFactory.getRule7Processor(ruleInfo);
        rule8Processor = ruleProcessorFactory.getRule8Processor(ruleInfo);
        rule9Processor = ruleProcessorFactory.getRule9Processor(ruleInfo);
        rule10Processor = ruleProcessorFactory.getRule10Processor(ruleInfo);
        rule11And12Processor = ruleProcessorFactory.getRule11And12Processor(ruleInfo);
        rule13Processor = ruleProcessorFactory.getRule13Processor(ruleInfo);
        rule14Processor = ruleProcessorFactory.getRule14Processor(ruleInfo);
        rule16Processor = ruleProcessorFactory.getRule16Processor(ruleInfo);
        rule17Processor = ruleProcessorFactory.getRule17Processor(ruleInfo);
        rule19Processor = ruleProcessorFactory.getRule19Processor(ruleInfo);
        rule20Processor = ruleProcessorFactory.getRule20Processor(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord rootWord) {
        ((HamzahChairProcessor) hamzahChairProcessor).setHamzahReplacement(ruleInfo.getHamzahReplacement());
        formVIIIProcessor.applyRules(rootWord);
        rule14Processor.applyRules(rootWord);
        rule1Processor.applyRules(rootWord);
        rule7Processor.applyRules(rootWord);
        rule9Processor.applyRules(rootWord);
        rule8Processor.applyRules(rootWord);
        rule10Processor.applyRules(rootWord);
        rule11And12Processor.applyRules(rootWord);
        rule13Processor.applyRules(rootWord);
        rule16Processor.applyRules(rootWord);
        rule17Processor.applyRules(rootWord);
        rule20Processor.applyRules(rootWord);
        rule19Processor.applyRules(rootWord);
        doubleLetteredProcessor.applyRules(rootWord);
        hamzatedFirstRadicalProcessor.applyRules(rootWord);
        hamzatedThirdRadicalProcessor.applyRules(rootWord);
        hamzahChairProcessor.applyRules(rootWord);
        prefixProcessor.applyRules(rootWord);
        return rootWord;
    }
}
