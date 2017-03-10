package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.FEMININE_DUAL_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.FEMININE_ENDING_SOUND_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.FEMININE_PLURAL_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.MASCULINE_DUAL_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.MASCULINE_ENDING_SOUND_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.MASCULINE_PLURAL_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.NON_FLEXIBLE_NOUN_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType.Type.PARTLY_FLEXIBLE_NOUN_TRANSFORMER;

/**
 * @author sali
 */
@Configuration
public class NounTransformerConfiguration {

    @Bean("MASCULINE_ENDING_SOUND_TRANSFORMER")
    @NounTransformerType(MASCULINE_ENDING_SOUND_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculineEndingSoundTransformer() {
        return new MasculineEndingSoundTransformer();
    }

    @Bean("FEMININE_ENDING_SOUND_TRANSFORMER")
    @NounTransformerType(FEMININE_ENDING_SOUND_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineEndingSoundTransformer() {
        return new FeminineEndingSoundTransformer();
    }

    @Bean("MASCULINE_DUAL_TRANSFORMER")
    @NounTransformerType(MASCULINE_DUAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculineDualTransformer() {
        return new MasculineDualTransformer();
    }

    @Bean("FEMININE_DUAL_TRANSFORMER")
    @NounTransformerType(FEMININE_DUAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineDualTransformer() {
        return new FeminineDualTransformer();
    }

    @Bean("MASCULINE_PLURAL_TRANSFORMER")
    @NounTransformerType(MASCULINE_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer masculinePluralTransformer() {
        return new MasculinePluralTransformer();
    }

    @Bean("FEMININE_PLURAL_TRANSFORMER")
    @NounTransformerType(FEMININE_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer femininePluralTransformer() {
        return new FemininePluralTransformer();
    }

    @Bean("FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER")
    @NounTransformerType(FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer feminineMasculineBasedPluralTransformer() {
        return new FeminineMasculineBasedPluralTransformer();
    }

    @Bean("PARTLY_FLEXIBLE_NOUN_TRANSFORMER")
    @NounTransformerType(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer partlyFlexibleNounTransformer() {
        return new PartlyFlexibleNounTransformer();
    }

    @Bean("NON_FLEXIBLE_NOUN_TRANSFORMER")
    @NounTransformerType(NON_FLEXIBLE_NOUN_TRANSFORMER)
    @Scope("prototype")
    public NounTransformer nonFlexibleNounTransformer() {
        return new NonFlexibleNounTransformer();
    }


}
