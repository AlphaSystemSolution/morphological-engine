package com.alphasystem.app.sarfengine.conjugation.member.triliteral;


import com.alphasystem.app.sarfengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleMasculineBuilder extends AbstractParticipleMemberBuilder {

    @AssistedInject
    public TriLiteralActiveParticipleMasculineBuilder(@Assisted NamedTemplate template,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical) {
        super(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, -1);
    }

    @Override
    protected RootWord doNominativeDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doNominativePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, DAMMA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativeSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, FATHATAN).append(LETTER_ALIF);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativeDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, KASRA).append(YA_WITH_SUKUN, NOON_WITH_FATHA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doGenitiveSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, KASRATAN);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_MASCULINE;
    }
}
