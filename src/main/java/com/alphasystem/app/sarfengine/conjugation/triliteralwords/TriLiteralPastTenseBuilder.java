package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PAST_TENSE;

/**
 * @author sali
 */
public class TriLiteralPastTenseBuilder extends TriLiteralTenseBuilder {

    protected static final ArabicWord FIRST_PERSON_PLURAL_SUFFIX = new ArabicWord(
            NOON_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord SECOND_PERSON_DUAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord SECOND_PERSON_MASULINE_PLURAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, MEEM_WITH_SUKUN);

    protected static final ArabicWord SECOND_PERSON_FEMININE_PLURAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, NOON_WITH_SHADDA_AND_FATHA);

    protected static final ArabicWord THIRD_PERSON_MASCULINE_PLURAL_SUFFIX = new ArabicWord(
            WAW_WITH_SUKUN, LETTER_ALIF);

    protected static final ArabicWord THIRD_PERSON_FEMININE_DUAL_SUFFIX = new ArabicWord(
            TA_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord THIRD_PERSON_FEMININE_SINGULAR_SUFFIX = new ArabicWord(
            TA_WITH_SUKUN);

    /**
     * This is the suffix for Third person feminine plural, second person
     * masculine and feminine, and first person.
     */
    protected ArabicWord prefixForSecondAndFirstPerson;

    @Override
    protected ArabicWord doFirstPersonPlural() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(FIRST_PERSON_PLURAL_SUFFIX);
    }

    @Override
    protected ArabicWord doFirstPersonSingular() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(TA_WITH_DAMMA);
    }

    @Override
    protected ArabicWord doSecondPersonFemininePlural() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(SECOND_PERSON_FEMININE_PLURAL_SUFFIX);
    }

    @Override
    protected ArabicWord doSecondPersonFeminineSingular() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(TA_WITH_KASRA);
    }

    @Override
    protected ArabicWord doSecondPersonMasculineDual() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(SECOND_PERSON_DUAL_SUFFIX);
    }

    @Override
    protected ArabicWord doSecondPersonMasculinePlural() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(SECOND_PERSON_MASULINE_PLURAL_SUFFIX);
    }

    @Override
    protected ArabicWord doSecondPersonMasculineSingular() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(TA_WITH_FATHA);
    }

    @Override
    protected ArabicWord doThirdPersonFeminineDual() {
        return new ArabicWord(rootWord)
                .append(THIRD_PERSON_FEMININE_DUAL_SUFFIX);
    }

    @Override
    protected ArabicWord doThirdPersonFemininePlural() {
        return new ArabicWord(prefixForSecondAndFirstPerson)
                .append(NOON_WITH_FATHA);
    }

    @Override
    protected ArabicWord doThirdPersonFeminineSingular() {
        return new ArabicWord(rootWord)
                .append(THIRD_PERSON_FEMININE_SINGULAR_SUFFIX);
    }

    @Override
    protected ArabicWord doThirdPersonMasculineDual() {
        return new ArabicWord(rootWord).append(LETTER_ALIF);
    }

    @Override
    protected ArabicWord doThirdPersonMasculinePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(thirdRadicalIndex,
                DAMMA).append(THIRD_PERSON_MASCULINE_PLURAL_SUFFIX);
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_TENSE;
    }

    protected void initPrefixForSecondAndFirstPerson() {
        prefixForSecondAndFirstPerson = new ArabicWord(rootWord)
                .replaceDiacritic(thirdRadicalIndex, SUKUN);
    }

    @Override
    protected void postInit() {
        initPrefixForSecondAndFirstPerson();
        super.postInit();
    }

}
