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

    @Bean
    @RuleProcessorType(DOUBLE_LETTERED_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory doubleLetteredProcessorFactory() {
        return DoubleLetteredProcessor::new;
    }

    @Bean
    @RuleProcessorType(FORM_VIII_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory formVIIIProcessorFactory() {
        return FormVIIIProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZAH_CHAIR_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzahChairProcessorFactory() {
        return HamzahChairProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZA_RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzaRule7ProcessorFactory() {
        return HamzaRule7Processor::new;
    }

    @Bean
    @RuleProcessorType(HAMZATED_FIRST_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedFirstRadicalProcessorFactory() {
        return HamzatedFirstRadicalProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZATED_THIRD_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedThirdRadicalProcessorFactory() {
        return HamzatedThirdRadicalProcessor::new;
    }

    @Bean
    @RuleProcessorType(IMPERATIVE_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory imperativeProcessorFactory() {
        return ImperativeProcessor::new;
    }

    @Bean
    @RuleProcessorType(PATTERN_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory patternProcessorFactory() {
        return PatternProcessor::new;
    }

    @Bean
    @RuleProcessorType(PREFIX_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory prefixProcessorFactory() {
        return PrefixProcessor::new;
    }

    @Bean
    @RuleProcessorType(RULE_1_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule1ProcessorFactory() {
        return Rule1Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule7ProcessorFactory() {
        return Rule7Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_8_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule8ProcessorFactory() {
        return Rule8Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_9_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule9ProcessorFactory() {
        return Rule9Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_10_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule10ProcessorFactory() {
        return Rule10Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_11_AND_12_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule11And12ProcessorFactory() {
        return Rule11And12Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_13_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule13ProcessorFactory() {
        return Rule13Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_14_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule14ProcessorFactory() {
        return Rule14Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_16_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule16ProcessorFactory() {
        return Rule16Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_17_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule17ProcessorFactory() {
        return Rule17Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_19_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule19ProcessorFactory() {
        return Rule19Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_20_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule20ProcessorFactory() {
        return Rule20Processor::new;
    }

    @Bean
    @RuleProcessorType(RuleProcessorType.Type.RULE_ENGINE)
    @Scope("prototype")
    RuleProcessorFactory ruleEngineFactory() {
        return new RuleEngineFactory();
    }
}
