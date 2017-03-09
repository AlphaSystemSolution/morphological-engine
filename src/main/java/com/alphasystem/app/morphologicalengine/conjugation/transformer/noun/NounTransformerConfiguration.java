package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author sali
 */
@Configuration
public class NounTransformerConfiguration {

    @Bean
    @NounTransformerType(NounTransformerType.Type.MASCULINE_ENDING_SOUND_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculineEndingSoundTransformer() {
        return new MasculineEndingSoundTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.FEMININE_ENDING_SOUND_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineEndingSoundTransformer() {
        return new FeminineEndingSoundTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.MASCULINE_DUAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculineDualTransformer() {
        return new MasculineDualTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.FEMININE_DUAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineDualTransformer() {
        return new FeminineDualTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.MASCULINE_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculinePluralTransformer() {
        return new MasculinePluralTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.FEMININE_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer femininePluralTransformer() {
        return new FemininePluralTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineMasculineBasedPluralTransformer() {
        return new FeminineMasculineBasedPluralTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer partlyFlexibleNounTransformer() {
        return new PartlyFlexibleNounTransformer();
    }

    @Bean
    @NounTransformerType(NounTransformerType.Type.NON_FLEXIBLE_NOUN_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer nonFlexibleNounTransformer() {
        return new NonFlexibleNounTransformer();
    }


}
