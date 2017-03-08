package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sali
 */
@Configuration
public class NounTransformerFactoryConfiguration {

    @Bean
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    NounTransformerFactory masculineSoundPluralTransformerFactory() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    NounTransformerFactory feminineSoundPluralTransformerFactory() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY)
    NounTransformerFactory masculineBasedFemininePluralTransformerFactory() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.MASCULINE_BASED_PARTLY_FLEXIBLE_PLURAL_TRANSFORMER_FACTORY)
    NounTransformerFactory masculineBasedPartlyFlexiblePluralTransformerFactory() {
        return new MasculineBasedPartlyFlexiblePluralTransformerFactory();
    }

}
