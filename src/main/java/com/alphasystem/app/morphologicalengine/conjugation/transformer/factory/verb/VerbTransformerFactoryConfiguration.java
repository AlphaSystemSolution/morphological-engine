package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author sali
 */
@Configuration
public class VerbTransformerFactoryConfiguration {

    @Bean
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PAST_TENSE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory pastTenseTransformerFactory(){
        return new PastTenseTransformerFactory();
    }

    @Bean
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PRESENT_TENSE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory presentTenseTransformerFactory(){
        return new PresentTenseTransformerFactory();
    }

    @Bean
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.IMPERATIVE_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory imperativeTransformerFactory(){
        return new ImperativeTransformerFactory();
    }

    @Bean
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.FORBIDDING_TRANSFORMER_FACTORY)
    @Scope("prototype")
    VerbTransformerFactory ForbiddingTransformerFactory(){
        return new ForbiddingTransformerFactory();
    }
}
