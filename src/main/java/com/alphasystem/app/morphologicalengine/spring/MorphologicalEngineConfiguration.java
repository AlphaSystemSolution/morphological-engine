package com.alphasystem.app.morphologicalengine.spring;

import com.alphasystem.app.morphologicalengine.conjugation.builder.BuilderConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author sali
 */
@Configuration
@Import({RuleProcessorConfiguration.class, NounTransformerConfiguration.class, NounTransformerFactoryConfiguration.class,
        VerbTransformerConfiguration.class, VerbTransformerFactoryConfiguration.class, BuilderConfiguration.class})
public class MorphologicalEngineConfiguration {

    @Bean
    MorphologicalEngineFactory morphologicalEngineFactory() {
        return new MorphologicalEngineFactory();
    }
}
