package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
public class TriLiteralPresentTenseBuilder extends AbstractTenseMemberBuilder {

    protected static final ArabicWord THIRD_PERSON_DUAL_SUFFIX = new ArabicWord(
            LETTER_ALIF, NOON_WITH_KASRA);

    protected static final ArabicWord MASCULINE_PLURAL_SUFFIX = new ArabicWord(
            WAW_WITH_SUKUN, NOON_WITH_FATHA);

    protected static final ArabicWord THIRD_PERSON_FEMININE_PLURAL_SUFFIX = new ArabicWord(
            YA_WITH_SUKUN, NOON_WITH_FATHA);

    protected DiacriticType imperfectSignDiacritic;

    protected ArabicWord thirdPersonFeminineSingular;

    @AssistedInject
    public TriLiteralPresentTenseBuilder(@Assisted RuleProcessor ruleProcessor,
                                         @Assisted NamedTemplate template,
                                         @Assisted boolean skipRuleProcessing,
                                         @Assisted RootWord baseRootWord) {
        super(ruleProcessor, template, skipRuleProcessing, baseRootWord);
        ArabicWord arabicWord = getRootWord().getRootWord();
        this.imperfectSignDiacritic = arabicWord.getFirstLetter().getDiacritics()[0];
        this.thirdPersonFeminineSingular = new ArabicWord(arabicWord).replaceLetter(0, TA);
    }

    @Override
    protected RootWord doThirdPersonMasculineDual(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().replaceDiacritic(rootWord.getThirdRadicalIndex(), FATHA)
                .append(THIRD_PERSON_DUAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonMasculinePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().append(MASCULINE_PLURAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFeminineSingular(RootWord rootWord) {
        rootWord.setRootWord(new ArabicWord(thirdPersonFeminineSingular));
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFeminineDual(RootWord rootWord) {
        ArabicWord arabicWord = new ArabicWord(thirdPersonFeminineSingular).replaceDiacritic(
                rootWord.getThirdRadicalIndex(), FATHA).append(THIRD_PERSON_DUAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doThirdPersonFemininePlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().replaceDiacritic(rootWord.getThirdRadicalIndex(),
                SUKUN).append(NOON_WITH_FATHA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonMasculineSingular(RootWord rootWord) {
        return doThirdPersonFeminineSingular(rootWord);
    }

    @Override
    protected RootWord doSecondPersonMasculineDual(RootWord rootWord) {
        return doThirdPersonFeminineDual(rootWord);
    }

    @Override
    protected RootWord doSecondPersonMasculinePlural(RootWord rootWord) {
        rootWord = doThirdPersonMasculinePlural(rootWord);
        ArabicWord arabicWord = rootWord.getRootWord().replaceLetter(0, TA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonFeminineSingular(RootWord rootWord) {
        ArabicWord arabicWord = new ArabicWord(thirdPersonFeminineSingular)
                .replaceDiacritic(rootWord.getThirdRadicalIndex(), KASRA).append(THIRD_PERSON_FEMININE_PLURAL_SUFFIX);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doSecondPersonFemininePlural(RootWord rootWord) {
        rootWord = doThirdPersonFemininePlural(rootWord);
        ArabicWord arabicWord = rootWord.getRootWord().replaceLetter(0, TA);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doFirstPersonSingular(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().replaceLetter(0, ALIF_HAMZA_ABOVE);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    protected RootWord doFirstPersonPlural(RootWord rootWord) {
        ArabicWord arabicWord = rootWord.getRootWord().replaceLetter(0, NOON);
        rootWord.setRootWord(arabicWord);
        return rootWord;
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_TENSE;
    }
}
