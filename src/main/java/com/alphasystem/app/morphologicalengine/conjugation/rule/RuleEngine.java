package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.app.morphologicalengine.conjugation.rule.processor.HamzahChairProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

;

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
    private final RuleProcessor imperativeProcessor;
    private final RuleProcessor patternProcessor;
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

    public RuleEngine(final RuleInfo ruleInfo, final RuleProcessorFactory doubleLetteredProcessorFactory,
                      final RuleProcessorFactory formVIIIProcessorFactory,
                      final RuleProcessorFactory hamzahChairProcessorFactory,
                      final RuleProcessorFactory hamzaRule7ProcessorFactory,
                      final RuleProcessorFactory hamzatedFirstRadicalProcessorFactory,
                      final RuleProcessorFactory hamzatedThirdRadicalProcessorFactory,
                      final RuleProcessorFactory imperativeProcessorFactory,
                      final RuleProcessorFactory patternProcessorFactory,
                      final RuleProcessorFactory prefixProcessorFactory,
                      final RuleProcessorFactory rule1ProcessorFactory,
                      final RuleProcessorFactory rule7ProcessorFactory,
                      final RuleProcessorFactory rule8ProcessorFactory,
                      final RuleProcessorFactory rule9ProcessorFactory,
                      final RuleProcessorFactory rule10ProcessorFactory,
                      final RuleProcessorFactory rule11And12ProcessorFactory,
                      final RuleProcessorFactory rule13ProcessorFactory,
                      final RuleProcessorFactory rule14ProcessorFactory,
                      final RuleProcessorFactory rule15ProcessorFactory,
                      final RuleProcessorFactory rule17ProcessorFactory,
                      final RuleProcessorFactory rule19ProcessorFactory,
                      final RuleProcessorFactory rule20ProcessorFactory) {
        super(ruleInfo);
        doubleLetteredProcessor = doubleLetteredProcessorFactory.createRuleProcessor(ruleInfo);
        formVIIIProcessor = formVIIIProcessorFactory.createRuleProcessor(ruleInfo);
        hamzahChairProcessor = hamzahChairProcessorFactory.createRuleProcessor(ruleInfo);
        hamzaRule7Processor = hamzaRule7ProcessorFactory.createRuleProcessor(ruleInfo);
        hamzatedFirstRadicalProcessor = hamzatedFirstRadicalProcessorFactory.createRuleProcessor(ruleInfo);
        hamzatedThirdRadicalProcessor = hamzatedThirdRadicalProcessorFactory.createRuleProcessor(ruleInfo);
        imperativeProcessor = imperativeProcessorFactory.createRuleProcessor(ruleInfo);
        patternProcessor = patternProcessorFactory.createRuleProcessor(ruleInfo);
        prefixProcessor = prefixProcessorFactory.createRuleProcessor(ruleInfo);
        rule1Processor = rule1ProcessorFactory.createRuleProcessor(ruleInfo);
        rule7Processor = rule7ProcessorFactory.createRuleProcessor(ruleInfo);
        rule8Processor = rule8ProcessorFactory.createRuleProcessor(ruleInfo);
        rule9Processor = rule9ProcessorFactory.createRuleProcessor(ruleInfo);
        rule10Processor = rule10ProcessorFactory.createRuleProcessor(ruleInfo);
        rule11And12Processor = rule11And12ProcessorFactory.createRuleProcessor(ruleInfo);
        rule13Processor = rule13ProcessorFactory.createRuleProcessor(ruleInfo);
        rule14Processor = rule14ProcessorFactory.createRuleProcessor(ruleInfo);
        rule16Processor = rule15ProcessorFactory.createRuleProcessor(ruleInfo);
        rule17Processor = rule17ProcessorFactory.createRuleProcessor(ruleInfo);
        rule19Processor = rule19ProcessorFactory.createRuleProcessor(ruleInfo);
        rule20Processor = rule20ProcessorFactory.createRuleProcessor(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord rootWord) {
        ((HamzahChairProcessor) hamzahChairProcessor).setHamzahReplacement(ruleInfo.getHamzahReplacement());
        formVIIIProcessor.applyRules(rootWord);
        if (!ruleInfo.isSkipRuleProcessing()) {
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
        }
        imperativeProcessor.applyRules(rootWord);
        doubleLetteredProcessor.applyRules(rootWord);
        hamzatedFirstRadicalProcessor.applyRules(rootWord);
        hamzatedThirdRadicalProcessor.applyRules(rootWord);
        hamzahChairProcessor.applyRules(rootWord);
        prefixProcessor.applyRules(rootWord);
        patternProcessor.applyRules(rootWord);
        return rootWord;
    }
}
