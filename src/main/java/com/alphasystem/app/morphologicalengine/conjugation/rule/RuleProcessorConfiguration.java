package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.app.morphologicalengine.conjugation.rule.processor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.*;

/**
 * @author sali
 */
@Configuration
public class RuleProcessorConfiguration {

    @Bean("DOUBLE_LETTERED_PROCESSOR")
    @RuleProcessorType(DOUBLE_LETTERED_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory doubleLetteredProcessorFactory() {
        return DoubleLetteredProcessor::new;
    }

    @Bean("FORM_VIII_PROCESSOR")
    @RuleProcessorType(FORM_VIII_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory formVIIIProcessorFactory() {
        return FormVIIIProcessor::new;
    }

    @Bean("HAMZAH_CHAIR_PROCESSOR")
    @RuleProcessorType(HAMZAH_CHAIR_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzahChairProcessorFactory() {
        return HamzahChairProcessor::new;
    }

    @Bean("HAMZA_RULE_7_PROCESSOR")
    @RuleProcessorType(HAMZA_RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzaRule7ProcessorFactory() {
        return HamzaRule7Processor::new;
    }

    @Bean("HAMZATED_FIRST_RADICAL_PROCESSOR")
    @RuleProcessorType(HAMZATED_FIRST_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedFirstRadicalProcessorFactory() {
        return HamzatedFirstRadicalProcessor::new;
    }

    @Bean("HAMZATED_THIRD_RADICAL_PROCESSOR")
    @RuleProcessorType(HAMZATED_THIRD_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedThirdRadicalProcessorFactory() {
        return HamzatedThirdRadicalProcessor::new;
    }

    @Bean("IMPERATIVE_PROCESSOR")
    @RuleProcessorType(IMPERATIVE_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory imperativeProcessorFactory() {
        return ImperativeProcessor::new;
    }

    @Bean("PATTERN_PROCESSOR")
    @RuleProcessorType(PATTERN_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory patternProcessorFactory() {
        return PatternProcessor::new;
    }

    @Bean("PREFIX_PROCESSOR")
    @RuleProcessorType(PREFIX_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory prefixProcessorFactory() {
        return PrefixProcessor::new;
    }

    @Bean("RULE_1_PROCESSOR")
    @RuleProcessorType(RULE_1_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule1ProcessorFactory() {
        return Rule1Processor::new;
    }

    @Bean("RULE_7_PROCESSOR")
    @RuleProcessorType(RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule7ProcessorFactory() {
        return Rule7Processor::new;
    }

    @Bean("RULE_8_PROCESSOR")
    @RuleProcessorType(RULE_8_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule8ProcessorFactory() {
        return Rule8Processor::new;
    }

    @Bean("RULE_9_PROCESSOR")
    @RuleProcessorType(RULE_9_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule9ProcessorFactory() {
        return Rule9Processor::new;
    }

    @Bean("RULE_10_PROCESSOR")
    @RuleProcessorType(RULE_10_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule10ProcessorFactory() {
        return Rule10Processor::new;
    }

    @Bean("RULE_11_AND_12_PROCESSOR")
    @RuleProcessorType(RULE_11_AND_12_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule11And12ProcessorFactory() {
        return Rule11And12Processor::new;
    }

    @Bean("RULE_13_PROCESSOR")
    @RuleProcessorType(RULE_13_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule13ProcessorFactory() {
        return Rule13Processor::new;
    }

    @Bean("RULE_14_PROCESSOR")
    @RuleProcessorType(RULE_14_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule14ProcessorFactory() {
        return Rule14Processor::new;
    }

    @Bean("RULE_16_PROCESSOR")
    @RuleProcessorType(RULE_16_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule16ProcessorFactory() {
        return Rule16Processor::new;
    }

    @Bean("RULE_17_PROCESSOR")
    @RuleProcessorType(RULE_17_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule17ProcessorFactory() {
        return Rule17Processor::new;
    }

    @Bean("RULE_19_PROCESSOR")
    @RuleProcessorType(RULE_19_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule19ProcessorFactory() {
        return Rule19Processor::new;
    }

    @Bean("RULE_20_PROCESSOR")
    @RuleProcessorType(RULE_20_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule20ProcessorFactory() {
        return Rule20Processor::new;
    }

    @Bean("RULE_ENGINE")
    @RuleProcessorType(RULE_ENGINE)
    @Scope("prototype")
    RuleProcessorFactory ruleEngineFactory() {
        return new RuleEngineFactory();
    }
}
