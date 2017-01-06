package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.google.inject.AbstractModule;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural.BROKEN_PLURAL_V12;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural.BROKEN_PLURAL_V13;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.Noun.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.*;
import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class NounTransformerModule extends AbstractModule {

    public static final String MASCULINE_ENDING_SOUND_TRANSFORMER = "MasculineEndingSoundTransformer";
    private static final String FEMININE_ENDING_SOUND_TRANSFORMER = "FeminineEndingSoundTransformer";
    private static final String MASCULINE_DUAL_TRANSFORMER = "MasculineDualTransformer";
    private static final String FEMININE_DUAL_TRANSFORMER = "FeminineDualTransformer";
    private static final String MASCULINE_PLURAL_TRANSFORMER = "MasculinePluralTransformer";
    private static final String FEMININE_PLURAL_TRANSFORMER = "FemininePluralTransformer";
    private static final String FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER = "FeminineMasculineBasedPluralTransformer";
    private static final String PARTLY_FLEXIBLE_NOUN_TRANSFORMER = "PartlyFlexibleNounTransformer";
    public static final String NON_FLEXIBLE_NOUN_TRANSFORMER = "NonFlexibleNounTransformer";

    @Override
    protected void configure() {
        // Default bindings
        bind(NounTransformer.class).annotatedWith(named(MASCULINE_ENDING_SOUND_TRANSFORMER)).to(MasculineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(FEMININE_ENDING_SOUND_TRANSFORMER)).to(FeminineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(MASCULINE_DUAL_TRANSFORMER)).to(MasculineDualTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(FEMININE_DUAL_TRANSFORMER)).to(FeminineDualTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(MASCULINE_PLURAL_TRANSFORMER)).to(MasculinePluralTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(FEMININE_PLURAL_TRANSFORMER)).to(FemininePluralTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER)).to(FeminineMasculineBasedPluralTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)).to(PartlyFlexibleNounTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(NON_FLEXIBLE_NOUN_TRANSFORMER)).to(NonFlexibleNounTransformer.class);

        // START OF MASCULINE PARTICIPLES
        bindMasculineNoun(FORM_I_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_I_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_I_CATEGORY_U_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_II_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_II_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_III_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_III_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_IV_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_IV_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_V_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_V_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VI_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VI_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VII_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VII_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VIII_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE);
        bindMasculineNoun(FORM_IX_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_X_MASCULINE_ACTIVE_PARTICIPLE);
        bindMasculineNoun(FORM_X_MASCULINE_PASSIVE_PARTICIPLE);
        // END OF MASCULINE PARTICIPLES

        // START OF FEMININE PARTICIPLES
        bindFeminineNoun(FORM_I_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_I_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_I_CATEGORY_U_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_II_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_II_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_III_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_III_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_IV_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_IV_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_V_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_V_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VI_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VI_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VII_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VII_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VIII_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_VIII_FEMININE_PASSIVE_PARTICIPLE);
        bindFeminineNoun(FORM_IX_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_X_FEMININE_ACTIVE_PARTICIPLE);
        bindFeminineNoun(FORM_X_FEMININE_PASSIVE_PARTICIPLE);
        // END OF FEMININE PARTICIPLES

        // START OF VERBAL NOUN
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V1);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V2);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V3);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V4);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V5);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V6);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V7);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_V8);
        bindFeminineNoun(VERBAL_NOUN_V9);
        bindFeminineNoun(VERBAL_NOUN_V10);
        bindFeminineNoun(VERBAL_NOUN_V11);
        bindFeminineNoun(VERBAL_NOUN_V12);
        bindFeminineNoun(VERBAL_NOUN_V13);
        bindFeminineNoun(VERBAL_NOUN_V14);
        bindFeminineNoun(VERBAL_NOUN_V15);
        bindFeminineNoun(VERBAL_NOUN_V27);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_II);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_III_V1);
        bindFeminineNoun(VERBAL_NOUN_FORM_III_V2);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_IV);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_V);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_VI);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_VII);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_VIII);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_IX);
        bindMasculineBasedFemininePluralNoun(VERBAL_NOUN_FORM_X);
        // END OF VERBAL NOUN

        // START OF ADVERB
        bindBrokenPluralAdverb(NOUN_OF_PLACE_AND_TIME_V1);
        bindBrokenPluralAdverb(NOUN_OF_PLACE_AND_TIME_V2);
        bindFeminineNoun(NOUN_OF_PLACE_AND_TIME_V3);
        // END OF ADVERB

        // START OF BROKEN PLURAL
        bindBrokenPlural(BROKEN_PLURAL_V12);
        bindBrokenPlural(BROKEN_PLURAL_V13);
        // END OF BROKEN PLURAL
    }

    private void bindMasculineNoun(NounSupport noun) {
        bind(NounTransformer.class).annotatedWith(named(noun.getSingularRootName())).to(MasculineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getDualRootName())).to(MasculineDualTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getPluralRootName())).to(MasculinePluralTransformer.class);
    }

    private void bindFeminineNoun(NounSupport noun) {
        bind(NounTransformer.class).annotatedWith(named(noun.getSingularRootName())).to(FeminineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getDualRootName())).to(FeminineDualTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getPluralRootName())).to(FemininePluralTransformer.class);
    }

    private void bindMasculineBasedFemininePluralNoun(NounSupport noun) {
        bind(NounTransformer.class).annotatedWith(named(noun.getSingularRootName())).to(MasculineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getDualRootName())).to(MasculineDualTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(noun.getPluralRootName())).to(FeminineMasculineBasedPluralTransformer.class);
    }

    private void bindBrokenPluralAdverb(Noun nounOfPlaceAndTime){
        bind(NounTransformer.class).annotatedWith(named(nounOfPlaceAndTime.getSingularRootName())).to(MasculineEndingSoundTransformer.class);
        bind(NounTransformer.class).annotatedWith(named(nounOfPlaceAndTime.getDualRootName())).to(MasculineDualTransformer.class);
    }

    private void bindBrokenPlural(BrokenPlural brokenPlural){
        bind(NounTransformer.class).annotatedWith(named(brokenPlural.getPluralRootName())).to(PartlyFlexibleNounTransformer.class);
    }
}
