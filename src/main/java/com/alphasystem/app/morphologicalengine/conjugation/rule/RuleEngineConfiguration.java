package com.alphasystem.app.morphologicalengine.conjugation.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author sali
 */
@Configuration
public class RuleEngineConfiguration {

    @Bean
    @RuleProcessorType(RuleProcessorType.Type.RULE_ENGINE)
    @Scope("prototype")
    RuleEngineFactory ruleEngineFactory(){
        return new RuleEngineFactory();
    }
}
