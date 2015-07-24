package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.app.sarfengine.guice.RuleProcessorModule;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import static com.alphasystem.app.sarfengine.guice.RuleProcessorModule.*;

/**
 * @author sali
 */
public interface RuleProcessorFactory {

    @Named(RuleProcessorModule.DEFAULT_RULE_PROCESSOR)
    RuleProcessor getRuleProcessor(@Assisted NamedTemplate template,
                                   @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                   @Assisted boolean pastTenseHasTransformed,
                                   @Assisted ArabicLetterType hamzahReplacement);

    @Named(DOUBLE_LETTERED_PROCESSOR)
    RuleProcessor getDoubleLetteredProcessor(@Assisted NamedTemplate template,
                                             @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                             @Assisted boolean pastTenseHasTransformed);

    @Named(FORM_VIII_PROCESSOR)
    RuleProcessor getFormVIIIProcessor(@Assisted NamedTemplate template,
                                       @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                       @Assisted boolean pastTenseHasTransformed);

    @Named(HAMZAH_CHAIR_PROCESSOR)
    RuleProcessor getHamzahChairProcessors(@Assisted NamedTemplate template,
                                           @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                           @Assisted boolean pastTenseHasTransformed,
                                           @Assisted ArabicLetterType hamzahReplacement);

    @Named(HAMZA_RULE_7_PROCESSOR)
    RuleProcessor getHamzaRule7Processor(@Assisted NamedTemplate template,
                                         @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                         @Assisted boolean pastTenseHasTransformed);

    @Named(HAMZATED_FIRST_RADICAL_PROCESSOR)
    RuleProcessor getHamzatedFirstRadicalProcessor(@Assisted NamedTemplate template,
                                                   @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                                   @Assisted boolean pastTenseHasTransformed);

    @Named(HAMZATED_THIRD_RADICAL_PROCESSOR)
    RuleProcessor getHamzatedThirdRadicalProcessor(@Assisted NamedTemplate template,
                                                   @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                                   @Assisted boolean pastTenseHasTransformed);

    @Named(PREFIX_PROCESSOR)
    RuleProcessor getPrefixProcessor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_1_PROCESSOR)
    RuleProcessor getRule1Processor(@Assisted NamedTemplate template,
                                    @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                    @Assisted boolean pastTenseHasTransformed);

    // Rulr 7 is setting "diacriticForWeakSecondRadicalWaw" and "pastTenseHasTransformed"
    @Named(RULE_7_PROCESSOR)
    RuleProcessor getRule7Processor(@Assisted NamedTemplate template,
                                    @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                    @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_8_PROCESSOR)
    RuleProcessor getRule8Processor(@Assisted NamedTemplate template,
                                    @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                    @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_9_PROCESSOR)
    RuleProcessor getRule9Processor(@Assisted NamedTemplate template,
                                    @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                    @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_10_PROCESSOR)
    RuleProcessor getRule10Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_11_AND_12_PROCESSOR)
    RuleProcessor getRule11And12Processor(@Assisted NamedTemplate template,
                                          @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                          @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_13_PROCESSOR)
    RuleProcessor getRule13Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_14_PROCESSOR)
    RuleProcessor getRule14Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_16_PROCESSOR)
    RuleProcessor getRule16Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_17_PROCESSOR)
    RuleProcessor getRule17Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_19_PROCESSOR)
    RuleProcessor getRule19Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);

    @Named(RULE_20_PROCESSOR)
    RuleProcessor getRule20Processor(@Assisted NamedTemplate template,
                                     @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                     @Assisted boolean pastTenseHasTransformed);
}
