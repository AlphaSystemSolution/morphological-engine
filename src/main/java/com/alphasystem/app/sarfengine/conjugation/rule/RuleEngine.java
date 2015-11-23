package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.app.sarfengine.conjugation.rule.processor.HamzahChairProcessor;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.NamedTemplate;
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

    public RootWord applyRules(NamedTemplate template, RootWord rootWord) {
        ((HamzahChairProcessor) hamzahChairProcessor).setHamzahReplacement(ruleInfo.getHamzahReplacement());
        formVIIIProcessor.applyRules(template, rootWord);
        rule14Processor.applyRules(template, rootWord);
        rule1Processor.applyRules(template, rootWord);
        rule7Processor.applyRules(template, rootWord);
        rule9Processor.applyRules(template, rootWord);
        rule8Processor.applyRules(template, rootWord);
        rule10Processor.applyRules(template, rootWord);
        rule11And12Processor.applyRules(template, rootWord);
        rule13Processor.applyRules(template, rootWord);
        rule16Processor.applyRules(template, rootWord);
        rule17Processor.applyRules(template, rootWord);
        rule20Processor.applyRules(template, rootWord);
        rule19Processor.applyRules(template, rootWord);
        doubleLetteredProcessor.applyRules(template, rootWord);
        hamzatedFirstRadicalProcessor.applyRules(template, rootWord);
        hamzatedThirdRadicalProcessor.applyRules(template, rootWord);
        hamzahChairProcessor.applyRules(template, rootWord);
        prefixProcessor.applyRules(template, rootWord);
        return rootWord;
    }
}
