package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import javax.annotation.Nullable;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerModule.*;

/**
 * @author sali
 */
public interface NounTransformerFactory {

    @Named(MASCULINE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getMasculineEndingSoundTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(FEMININE_ENDING_SOUND_TRANSFORMER)
    NounTransformer getFeminineEndingSoundTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(MASCULINE_DUAL_TRANSFORMER)
    NounTransformer getMasculineDualTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(FEMININE_DUAL_TRANSFORMER)
    NounTransformer getFeminineDualTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(MASCULINE_PLURAL_TRANSFORMER)
    NounTransformer getMasculinePluralTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(FEMININE_PLURAL_TRANSFORMER)
    NounTransformer getFemininePluralTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER)
    NounTransformer getFeminineMasculineBasedPluralTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getPartlyFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                     @Assisted int variableIndex);

    @Named(PARTLY_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getPartlyFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(NON_FLEXIBLE_NOUN_TRANSFORMER)
    NounTransformer getNonFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

}
