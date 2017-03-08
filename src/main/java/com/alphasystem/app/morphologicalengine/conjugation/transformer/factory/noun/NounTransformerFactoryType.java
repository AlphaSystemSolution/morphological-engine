package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

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
public @interface NounTransformerFactoryType {

    Type value();

    enum Type {
        MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY, FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY,
        MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, MASCULINE_BASED_PARTLY_FLEXIBLE_PLURAL_TRANSFORMER_FACTORY
    }

}
