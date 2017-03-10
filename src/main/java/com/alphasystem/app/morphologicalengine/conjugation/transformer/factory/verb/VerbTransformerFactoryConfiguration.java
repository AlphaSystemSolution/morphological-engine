package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType.Type.FORBIDDING_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType.Type.IMPERATIVE_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType.Type.PAST_TENSE_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType.Type.PRESENT_TENSE_TRANSFORMER_FACTORY;

/**
 * @author sali
 */
@Configuration
public class VerbTransformerFactoryConfiguration {

    @Bean("PAST_TENSE_TRANSFORMER_FACTORY")
    @VerbTransformerFactoryType(PAST_TENSE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory pastTenseTransformerFactory(){
        return new PastTenseTransformerFactory();
    }

    @Bean("PRESENT_TENSE_TRANSFORMER_FACTORY")
    @VerbTransformerFactoryType(PRESENT_TENSE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory presentTenseTransformerFactory(){
        return new PresentTenseTransformerFactory();
    }

    @Bean("IMPERATIVE_TRANSFORMER_FACTORY")
    @VerbTransformerFactoryType(IMPERATIVE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory imperativeTransformerFactory(){
        return new ImperativeTransformerFactory();
    }

    @Bean("FORBIDDING_TRANSFORMER_FACTORY")
    @VerbTransformerFactoryType(FORBIDDING_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory ForbiddingTransformerFactory(){
        return new ForbiddingTransformerFactory();
    }
}
