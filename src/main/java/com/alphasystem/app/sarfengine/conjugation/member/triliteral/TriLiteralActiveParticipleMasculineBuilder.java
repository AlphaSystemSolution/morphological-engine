package com.alphasystem.app.sarfengine.conjugation.member.triliteral;


import com.alphasystem.app.sarfengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleMasculineBuilder extends AbstractParticipleMemberBuilder {

    @AssistedInject
    public TriLiteralActiveParticipleMasculineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted RootWord baseRootWord) {
        this(ruleProcessor, skipRuleProcessing, baseRootWord, -1);
    }

    protected TriLiteralActiveParticipleMasculineBuilder(RuleProcessor ruleProcessor, boolean skipRuleProcessing,
                                                         RootWord baseRootWord,
                                                         int variableLetterIndex) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord, variableLetterIndex);
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
