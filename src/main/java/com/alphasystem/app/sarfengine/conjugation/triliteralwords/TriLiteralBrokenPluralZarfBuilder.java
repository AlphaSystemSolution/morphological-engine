/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class TriLiteralBrokenPluralZarfBuilder extends
        DefaultTriLiteralZarfBuilder {

    private RootWord brokenPluralWord;

    @Override
    public ArabicWord accusativePlural() {
        return doPostAccusativePlural(doAccusativePlural());
    }

    @Override
    protected RootWord createRootWord(SarfMemberType memberType, ArabicWord src) {
        if (NOMINATIVE_PLURAL.equals(memberType)
                || ACCUSATIVE_PLURAL.equals(memberType)
                || GENITIVE_PLURAL.equals(memberType)) {
            return new RootWord(brokenPluralWord).withMemberType(memberType)
                    .withRootWord(src);
        } else {
            return super.createRootWord(memberType, src);
        }
    }

    protected ArabicWord doAccusativePlural() {
        ArabicWord word = new ArabicWord(brokenPluralWord.getRootWord());
        return word.replaceDiacritic(word.getLength() - 1, FATHA);
    }

    protected ArabicWord doNominativePlural() {
        return new ArabicWord(brokenPluralWord.getRootWord());
    }

    protected ArabicWord doPostAccusativePlural(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_PLURAL, src);
    }

    protected ArabicWord doPostNominativePlural(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_PLURAL, src);
    }

    @Override
    protected void initConjugations() {
        super.initConjugations();
        brokenPluralWord = new RootWord(baseRootWord);
        ArabicLetterType firstRadicalLetter = baseRootWord.getFirstRadical()
                .getLetter();
        ArabicLetterType secondRadicalLetter = baseRootWord.getSecondRadical()
                .getLetter();
        ArabicLetterType thirdRadicalLetter = baseRootWord.getThirdRadical()
                .getLetter();
        ArabicWord nominativePluralWord = new ArabicWord(MEEM_WITH_FATHA,
                new ArabicLetter(firstRadicalLetter, FATHA), LETTER_ALIF,
                new ArabicLetter(secondRadicalLetter, KASRA), new ArabicLetter(
                thirdRadicalLetter, DAMMA));
        brokenPluralWord.setRootWord(nominativePluralWord);
        brokenPluralWord.setFirstRadicalIndex(1);
        brokenPluralWord.setSecondRadicalIndex(3);
        brokenPluralWord.setThirdRadicalIndex(4);
    }

    @Override
    public ArabicWord nominativePlural() {
        return doPostNominativePlural(doNominativePlural());
    }

}
