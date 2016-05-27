package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import static com.alphasystem.app.sarfengine.conjugation.transformer.noun.TransformerModule.*;

/**
 * @author sali
 */
public interface TransformerFactory {

    @Named(MASCULINE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getMasculineEndingSoundTransformer();

    @Named(FEMININE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getFeminineEndingSoundTransformer();

    @Named(MASCULINE_DUAL_TRANSFORMER)
    NounTransformer getMasculineDualTransformer();

    @Named(FEMININE_DUAL_TRANSFORMER)
    NounTransformer getFeminineDualTransformer();

    @Named(MASCULINE_PLURAL_TRANSFORMER)
    NounTransformer getMasculinePluralTransformer();

    @Named(FEMININE_PLURAL_TRANSFORMER)
    NounTransformer getFemininePluralTransformer();

    @Named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getPartlyFlexibleNounTransformer(@Assisted int variableIndex);

    @Named(NON_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getNonFlexibleNounTransformer();
}
