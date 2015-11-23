package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.*;

/**
 * @author sali
 */
public class TriLiteralBrokenPluralAdverbBuilder extends TriLiteralAdverbBuilder {

    private RootWord brokenPluralWord;

    @AssistedInject
    public TriLiteralBrokenPluralAdverbBuilder(@Assisted RuleProcessor ruleProcessor,
                                               @Assisted NamedTemplate template,
                                               @Assisted boolean skipRuleProcessing,
                                               @Assisted("firstRadical") ArabicLetterType firstRadical,
                                               @Assisted("secondRadical") ArabicLetterType secondRadical,
                                               @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                               @Nullable @Assisted RootWord baseRootWord) {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord);
        brokenPluralWord = new RootWord(getRootWord());
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
    protected RootWord doNominativePlural(RootWord rootWord) {
        return new RootWord(brokenPluralWord);
    }

    @Override
    protected RootWord doAccusativePlural(RootWord rootWord) {
        RootWord rw = new RootWord(brokenPluralWord);
        ArabicWord word = rw.getRootWord();
        rw.setRootWord(word.replaceDiacritic(word.getLength() - 1, FATHA));
        return rw;
    }
}
