package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class VerbTransformerModule extends AbstractModule {

    public static final String PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER = "PastTenseThirdPersonMasculineTransformer";
    public static final String PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER = "PastTenseThirdPersonFeminineTransformer";
    public static final String PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER = "PastTenseSecondPersonMasculineTransformer";
    public static final String PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER = "PastTenseSecondPersonFeminineTransformer";
    public static final String PAST_TENSE_FIRST_PERSON_TRANSFORMER = "PastTenseFirstPersonTransformer";
    public static final String PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER = "PresentTenseThirdPersonMasculineTransformer";
    public static final String PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER = "PresentTenseThirdPersonFeminineTransformer";
    public static final String PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER = "PresentTenseSecondPersonMasculineTransformer";
    public static final String PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER = "PresentTenseSecondPersonFeminineTransformer";
    public static final String PRESENT_TENSE_FIRST_PERSON_TRANSFORMER = "PresentTenseFirstPersonTransformer";

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(VerbTransformer.class, named(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER), PastTenseThirdPersonMasculineTransformer.class)
                .implement(VerbTransformer.class, named(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER), PastTenseThirdPersonFeminineTransformer.class)
                .implement(VerbTransformer.class, named(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER), PastTenseSecondPersonMasculineTransformer.class)
                .implement(VerbTransformer.class, named(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER), PastTenseSecondPersonFeminineTransformer.class)
                .implement(VerbTransformer.class, named(PAST_TENSE_FIRST_PERSON_TRANSFORMER), PastTenseFirstPersonTransformer.class)
                .implement(VerbTransformer.class, named(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER), PresentTenseThirdPersonMasculineTransformer.class)
                .implement(VerbTransformer.class, named(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER), PresentTenseThirdPersonFeminineTransformer.class)
                .implement(VerbTransformer.class, named(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER), PresentTenseSecondPersonMasculineTransformer.class)
                .implement(VerbTransformer.class, named(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER), PresentTenseSecondPersonFeminineTransformer.class)
                .implement(VerbTransformer.class, named(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER), PresentTenseFirstPersonTransformer.class)
                .build(VerbTransformerFactory.class));
    }
}
