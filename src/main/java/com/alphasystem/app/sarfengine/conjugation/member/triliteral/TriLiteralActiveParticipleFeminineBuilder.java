package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleFeminineBuilder extends AbstractParticipleMemberBuilder {

    @AssistedInject
    public TriLiteralActiveParticipleFeminineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                     @Assisted boolean skipRuleProcessing,
                                                     @Assisted RootWord baseRootWord) {
        this(ruleProcessor, skipRuleProcessing, baseRootWord, -1);
    }

    protected TriLiteralActiveParticipleFeminineBuilder(RuleProcessor ruleProcessor, boolean skipRuleProcessing,
                                                        RootWord baseRootWord,
                                                        int variableLetterIndex) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord, variableLetterIndex);
    }

    @Override
    protected RootWord doNominativeDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.removeLast().append(TA_WITH_FATHA, LETTER_ALIF, NOON_WITH_KASRA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doNominativePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, FATHA).removeLast().append(LETTER_ALIF, TA_WITH_DAMMATAN);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativeSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.removeLast().append(TA_MARBUTA_WITH_FATHATAN);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativeDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.removeLast().append(TA_WITH_FATHA, YA_WITH_SUKUN, NOON_WITH_KASRA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doAccusativePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.replaceDiacritic(variableLetterIndex, FATHA).removeLast().append(LETTER_ALIF, TA_WITH_KASRATAN);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doGenitiveSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord();
        arabicWord.removeLast().append(TA_MARBUTA_WITH_KASRATAN);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_FEMININE;
    }
}
