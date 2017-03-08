package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sali
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface VerbTransformerType {

    Type value();

    enum Type {
        PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER,
        PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER, PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER,
        PAST_TENSE_FIRST_PERSON_TRANSFORMER, PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER,
        PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER, PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER,
        PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER, PRESENT_TENSE_FIRST_PERSON_TRANSFORMER,
        FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER, FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER,
        IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER, IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER
    }
}
