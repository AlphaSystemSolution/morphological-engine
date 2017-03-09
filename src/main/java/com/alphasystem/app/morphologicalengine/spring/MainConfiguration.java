package com.alphasystem.app.morphologicalengine.spring;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleEngineConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.rule.processor.RuleProcessorConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author sali
 */
@Configuration
@Import({RuleProcessorConfiguration.class, RuleEngineConfiguration.class, NounTransformerConfiguration.class,
        NounTransformerFactoryConfiguration.class, VerbTransformerConfiguration.class, VerbTransformerFactoryConfiguration.class})
public class MainConfiguration {
}
