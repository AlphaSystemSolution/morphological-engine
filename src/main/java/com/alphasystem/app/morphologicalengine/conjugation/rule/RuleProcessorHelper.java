/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.arabic.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

;

/**
 * @author sali
 */
public class RuleProcessorHelper {

    public static final ArabicLetterType[] HEAVY_LETTERS = new ArabicLetterType[]{
            HA, KHA, AIN, GHAIN, HHA, HAMZA};

    public static final SarfMemberType[] THIRD_PERSON_FEMENINE_PLURAL_AND_SECOND_AND_FIRST_PERSONS_MEMBERS = {
            THIRD_PERSON_FEMININE_PLURAL, SECOND_PERSON_MASCULINE_SINGULAR,
            SECOND_PERSON_MASCULINE_DUAL, SECOND_PERSON_MASCULINE_PLURAL,
            SECOND_PERSON_FEMININE_SINGULAR, SECOND_PERSON_FEMININE_DUAL,
            SECOND_PERSON_FEMININE_PLURAL, FIRST_PERSON_SINGULAR,
            FIRST_PERSON_PLURAL};

    public static final SarfTermType[] NOUN_BASED_TYPES = {
            ACTIVE_PARTICIPLE_MASCULINE, ACTIVE_PARTICIPLE_FEMININE,
            PASSIVE_PARTICIPLE_MASCULINE, PASSIVE_PARTICIPLE_FEMININE,
            VERBAL_NOUN, NOUN_OF_PLACE_AND_TIME};

    public static final SarfTermType[] VERB_BASED_TYPES = {PAST_TENSE,
            PRESENT_TENSE, PAST_PASSIVE_TENSE, PRESENT_PASSIVE_TENSE,
            IMPERATIVE, FORBIDDING};

    public static final SarfMemberType[] IMPERFECT_SINGULAR = {
            THIRD_PERSON_MASCULINE_SINGULAR, THIRD_PERSON_FEMININE_SINGULAR,
            SECOND_PERSON_MASCULINE_SINGULAR, FIRST_PERSON_SINGULAR,
            FIRST_PERSON_PLURAL};

    public static final SarfMemberType[] IMPERFECT_DUALS = {
            THIRD_PERSON_FEMININE_DUAL, THIRD_PERSON_MASCULINE_DUAL,
            SECOND_PERSON_FEMININE_DUAL, SECOND_PERSON_MASCULINE_DUAL};

