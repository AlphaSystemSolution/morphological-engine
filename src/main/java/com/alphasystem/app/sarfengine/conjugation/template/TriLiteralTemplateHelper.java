/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.template;

import com.alphasystem.app.sarfengine.conjugation.triliteralwords.*;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.ObjectFactory;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.*;

/**
 * @author sali
 */
public class TriLiteralTemplateHelper {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static RootWord createActiveParticipleFeminineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex,
                TriLiteralActiveParticipleFeminineBuilder.class.getName(),
                ACTIVE_PARTICIPLE_FEMININE, arabicLetters);
    }

    public static RootWord createActiveParticipleFeminineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass,
                ACTIVE_PARTICIPLE_FEMININE, arabicLetters);
    }

    public static RootWord createActiveParticipleMasculineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createActiveParticipleMasculineRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralActiveParticipleMasculineBuilder.class.getName(),
                arabicLetters);
    }

    public static RootWord createActiveParticipleMasculineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass,
                ACTIVE_PARTICIPLE_MASCULINE, arabicLetters);
    }

    public static RootWord createForbiddingRootWord(RootWord presentTenseRoot) {
        return createForbiddingRootWord(presentTenseRoot,
                DefaultTriLiteralForbiddingBuilder.class.getName());
    }

    public static RootWord createForbiddingRootWord(RootWord presentTenseRoot,
                                                    String implementationClass) {
        return new RootWord(presentTenseRoot).withImplementationClass(
                implementationClass).withSarfTermType(FORBIDDING);
    }

    public static RootWord createImperativeRootWord(RootWord presentTenseRoot) {
        return createImperativeRootWord(presentTenseRoot,
                DefaultTriLiteralImperativeBuilder.class.getName());
    }

    public static RootWord createImperativeRootWord(RootWord presentTenseRoot,
                                                    String implementationClass) {
        return new RootWord(presentTenseRoot).withImplementationClass(
                implementationClass).withSarfTermType(IMPERATIVE);
    }

    public static RootWord createPassiveParticipleFeminineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createPassiveParticipleFeminineRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralPassiveParticipleFeminineBuilder.class.getName(),
                arabicLetters);
    }

    public static RootWord createPassiveParticipleFeminineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass,
                PASSIVE_PARTICIPLE_FEMININE, arabicLetters);
    }

    public static RootWord createPassiveParticipleMasculineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createPassiveParticipleMasculineRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralPassiveParticipleMasculineBuilder.class.getName(),
                arabicLetters);
    }

    public static RootWord createPassiveParticipleMasculineRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass,
                PASSIVE_PARTICIPLE_MASCULINE, arabicLetters);
    }

    public static RootWord createPastPassiveTenseRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createPastPassiveTenseRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralPastPassiveBuilder.class.getName(), arabicLetters);
    }

    public static RootWord createPastPassiveTenseRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, PAST_PASSIVE_TENSE,
                arabicLetters);
    }

    public static RootWord createPastTenseRootWord(int firstRadicalIndex,
                                                   int secondRadicalIndex, int thirdRadicalIndex,
                                                   ArabicLetter... arabicLetters) {
        return createPastTenseRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, TriLiteralPastTenseBuilder.class.getName(),
                arabicLetters);
    }

    public static RootWord createPastTenseRootWord(int firstRadicalIndex,
                                                   int secondRadicalIndex, int thirdRadicalIndex,
                                                   String implementationClass, ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, PAST_TENSE,
                arabicLetters);
    }

    public static RootWord createPresentPassiveTenseRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, ArabicLetter... arabicLetters) {
        return createPresentPassiveTenseRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralPresentPassiveBuilder.class.getName(), arabicLetters);
    }

    public static RootWord createPresentPassiveTenseRootWord(
            int firstRadicalIndex, int secondRadicalIndex,
            int thirdRadicalIndex, String implementationClass,
            ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, PRESENT_PASSIVE_TENSE,
                arabicLetters);
    }

    public static RootWord createPresentTenseRootWord(int firstRadicalIndex,
                                                      int secondRadicalIndex, int thirdRadicalIndex,
                                                      ArabicLetter... arabicLetters) {
        return createPresentTenseRootWord(firstRadicalIndex,
                secondRadicalIndex, thirdRadicalIndex,
                TriLiteralPresentTenseBuilder.class.getName(), arabicLetters);
    }

    public static RootWord createPresentTenseRootWord(int firstRadicalIndex,
                                                      int secondRadicalIndex, int thirdRadicalIndex,
                                                      String implementationClass, ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, PRESENT_TENSE,
                arabicLetters);
    }

    public static RootWord createRootWord(int firstRadicalIndex,
                                          int secondRadicalIndex, int thirdRadicalIndex,
                                          ArabicLetter... arabicLetters) {
        ArabicWord arabicWord = new ArabicWord(arabicLetters);
        return OBJECT_FACTORY.createRootWord().withSarfTermType(PAST_TENSE)
                .withFirstRadicalIndex(firstRadicalIndex)
                .withSecondRadicalIndex(secondRadicalIndex)
                .withThirdRadicalIndex(thirdRadicalIndex)
                .withFourthRadicalIndex(-1).withBaseWord(arabicWord)
                .withRootWord(arabicWord);
    }

    public static RootWord createRootWord(int firstRadicalIndex,
                                          int secondRadicalIndex, int thirdRadicalIndex,
                                          String implementationClass, SarfTermType sarfTermType,
                                          ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, arabicLetters).withImplementationClass(
                implementationClass).withSarfTermType(sarfTermType);
    }

    // public static RootWord createVerbalNounFamineRootWord(
    // int firstRadicalIndex, int secondRadicalIndex,
    // int thirdRadicalIndex, ArabicLetter... arabicLetters) {
    // return createVerbalNounRootWord(firstRadicalIndex, secondRadicalIndex,
    // thirdRadicalIndex,
    // TriLiteralVerbalNounFaminineBuilder.class.getName(),
    // arabicLetters);
    // }

    public static RootWord createVerbalNounRootWord(int firstRadicalIndex,
                                                    int secondRadicalIndex, int thirdRadicalIndex,
                                                    ArabicLetter... arabicLetters) {
        return createVerbalNounRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex,
                DefaultTriLiteralVerbalNounBuilder.class.getName(),
                arabicLetters);
    }

    public static RootWord createVerbalNounRootWord(int firstRadicalIndex,
                                                    int secondRadicalIndex, int thirdRadicalIndex,
                                                    String implementationClass, ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, VERBAL_NOUN,
                arabicLetters);
    }

    public static RootWord createZarfRootWord(int firstRadicalIndex,
                                              int secondRadicalIndex, int thirdRadicalIndex,
                                              ArabicLetter... arabicLetters) {
        return createZarfRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex,
                DefaultTriLiteralZarfBuilder.class.getName(), arabicLetters);
    }

    public static RootWord createZarfRootWord(int firstRadicalIndex,
                                              int secondRadicalIndex, int thirdRadicalIndex,
                                              String implementationClass, ArabicLetter... arabicLetters) {
        return createRootWord(firstRadicalIndex, secondRadicalIndex,
                thirdRadicalIndex, implementationClass, NOUN_OF_PLACE_AND_TIME,
                arabicLetters);
    }
}
