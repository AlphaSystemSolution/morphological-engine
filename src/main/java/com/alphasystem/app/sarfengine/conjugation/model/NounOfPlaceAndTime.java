/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.app.sarfengine.util.TriLiteralTemplateHelper.createZarfRootWord;

/**
 * @author sali
 */
public enum NounOfPlaceAndTime implements ArabicLetters {

    NOUN_OF_PLACE_AND_TIME_V1(createZarfRootWord(1, 2, 3,
            //TriLiteralBrokenPluralZarfBuilder.class.getName(),
            MEEM_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMATAN)),

    NOUN_OF_PLACE_AND_TIME_V2(createZarfRootWord(1, 2, 3,
            //TriLiteralBrokenPluralZarfBuilder.class.getName(),
            MEEM_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_DAMMATAN)),

    NOUN_OF_PLACE_AND_TIME_V3(createZarfRootWord(1, 2, 3,
            //TriLiteralBrokenPluralZarfBuilder.class.getName(),
            MEEM_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_FATHA,
            TA_MARBUTA_WITH_DAMMATAN));

    private static RootWord[] rootWords;

    static {
        NounOfPlaceAndTime[] values = values();
        rootWords = new RootWord[values.length];
        for (int i = 0; i < values.length; i++) {
            rootWords[i] = values[i].getRootWord();
        }
    }

    private final RootWord rootWord;

    NounOfPlaceAndTime(RootWord rootWord) {
        this.rootWord = rootWord;
    }

    public static RootWord[] getRootWords() {
        return rootWords;
    }

    public RootWord getRootWord() {
        return rootWord;
    }
}
