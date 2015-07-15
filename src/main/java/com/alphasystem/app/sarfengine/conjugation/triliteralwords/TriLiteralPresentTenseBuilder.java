/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
public class TriLiteralPresentTenseBuilder extends TriLiteralTenseBuilder {

    protected static final ArabicWord THIRD_PERSON_DUAL_SUFFIX = new ArabicWord(
            LETTER_ALIF, NOON_WITH_KASRA);

    protected static final ArabicWord MASCULINE_PLURAL_SUFFIX = new ArabicWord(
            WAW_WITH_SUKUN, NOON_WITH_FATHA);

    protected static final ArabicWord THIRD_PERSON_FEMININE_PLURAL_SUFFIX = new ArabicWord(
            YA_WITH_SUKUN, NOON_WITH_FATHA);

    protected DiacriticType imperfectSignDiacritic;

    protected ArabicWord thirdPersonFeminineSingular;

    @Override
    protected ArabicWord doFirstPersonPlural() {
        return firsPersonConjugation(rootWord, NOON);
    }

    @Override
    protected ArabicWord doFirstPersonSingular() {
        return firsPersonConjugation(rootWord, ALIF_HAMZA_ABOVE);
    }

    @Override
    protected ArabicWord doSecondPersonFemininePlural() {
        return new ArabicWord(doThirdPersonFemininePlural()).replaceLetter(0,
                TA);
    }

    @Override
    protected ArabicWord doSecondPersonFeminineSingular() {
        return new ArabicWord(thirdPersonFeminineSingular).replaceDiacritic(
                thirdRadicalIndex, KASRA).append(
                THIRD_PERSON_FEMININE_PLURAL_SUFFIX);
    }

    @Override
    protected ArabicWord doSecondPersonMasculinePlural() {
        return new ArabicWord(doThirdPersonMasculinePlural()).replaceLetter(0,
                TA);
    }

    @Override
    protected ArabicWord doThirdPersonFeminineDual() {
        return new ArabicWord(thirdPersonFeminineSingular).replaceDiacritic(
                thirdRadicalIndex, FATHA).append(THIRD_PERSON_DUAL_SUFFIX);
    }

    @Override
    protected ArabicWord doThirdPersonFemininePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(thirdRadicalIndex,
                SUKUN).append(NOON_WITH_FATHA);
    }

    @Override
    protected ArabicWord doThirdPersonFeminineSingular() {
        return new ArabicWord(thirdPersonFeminineSingular);
    }

    @Override
    protected ArabicWord doThirdPersonMasculineDual() {
        return new ArabicWord(rootWord).replaceDiacritic(thirdRadicalIndex,
                FATHA).append(THIRD_PERSON_DUAL_SUFFIX);
    }

    @Override
    protected ArabicWord doThirdPersonMasculinePlural() {
        return new ArabicWord(rootWord).append(MASCULINE_PLURAL_SUFFIX);
    }

    protected ArabicWord firsPersonConjugation(ArabicWord rootWord,
                                               ArabicLetterType tenseSign) {
        return new ArabicWord(rootWord).replaceLetter(0, tenseSign);
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_TENSE;
    }

    @Override
    protected void postInit() {
        this.imperfectSignDiacritic = rootWord.getFirstLetter().getDiacritics()[0];
        this.thirdPersonFeminineSingular = new ArabicWord(this.rootWord)
                .replaceLetter(0, TA);
        super.postInit();
    }

}
