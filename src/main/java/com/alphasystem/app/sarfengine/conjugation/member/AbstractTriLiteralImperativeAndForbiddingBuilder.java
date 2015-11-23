package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.alphasystem.util.MethodNotSupportedException;

import static com.alphasystem.app.sarfengine.guice.GuiceSupport.getInstance;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public abstract class AbstractTriLiteralImperativeAndForbiddingBuilder
        extends AbstractTenseMemberBuilder {

    protected final TenseMemberBuilder builder;
    protected final boolean forbidding;
    protected ArabicLetter imperativeLetter;

    protected AbstractTriLiteralImperativeAndForbiddingBuilder(RuleProcessor ruleProcessor,
                                                               NamedTemplate template,
                                                               boolean skipRuleProcessing,
                                                               RootWord baseRootWord,
                                                               ArabicLetter imperativeLetter,
                                                               boolean forbidding) {
        super(ruleProcessor, template, skipRuleProcessing, baseRootWord);
        setImperativeLetter(imperativeLetter);
        this.forbidding = forbidding;
        builder = getInstance().getMemberBuilderFactory().getTriLiteralPresentTenseBuilder(ruleProcessor,
                template, skipRuleProcessing, baseRootWord);
    }

    @Override
    public RootWord[] doConjugation() {
        RootWord[] conjugations = new RootWord[6];

        conjugations[2] = secondPersonMasculineSingular();
        conjugations[1] = secondPersonMasculineDual();
        conjugations[0] = secondPersonMasculinePlural();

        conjugations[5] = secondPersonFeminineSingular();
        conjugations[4] = secondPersonFeminineDual();
        conjugations[3] = secondPersonFemininePlural();

        return conjugations;
    }

    @Override
    protected RootWord doSecondPersonMasculineSingular(RootWord rootWord) {
        RootWord rw = new RootWord(builder.secondPersonMasculineSingular()).withSarfTermType(getTermType());
        ArabicWord arabicWord = rw.getRootWord();
        int lastLetterIndex = arabicWord.getLength() - 1;
        ArabicLetter lastLetter = arabicWord.getLetter(lastLetterIndex);
        DiacriticType[] diacritics = lastLetter.getDiacritics();
        if (diacritics != null && !isEmpty(diacritics)) {
            diacritics[diacritics.length - 1] = diacritics[0].equals(SHADDA) ? FATHA : SUKUN;
        }
        arabicWord = arabicWord.replaceDiacritic(lastLetterIndex, diacritics);
        rw.setRootWord(arabicWord);
        return rw;
    }

    @Override
    protected RootWord doSecondPersonMasculineDual(RootWord rootWord) {
        RootWord rw = new RootWord(builder.secondPersonMasculineDual()).withSarfTermType(getTermType());
        rw.setRootWord(rw.getRootWord().removeLast());
        return rw;
    }

    @Override
    protected RootWord doSecondPersonMasculinePlural(RootWord rootWord) {
        RootWord rw = new RootWord(builder.secondPersonMasculinePlural()).withSarfTermType(getTermType());
        rw.setRootWord(rw.getRootWord().removeLast().append(LETTER_ALIF));
        return rw;
    }

    @Override
    protected RootWord doSecondPersonFeminineSingular(RootWord rootWord) {
        RootWord rw = new RootWord(builder.secondPersonFeminineSingular()).withSarfTermType(getTermType());
        rw.setRootWord(rw.getRootWord().removeLast());
        return rw;
    }

    @Override
    protected RootWord doSecondPersonFeminineDual(RootWord rootWord) {
        RootWord rw = new RootWord(builder.secondPersonFeminineDual()).withSarfTermType(getTermType());
        rw.setRootWord(rw.getRootWord().removeLast());
        return rw;
    }

    @Override
    protected RootWord doSecondPersonFemininePlural(RootWord rootWord) {
        return new RootWord(builder.secondPersonFemininePlural()).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doFirstPersonPlural(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doThirdPersonMasculineDual(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doThirdPersonMasculinePlural(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doThirdPersonFeminineSingular(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doThirdPersonFeminineDual(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doThirdPersonFemininePlural(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    protected RootWord doFirstPersonSingular(RootWord rootWord) {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonMasculineSingular() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonMasculineDual() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonMasculinePlural() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonFeminineSingular() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonFeminineDual() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord thirdPersonFemininePlural() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord firstPersonSingular() {
        throw new MethodNotSupportedException();
    }

    @Override
    public RootWord firstPersonPlural() {
        throw new MethodNotSupportedException();
    }

    private void setImperativeLetter(ArabicLetter imperativeLetter) {
        if (imperativeLetter == null) {
            ArabicLetter secondRadical = getRootWord().getSecondRadical();
            DiacriticType[] diacritics = secondRadical.getDiacritics();
            DiacriticType secondRadicalDiacritic = isEmpty(diacritics) ? FATHA : diacritics[0];
            DiacriticType imperativeDiacritic = secondRadicalDiacritic.equals(DAMMA) ? DAMMA : KASRA;
            imperativeLetter = imperativeDiacritic.equals(DAMMA) ? ALIF_HAMZA_ABOVE_WITH_DAMMA
                    : ALIF_HAMZA_BELOW_WITH_KASRA;
            ArabicLetter firstRadical = getRootWord().getFirstRadical();
            diacritics = firstRadical.getDiacritics();
            DiacriticType firstLetterDiacritics = isEmpty(diacritics) ? SUKUN : firstRadical.getDiacritics()[0];
            if (!firstLetterDiacritics.equals(SUKUN) && !firstLetterDiacritics.equals(SHADDA)) {
                imperativeLetter = null;
            }
        }
        this.imperativeLetter = imperativeLetter;
    }

    @Override
    protected RootWord doPostProcessConjugation(RootWord src) {
        RootWord rootWord = processImperative(src);
        return super.doPostProcessConjugation(rootWord);
    }

    protected RootWord processImperative(RootWord rootWord) {
        if (forbidding) {
            return rootWord;
        }
        ArabicWord result = rootWord.getRootWord().remove(0);
        if (imperativeLetter != null) {
            result.preppend(imperativeLetter);
        }
        rootWord.setRootWord(result);
        return rootWord;
    }

    @Override
    public SarfTermType getTermType() {
        return forbidding ? FORBIDDING : IMPERATIVE;
    }
}
