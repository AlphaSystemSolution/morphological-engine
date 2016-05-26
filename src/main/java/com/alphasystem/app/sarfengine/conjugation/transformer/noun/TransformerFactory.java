package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.transformer.noun.TransformerModule.*;

/**
 * @author sali
 */
public interface TransformerFactory {

    @Named(MASCULINE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getMasculineEndingSoundTransformer(@Assisted RootWord rootWord,
                                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                       @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(FEMININE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getFeminineEndingSoundTransformer(@Assisted RootWord rootWord,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                      @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(MASCULINE_DUAL_TRANSFORMER)
    NounTransformer getMasculineDualTransformer(@Assisted RootWord rootWord,
                                                @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(FEMININE_DUAL_TRANSFORMER)
    NounTransformer getFeminineDualTransformer(@Assisted RootWord rootWord,
                                               @Assisted("firstRadical") ArabicLetterType firstRadical,
                                               @Assisted("secondRadical") ArabicLetterType secondRadical,
                                               @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                               @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(MASCULINE_PLURAL_TRANSFORMER)
    NounTransformer getMasculinePluralTransformer(@Assisted RootWord rootWord,
                                                  @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                  @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                  @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                  @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(FEMININE_PLURAL_TRANSFORMER)
    NounTransformer getFemininePluralTransformer(@Assisted RootWord rootWord,
                                                 @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                 @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                 @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                 @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);

    @Named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getPartlyFlexibleNounTransformer(@Assisted RootWord rootWord,
                                                     @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                     @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                     @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                     @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical,
                                                     @Assisted int variableIndex);

    @Named(NON_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getNonFlexibleNounTransformer(@Assisted RootWord rootWord,
                                                  @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                  @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                  @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                  @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical);
}
