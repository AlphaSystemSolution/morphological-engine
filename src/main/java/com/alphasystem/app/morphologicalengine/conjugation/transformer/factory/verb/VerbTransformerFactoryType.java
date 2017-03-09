package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

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
public @interface VerbTransformerFactoryType {

    Type value();

    enum Type {
        PAST_TENSE_TRANSFORMER_FACTORY, PRESENT_TENSE_TRANSFORMER_FACTORY, IMPERATIVE_TRANSFORMER_FACTORY,
        FORBIDDING_TRANSFORMER_FACTORY
    }
}
