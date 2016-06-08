package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.google.inject.assistedinject.Assisted;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;

/**
 * @author sali
 */
public interface VerbTransformerFactory {

    @Named(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer getPastTenseThirdPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer getPastTenseThirdPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer getPastTenseSecondPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer getPastTenseSecondPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PAST_TENSE_FIRST_PERSON_TRANSFORMER)
    VerbTransformer getPastTenseFirstPersonTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer getPresentTenseThirdPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer getPresentTenseThirdPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer getPresentTenseSecondPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer getPresentTenseSecondPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);

    @Named(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER)
    VerbTransformer getPresentTenseFirstPersonTransformer(@Assisted @Nullable RuleProcessor ruleProcessor);
}