    public static void checkArgument(RootWord baseRootWord,
                                     SarfTermType... validTerms) throws IllegalArgumentException {
        if (baseRootWord == null) {
            throw new IllegalArgumentException("rootWord is null");
        }
        ArabicWord src = baseRootWord.getRootWord();
        if (src == null) {
            throw new IllegalArgumentException("ArabicWord is null");
        }
        if (baseRootWord.getFirstRadicalIndex() < 0
                || baseRootWord.getSecondRadicalIndex() < 0
                || baseRootWord.getThirdRadicalIndex() < 0) {
            throw new IllegalArgumentException(
                    "One of radical is negative");
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        if (!isEmpty(validTerms) && !contains(validTerms, sarfTermType)) {
            throw new IllegalArgumentException(format(
                    "Unexpected Sarf Term {%s}", sarfTermType));
        }
    }

    public static void checkArgument(RootWord baseRootWord,
                                     SarfTermType[] validTerms, SarfTermType[] invalidTerms) {
        if (baseRootWord == null) {
            throw new IllegalArgumentException("rootWord is null");
        }
        ArabicWord src = baseRootWord.getRootWord();
        if (src == null) {
            throw new IllegalArgumentException("ArabicWord is null");
        }
        if (baseRootWord.getFirstRadicalIndex() < 0
                || baseRootWord.getSecondRadicalIndex() < 0
                || baseRootWord.getThirdRadicalIndex() < 0) {
            throw new IllegalArgumentException("One of index is negative");
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        if (!isEmpty(validTerms) && !contains(validTerms, sarfTermType)) {
            throw new IllegalArgumentException(format(
                    "Unexpected Sarf Term {%s}", sarfTermType));
        }
        if (!isEmpty(invalidTerms) && contains(invalidTerms, sarfTermType)) {
            throw new IllegalArgumentException(format(
                    "Unexpected Sarf Term {%s}", sarfTermType));
        }
    }

    public static DiacriticType getDiacritic(ArabicLetter letter) {
        DiacriticType[] diacritics = letter.getDiacritics();
        return isEmpty(diacritics) ? null : diacritics[0];
    }

    public static DiacriticType getDiacriticIncludingShadda(ArabicLetter letter) {
        DiacriticType[] diacritics = letter.getDiacritics();
        return isEmpty(diacritics) ? null : diacritics[diacritics.length - 1];
    }

    public static ArabicLetterType getHamzahReplacement(
            DiacriticType hamzahDiacritic) {
        ArabicLetterType hamzahReplacement = null;
        if (isKasra(hamzahDiacritic)) {
            hamzahReplacement = YA_HAMZA_ABOVE;
        } else if (isFatha(hamzahDiacritic)) {
            hamzahReplacement = ALIF_HAMZA_ABOVE;
        } else if (isDamma(hamzahDiacritic)) {
            hamzahReplacement = WAW_HAMZA_ABOVE;
        }
        return hamzahReplacement;
    }

    public static boolean isDamma(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(DAMMA);
    }

    public static boolean isDammatan(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(DAMMATAN);
    }

    public static boolean isFatha(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(FATHA);
    }

    public static boolean isFathatan(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(FATHATAN);
    }

    public static boolean isKasra(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(KASRA);
    }

    public static boolean isKasratan(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(KASRATAN);
    }

    public static boolean isLongAlif(ArabicLetterType arabicLetterType) {
        return arabicLetterType != null
                && (arabicLetterType.equals(ALIF) || arabicLetterType
                .equals(ALIF_MADDAH));
    }

    public static boolean isMaddaExtra(ArabicWord src,
                                       SarfTermType sarfTermType, int maddaIndex) {
        boolean result = false;
        // All Verb based doesn't contain madda extra
        if (maddaIndex > -1 && contains(NOUN_BASED_TYPES, sarfTermType)) {
            ArabicLetter previousLetter = src.getLetter(maddaIndex - 1);
            ArabicLetterType previousLetterType = previousLetter.getLetter();
            result = isWaw(previousLetterType) || isYa(previousLetterType);
        }
        return result;
    }

    public static boolean isMutaharik(DiacriticType diacritic) {
        return isKasra(diacritic) || isDamma(diacritic) || isFatha(diacritic)
                || isKasratan(diacritic) || isDammatan(diacritic)
                || isFathatan(diacritic);
    }

    public static boolean isMutaharikNoTanween(DiacriticType diacritic) {
        return isKasra(diacritic) || isDamma(diacritic) || isFatha(diacritic);
    }

    public static boolean isNounBasedType(SarfTermType src) {
        return contains(NOUN_BASED_TYPES, src);
    }

    public static boolean isSakin(DiacriticType diacritic) {
        return diacritic != null && diacritic.equals(SUKUN);
    }

    public static boolean isWaw(ArabicLetterType arabicLetterType) {
        return arabicLetterType != null && arabicLetterType.equals(WAW);
    }

    public static boolean is(ArabicLetterType arabicLetterType) {
        return arabicLetterType != null && arabicLetterType.equals(WAW);
    }

    public static boolean isYa(ArabicLetterType arabicLetterType) {
        return arabicLetterType != null && arabicLetterType.equals(YA);
    }

    public static int maddaIndex(ArabicWord src) {
        return maddaIndex(src, 1);
    }

    public static int maddaIndex(ArabicWord src, int startIndex) {
        int result = -1;
        int length = src.getLength();
        if (startIndex < 1 || startIndex >= length) {
            return result;
        }
        ArabicLetter previousLetter = src.getLetter(startIndex - 1);
        for (int i = startIndex; i < length; i++) {
            ArabicLetter currentLetter = src.getLetter(i);
            DiacriticType previousLetterDiacritic = getDiacritic(previousLetter);
            ArabicLetterType currentLetterType = currentLetter.getLetter();
            boolean yaMadda = isKasra(previousLetterDiacritic)
                    && currentLetterType.equals(YA);
            boolean wawMadda = isDamma(previousLetterDiacritic)
                    && currentLetterType.equals(WAW);
            boolean alifMadda = isFatha(previousLetterDiacritic)
                    && currentLetterType.equals(ALIF);
            if (yaMadda || wawMadda || alifMadda) {
                result = i;
                break;
            }
            previousLetter = currentLetter;
        }
        return result;
    }
}
