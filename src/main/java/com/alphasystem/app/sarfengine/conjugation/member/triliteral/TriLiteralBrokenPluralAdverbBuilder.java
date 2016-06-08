package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.*;

/**
 * @author sali
 */
public class TriLiteralBrokenPluralAdverbBuilder extends TriLiteralAdverbBuilder {

    private RootWord brokenPluralWord;

    @AssistedInject
    public TriLiteralBrokenPluralAdverbBuilder(@Assisted RuleProcessor ruleProcessor,
                                               @Assisted boolean skipRuleProcessing,
                                               @Assisted RootWord baseRootWord) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord);
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
