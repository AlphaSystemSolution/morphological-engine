package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class NounTransformerModule extends AbstractModule {

    public static final String MASCULINE_ENDING_SOUND_TRANSFORMER = "MasculineEndingSoundTransformer";
    public static final String FEMININE_ENDING_SOUND_TRANSFORMER = "FeminineEndingSoundTransformer";
    public static final String MASCULINE_DUAL_TRANSFORMER = "MasculineDualTransformer";
    public static final String FEMININE_DUAL_TRANSFORMER = "FeminineDualTransformer";
    public static final String MASCULINE_PLURAL_TRANSFORMER = "MasculinePluralTransformer";
    public static final String FEMININE_PLURAL_TRANSFORMER = "FemininePluralTransformer";
    public static final String FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER = "FeminineMasculineBasedPluralTransformer";
    public static final String PARTLY_FLEXIBLE_NOUN_TRANSFORMER = "PartlyFlexibleNounTransformer";
    public static final String NON_FLEXIBLE_NOUN_TRANSFORMER = "NonFlexibleNounTransformer";

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(NounTransformer.class, named(MASCULINE_ENDING_SOUND_TRANSFORMER), MasculineEndingSoundTransformer.class)
                .implement(NounTransformer.class, named(FEMININE_ENDING_SOUND_TRANSFORMER), FeminineEndingSoundTransformer.class)
                .implement(NounTransformer.class, named(MASCULINE_DUAL_TRANSFORMER), MasculineDualTransformer.class)
                .implement(NounTransformer.class, named(FEMININE_DUAL_TRANSFORMER), FeminineDualTransformer.class)
                .implement(NounTransformer.class, named(MASCULINE_PLURAL_TRANSFORMER), MasculinePluralTransformer.class)
                .implement(NounTransformer.class, named(FEMININE_PLURAL_TRANSFORMER), FemininePluralTransformer.class)
                .implement(NounTransformer.class, named(FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER), FeminineMasculineBasedPluralTransformer.class)
                .implement(NounTransformer.class, named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER), PartlyFlexibleNounTransformer.class)
                .implement(NounTransformer.class, named(NON_FLEXIBLE_NOUN_TRANSFORMER), NonFlexibleNounTransformer.class)
                .build(NounTransformerFactory.class));
    }
}
