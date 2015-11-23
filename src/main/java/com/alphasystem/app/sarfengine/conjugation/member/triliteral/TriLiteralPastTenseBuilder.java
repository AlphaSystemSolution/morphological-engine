package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PAST_TENSE;

/**
 * @author sali
 */
public class TriLiteralPastTenseBuilder extends AbstractTenseMemberBuilder {

    protected static final ArabicWord FIRST_PERSON_PLURAL_SUFFIX = new ArabicWord(
            NOON_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord SECOND_PERSON_DUAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord SECOND_PERSON_MASULINE_PLURAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, MEEM_WITH_SUKUN);

    protected static final ArabicWord SECOND_PERSON_FEMININE_PLURAL_SUFFIX = new ArabicWord(
            TA_WITH_DAMMA, NOON_WITH_SHADDA_AND_FATHA);

    protected static final ArabicWord THIRD_PERSON_MASCULINE_PLURAL_SUFFIX = new ArabicWord(
            WAW_WITH_SUKUN, LETTER_ALIF);

    protected static final ArabicWord THIRD_PERSON_FEMININE_DUAL_SUFFIX = new ArabicWord(
            TA_WITH_FATHA, LETTER_ALIF);

    protected static final ArabicWord THIRD_PERSON_FEMININE_SINGULAR_SUFFIX = new ArabicWord(
            TA_WITH_SUKUN);

    /**
     * This is the suffix for Third person feminine plural, second person
     * masculine and feminine, and first person.
     */
    protected ArabicWord prefixForSecondAndFirstPerson;

    @AssistedInject
    public TriLiteralPastTenseBuilder(@Assisted RuleProcessor ruleProcessor,
                                      @Assisted NamedTemplate template,
                                      @Assisted boolean skipRuleProcessing,
                                      @Assisted RootWord baseRootWord) {
        super(ruleProcessor, template, skipRuleProcessing, baseRootWord);
        initPrefixForSecondAndFirstPerson();
    }

    private void initPrefixForSecondAndFirstPerson() {
        RootWord rootWord = getRootWord();
        ArabicWord arabicWord = rootWord.getRootWord();
        int thirdRadicalIndex = rootWord.getThirdRadicalIndex();
        prefixForSecondAndFirstPerson = new ArabicWord(arabicWord).replaceDiacritic(
                thirdRadicalIndex, SUKUN);
    }

    @Override
    protected RootWord doThirdPersonMasculineDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().append(LETTER_ALIF);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonMasculinePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().replaceDiacritic(rootWord.getThirdRadicalIndex(), DAMMA)
                .append(THIRD_PERSON_MASCULINE_PLURAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFeminineSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().append(THIRD_PERSON_FEMININE_SINGULAR_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFeminineDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().append(THIRD_PERSON_FEMININE_DUAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFemininePlural(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson).append(NOON_WITH_FATHA));
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonMasculineSingular(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson).append(TA_WITH_FATHA));
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonMasculineDual(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson).append(SECOND_PERSON_DUAL_SUFFIX));
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonMasculinePlural(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson)
                .append(SECOND_PERSON_MASULINE_PLURAL_SUFFIX));
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonFeminineSingular(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson)
                .append(TA_WITH_KASRA));
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonFemininePlural(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson)
                .append(SECOND_PERSON_FEMININE_PLURAL_SUFFIX));
        return rootWord;
    }

    @Override
    protected RootWord doFirstPersonSingular(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson)
                .append(TA_WITH_DAMMA));
        return rootWord;
    }

    @Override
    protected RootWord doFirstPersonPlural(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(prefixForSecondAndFirstPerson).append(FIRST_PERSON_PLURAL_SUFFIX));
        return rootWord;
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_TENSE;
    }
}
