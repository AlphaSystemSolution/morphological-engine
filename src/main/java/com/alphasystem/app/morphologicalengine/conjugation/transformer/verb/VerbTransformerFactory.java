package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
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
}
