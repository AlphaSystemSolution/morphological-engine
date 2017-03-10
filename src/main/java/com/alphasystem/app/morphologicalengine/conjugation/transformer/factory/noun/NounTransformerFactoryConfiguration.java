package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.MASCULINE_BASED_PARTLY_FLEXIBLE_PLURAL_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY;

/**
 * @author sali
 */
@Configuration
public class NounTransformerFactoryConfiguration {

    @Bean("MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY")
    @NounTransformerFactoryType(MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    @Scope("prototype")
    NounTransformerFactory masculineSoundPluralTransformerFactory() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean("FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY")
    @NounTransformerFactoryType(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    @Scope("prototype")
    NounTransformerFactory feminineSoundPluralTransformerFactory() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean("MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY")
    @NounTransformerFactoryType(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY)
    @Scope("prototype")
    NounTransformerFactory masculineBasedFemininePluralTransformerFactory() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean("MASCULINE_BASED_PARTLY_FLEXIBLE_PLURAL_TRANSFORMER_FACTORY")
    @NounTransformerFactoryType(MASCULINE_BASED_PARTLY_FLEXIBLE_PLURAL_TRANSFORMER_FACTORY)
    @Scope("prototype")
    NounTransformerFactory masculineBasedPartlyFlexiblePluralTransformerFactory() {
        return new MasculineBasedPartlyFlexiblePluralTransformerFactory();
    }

}
