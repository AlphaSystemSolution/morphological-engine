package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleFeminineBuilder extends AbstractParticipleMemberBuilder {

    protected TriLiteralActiveParticipleFeminineBuilder(RuleProcessor ruleProcessor, NamedTemplate template, boolean skipRuleProcessing,
                                                        ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                        ArabicLetterType thirdRadical, RootWord baseRootWord,
                                                        int variableLetterIndex) throws NullPointerException {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord, variableLetterIndex);
    }

    @AssistedInject
    public TriLiteralActiveParticipleFeminineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                     @Assisted NamedTemplate template,
                                                     @Assisted boolean skipRuleProcessing,
                                                     @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                     @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                     @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                     @Nullable @Assisted RootWord baseRootWord) {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord, -1);
    }

    @AssistedInject
    public TriLiteralActiveParticipleFeminineBuilder(@Assisted RuleProcessor ruleProcessor,
                                                     @Assisted NamedTemplate template,
                                                     @Assisted boolean skipRuleProcessing,
                                                     @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                     @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                     @Assisted("thirdRadical") ArabicLetterType thirdRadical) throws NullPointerException {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null);
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
