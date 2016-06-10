package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.google.inject.AbstractModule;

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
        bind(VerbTransformer.class).annotatedWith(named(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)).toInstance(new PastTenseThirdPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)).toInstance(new PastTenseThirdPersonFeminineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)).toInstance(new PastTenseSecondPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)).toInstance(new PastTenseSecondPersonFeminineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PAST_TENSE_FIRST_PERSON_TRANSFORMER)).toInstance(new PastTenseFirstPersonTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)).toInstance(new PresentTenseThirdPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)).toInstance(new PresentTenseThirdPersonFeminineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)).toInstance(new PresentTenseSecondPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)).toInstance(new PresentTenseSecondPersonFeminineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER)).toInstance(new PresentTenseFirstPersonTransformer());
    }
}
