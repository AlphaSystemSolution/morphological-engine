package com.alphasystem.app.morphologicalengine.spring;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.factory.NounTransformerFactoryConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author sali
 */
@Configuration
@Import({NounTransformerConfiguration.class, NounTransformerFactoryConfiguration.class, VerbTransformerConfiguration.class})
public class MainConfiguration {
}
