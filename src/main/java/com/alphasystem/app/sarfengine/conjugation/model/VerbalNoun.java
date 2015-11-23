/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.app.sarfengine.util.TriLiteralTemplateHelper.createVerbalNounRootWord;

/**
 * @author sali
 */
public enum VerbalNoun implements ArabicLetters {

    VERBAL_NOUN_V1(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V2(createVerbalNounRootWord(0, 1, 2, FA_WITH_DAMMA,
            AIN_WITH_DAMMA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V3(createVerbalNounRootWord(0, 1, 2, FA_WITH_DAMMA,
            AIN_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V4(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_FATHA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V5(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_KASRA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V6(createVerbalNounRootWord(0, 1, 2, FA_WITH_DAMMA,
            AIN_WITH_FATHA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V7(createVerbalNounRootWord(0, 1, 2, FA_WITH_KASRA,
            AIN_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V8(createVerbalNounRootWord(0, 1, 2, FA_WITH_KASRA,
            AIN_WITH_FATHA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V9(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V10(createVerbalNounRootWord(0, 1, 2, FA_WITH_KASRA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V11(createVerbalNounRootWord(0, 1, 2, FA_WITH_DAMMA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V12(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_FATHA, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V13(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_KASRA, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V14(createVerbalNounRootWord(0, 1, 3, FA_WITH_FATHA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V15(createVerbalNounRootWord(0, 1, 3, FA_WITH_KASRA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V16(createVerbalNounRootWord(0, 1, 2, FA_WITH_KASRA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, LETTER_ALIF_MAKSURA)),

    VERBAL_NOUN_V17(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, LETTER_ALIF_MAKSURA)),

    VERBAL_NOUN_V18(createVerbalNounRootWord(0, 1, 2, FA_WITH_DAMMA,
            AIN_WITH_SUKUN, LAM_WITH_FATHA, LETTER_ALIF_MAKSURA)),

    VERBAL_NOUN_V19(createVerbalNounRootWord(0, 1, 3, FA_WITH_FATHA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V20(createVerbalNounRootWord(0, 1, 3, FA_WITH_KASRA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V21(createVerbalNounRootWord(0, 1, 3, FA_WITH_DAMMA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V22(createVerbalNounRootWord(0, 1, 3, FA_WITH_DAMMA,
            AIN_WITH_DAMMA, WAW_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V23(createVerbalNounRootWord(0, 1, 3, FA_WITH_FATHA,
            AIN_WITH_DAMMA, WAW_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V24(createVerbalNounRootWord(0, 1, 2, FA_WITH_FATHA,
            AIN_WITH_KASRA, YA_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_V25(createVerbalNounRootWord(0, 1, 3, FA_WITH_FATHA,
            AIN_WITH_DAMMA, WAW_WITH_SUKUN, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_V26(createVerbalNounRootWord(0, 1, 2,
            // TriLiteralVerbalNounV1Builder.class.getName(),
            FA_WITH_DAMMA, AIN_WITH_SUKUN, LAM_WITH_FATHA, LETTER_ALIF, NOON_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_II(createVerbalNounRootWord(1, 2, 4, TA_WITH_FATHA,
            FA_WITH_SUKUN, AIN_WITH_KASRA, YA_WITH_SUKUN, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_II_DEFECTIVE_VERB(createVerbalNounRootWord(1, 2, 3,
            TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_III_V1(createVerbalNounRootWord(0, 1, 3, FA_WITH_KASRA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_III_V2(createVerbalNounRootWord(1, 3, 4, MEEM_WITH_DAMMA,
            FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_III_DEFECTIVE_VERB(createVerbalNounRootWord(1, 3, 4,
            MEEM_WITH_DAMMA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
            LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_IV(createVerbalNounRootWord(1, 2, 4,
            ALIF_HAMZA_BELOW_WITH_KASRA, FA_WITH_SUKUN, AIN_WITH_FATHA,
            LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_V(createVerbalNounRootWord(1, 2, 3, TA_WITH_FATHA,
            FA_WITH_FATHA, AIN_WITH_SHADDA_AND_DAMMA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_VI(createVerbalNounRootWord(1, 3, 4, TA_WITH_FATHA,
            FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_DAMMA, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_VII(createVerbalNounRootWord(2, 3, 5,
            ALIF_HAMZA_BELOW_WITH_KASRA, NOON_WITH_SUKUN, FA_WITH_KASRA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_VIII(createVerbalNounRootWord(1, 3, 5,
            ALIF_HAMZA_BELOW_WITH_KASRA, FA_WITH_SUKUN, TA_WITH_KASRA,
            AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_IX(createVerbalNounRootWord(1, 2, 5,
            ALIF_HAMZA_BELOW_WITH_KASRA, FA_WITH_SUKUN, AIN_WITH_KASRA,
            LAM_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_X(createVerbalNounRootWord(3, 4, 6,
            ALIF_HAMZA_BELOW_WITH_KASRA, SEEN_WITH_SUKUN, TA_WITH_FATHA,
            FA_WITH_SUKUN, AIN_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),

    VERBAL_NOUN_FORM_XI(createVerbalNounRootWord(1, 2, 5,
            ALIF_HAMZA_BELOW_WITH_KASRA, FA_WITH_SUKUN, AIN_WITH_KASRA,
            YA_WITH_SUKUN, LAM_WITH_FATHA, LETTER_ALIF, LAM_WITH_DAMMATAN)),;

    private final RootWord rootWord;

    VerbalNoun(RootWord rootWord) {
        this.rootWord = rootWord;
    }

    public RootWord getRootWord() {
        return rootWord;
    }

}
