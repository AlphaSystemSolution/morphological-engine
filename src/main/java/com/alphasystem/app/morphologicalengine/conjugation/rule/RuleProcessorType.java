package com.alphasystem.app.morphologicalengine.conjugation.rule;

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
public @interface RuleProcessorType {

    Type value();

    enum Type {
        DOUBLE_LETTERED_PROCESSOR, FORM_VIII_PROCESSOR, HAMZAH_CHAIR_PROCESSOR, HAMZA_RULE_7_PROCESSOR,
        HAMZATED_FIRST_RADICAL_PROCESSOR, HAMZATED_THIRD_RADICAL_PROCESSOR, IMPERATIVE_PROCESSOR, PATTERN_PROCESSOR,
        PREFIX_PROCESSOR, RULE_1_PROCESSOR, RULE_7_PROCESSOR, RULE_8_PROCESSOR, RULE_9_PROCESSOR, RULE_10_PROCESSOR,
        RULE_11_AND_12_PROCESSOR, RULE_13_PROCESSOR, RULE_14_PROCESSOR, RULE_16_PROCESSOR, RULE_17_PROCESSOR,
        RULE_19_PROCESSOR, RULE_20_PROCESSOR, RULE_ENGINE
    }
}
