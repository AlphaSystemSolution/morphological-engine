package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType;
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
    RuleProcessorFactory doubleLetteredProcessor() {
        return DoubleLetteredProcessor::new;
    }

    @Bean
    @RuleProcessorType(FORM_VIII_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory formVIIIProcessor() {
        return FormVIIIProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZAH_CHAIR_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzahChairProcessor() {
        return HamzahChairProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZA_RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzaRule7Processor() {
        return HamzaRule7Processor::new;
    }

    @Bean
    @RuleProcessorType(HAMZATED_FIRST_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedFirstRadicalProcessor() {
        return HamzatedFirstRadicalProcessor::new;
    }

    @Bean
    @RuleProcessorType(HAMZATED_THIRD_RADICAL_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory hamzatedThirdRadicalProcessor() {
        return HamzatedThirdRadicalProcessor::new;
    }

    @Bean
    @RuleProcessorType(IMPERATIVE_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory imperativeProcessor() {
        return ImperativeProcessor::new;
    }

    @Bean
    @RuleProcessorType(PATTERN_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory patternProcessor() {
        return PatternProcessor::new;
    }

    @Bean
    @RuleProcessorType(PREFIX_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory prefixProcessor() {
        return PrefixProcessor::new;
    }

    @Bean
    @RuleProcessorType(RULE_1_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule1Processor() {
        return Rule1Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_7_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule7Processor() {
        return Rule7Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_8_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule8Processor() {
        return Rule8Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_9_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule9Processor() {
        return Rule9Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_10_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule10Processor() {
        return Rule10Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_11_AND_12_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule11And12Processor() {
        return Rule11And12Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_13_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule13Processor() {
        return Rule13Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_14_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule14Processor() {
        return Rule14Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_16_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule16Processor() {
        return Rule16Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_17_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule17Processor() {
        return Rule17Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_19_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule19Processor() {
        return Rule19Processor::new;
    }

    @Bean
    @RuleProcessorType(RULE_20_PROCESSOR)
    @Scope("prototype")
    RuleProcessorFactory rule20Processor() {
        return Rule20Processor::new;
    }

}
