package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sali
 */
@Configuration
public class NounTransformerConfiguration {

    @Bean
    @Transformer(Transformer.TransformerType.MASCULINE_ENDING_SOUND_TRANSFORMER)
    public NounTransformer masculineEndingSoundTransformer() {
        return new MasculineEndingSoundTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.FEMININE_ENDING_SOUND_TRANSFORMER)
    public NounTransformer feminineEndingSoundTransformer() {
        return new FeminineEndingSoundTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.MASCULINE_DUAL_TRANSFORMER)
    public NounTransformer masculineDualTransformer() {
        return new MasculineDualTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.FEMININE_DUAL_TRANSFORMER)
    public NounTransformer feminineDualTransformer() {
        return new FeminineDualTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.MASCULINE_PLURAL_TRANSFORMER)
    public NounTransformer masculinePluralTransformer() {
        return new MasculinePluralTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.FEMININE_PLURAL_TRANSFORMER)
    public NounTransformer femininePluralTransformer() {
        return new FemininePluralTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER)
    public NounTransformer feminineMasculineBasedPluralTransformer() {
        return new FeminineMasculineBasedPluralTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    public NounTransformer partlyFlexibleNounTransformer() {
        return new PartlyFlexibleNounTransformer();
    }

    @Bean
    @Transformer(Transformer.TransformerType.NON_FLEXIBLE_NOUN_TRANSFORMER)
    public NounTransformer nonFlexibleNounTransformer() {
        return new NonFlexibleNounTransformer();
    }


}
