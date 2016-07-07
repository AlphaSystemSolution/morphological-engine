package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import com.google.inject.AbstractModule;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.Verb.*;
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
    public static final String FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER = "ForbiddingSecondPersonMasculineTransformer";
    public static final String FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER = "ForbiddingSecondPersonFeminineTransformer";
    public static final String IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER = "ImperativeSecondPersonMasculineTransformer";
    public static final String IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER = "ImperativeSecondPersonFeminineTransformer";

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
        bind(VerbTransformer.class).annotatedWith(named(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER)).toInstance(new ForbiddingSecondPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER)).toInstance(new ForbiddingSecondPersonFeminineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER)).toInstance(new ImperativeSecondPersonMasculineTransformer());
        bind(VerbTransformer.class).annotatedWith(named(IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER)).toInstance(new ImperativeSecondPersonFeminineTransformer());

        // PAST TENSE
        bindPastTense(FORM_I_PAST_TENSE_V1);
        bindPastTense(FORM_I_PAST_TENSE_V2);
        bindPastTense(FORM_I_PAST_TENSE_V3);
        bindPastTense(FORM_II_PAST_TENSE);
        bindPastTense(FORM_III_PAST_TENSE);
        bindPastTense(FORM_IV_PAST_TENSE);
        bindPastTense(FORM_V_PAST_TENSE);
        bindPastTense(FORM_VI_PAST_TENSE);
        bindPastTense(FORM_VII_PAST_TENSE);
        bindPastTense(FORM_VIII_PAST_TENSE);
        bindPastTense(FORM_IX_PAST_TENSE);
        bindPastTense(FORM_X_PAST_TENSE);

        // PRESENT TENSE
        bindPresentTense(FORM_I_PRESENT_TENSE_V1);
        bindPresentTense(FORM_I_PRESENT_TENSE_V2);
        bindPresentTense(FORM_I_PRESENT_TENSE_V3);
        bindPresentTense(FORM_II_PRESENT_TENSE);
        bindPresentTense(FORM_III_PRESENT_TENSE);
        bindPresentTense(FORM_IV_PRESENT_TENSE);
        bindPresentTense(FORM_V_PRESENT_TENSE);
        bindPresentTense(FORM_VI_PRESENT_TENSE);
        bindPresentTense(FORM_VII_PRESENT_TENSE);
        bindPresentTense(FORM_VIII_PRESENT_TENSE);
        bindPresentTense(FORM_IX_PRESENT_TENSE);
        bindPresentTense(FORM_X_PRESENT_TENSE);

        // PAST PASSIVE TENSE
        bindPastTense(FORM_I_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_II_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_III_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_IV_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_V_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_VI_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_VIII_PAST_PASSIVE_TENSE);
        bindPastTense(FORM_X_PAST_PASSIVE_TENSE);

        // PRESENT PASSIVE TENSE
        bindPresentTense(FORM_I_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_II_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_III_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_IV_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_V_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_VI_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_VIII_PRESENT_PASSIVE_TENSE);
        bindPresentTense(FORM_X_PRESENT_PASSIVE_TENSE);

        // IMPERATIVE
        bindImperative(FORM_I_IMPERATIVE_V1);
        bindImperative(FORM_I_IMPERATIVE_V2);
        bindImperative(FORM_I_IMPERATIVE_V3);
        bindImperative(FORM_II_IMPERATIVE);
        bindImperative(FORM_III_IMPERATIVE);
        bindImperative(FORM_IV_IMPERATIVE);
        bindImperative(FORM_V_IMPERATIVE);
        bindImperative(FORM_VI_IMPERATIVE);
        bindImperative(FORM_VII_IMPERATIVE);
        bindImperative(FORM_VIII_IMPERATIVE);
        bindImperative(FORM_IX_IMPERATIVE);
        bindImperative(FORM_X_IMPERATIVE);

        // FORBIDDING
        bindForbidding(FORM_I_FORBIDDING_V1);
        bindForbidding(FORM_I_FORBIDDING_V2);
        bindForbidding(FORM_I_FORBIDDING_V3);
        bindForbidding(FORM_II_FORBIDDING);
        bindForbidding(FORM_III_FORBIDDING);
        bindForbidding(FORM_IV_FORBIDDING);
        bindForbidding(FORM_V_FORBIDDING);
        bindForbidding(FORM_VI_FORBIDDING);
        bindForbidding(FORM_VII_FORBIDDING);
        bindForbidding(FORM_VIII_FORBIDDING);
        bindForbidding(FORM_IX_FORBIDDING);
        bindForbidding(FORM_X_FORBIDDING);
    }

    private void bindPastTense(Verb verb) {
        bind(VerbTransformer.class).annotatedWith(named(verb.getThirdPersonMasculineName())).to(PastTenseThirdPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getThirdPersonFeminineName())).to(PastTenseThirdPersonFeminineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonMasculineName())).to(PastTenseSecondPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonFeminineName())).to(PastTenseSecondPersonFeminineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getFirstPersonName())).to(PastTenseFirstPersonTransformer.class);
    }

    private void bindPresentTense(Verb verb) {
        bind(VerbTransformer.class).annotatedWith(named(verb.getThirdPersonMasculineName())).to(PresentTenseThirdPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getThirdPersonFeminineName())).to(PresentTenseThirdPersonFeminineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonMasculineName())).to(PresentTenseSecondPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonFeminineName())).to(PresentTenseSecondPersonFeminineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getFirstPersonName())).to(PresentTenseFirstPersonTransformer.class);
    }

    private void bindImperative(Verb verb) {
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonMasculineName())).to(ImperativeSecondPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonFeminineName())).to(ImperativeSecondPersonFeminineTransformer.class);
    }

    private void bindForbidding(Verb verb) {
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonMasculineName())).to(ForbiddingSecondPersonMasculineTransformer.class);
        bind(VerbTransformer.class).annotatedWith(named(verb.getSecondPersonFeminineName())).to(ForbiddingSecondPersonFeminineTransformer.class);
    }

}
