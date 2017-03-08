package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

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
public @interface NounTransformerType {

    Type value();

    enum Type {
        MASCULINE_ENDING_SOUND_TRANSFORMER, FEMININE_ENDING_SOUND_TRANSFORMER,
        MASCULINE_DUAL_TRANSFORMER,
        FEMININE_DUAL_TRANSFORMER,
        MASCULINE_PLURAL_TRANSFORMER,
        FEMININE_PLURAL_TRANSFORMER,
        FEMININE_MASCULINE_BASED_PLURAL_TRANSFORMER,
        PARTLY_FLEXIBLE_NOUN_TRANSFORMER,
        NON_FLEXIBLE_NOUN_TRANSFORMER
    }
}
