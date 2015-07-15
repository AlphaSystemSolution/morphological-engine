/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.app.sarfengine.conjugation.AbstractConjugationMemberBuilder;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public abstract class AbstractTriLiteralImperativeAndForbiddingBuilder<P extends TriLiteralPresentTenseBuilder>
        extends AbstractConjugationMemberBuilder {

    protected Class<P> presentTenseBuilderClass;
    protected P builder;
    protected ArabicWord[] conjugations;
    protected boolean forbidding;
    protected ArabicLetter imperativeLetter;

    @Override
    public ArabicWord[] doConjugation() {
        ArabicWord[] words = new ArabicWord[6];

        words[2] = secondPersonMasculineSingular();
        words[1] = secondPersonMasculineDual();
        words[0] = secondPersonMasculinePlural();

        words[5] = secondPersonFeminineSingular();
        words[4] = secondPersonFeminineDual();
        words[3] = secondPersonFemininePlural();

        return words;
    }

    protected ArabicWord doPostecondPersonMasculinePlural(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_PLURAL, src);
    }

    @Override
    protected ArabicWord doPostProcessConjugation(SarfMemberType memberType,
                                                  ArabicWord src) {
        ArabicWord result = new ArabicWord(src);
        RootWord baseRootWord = createRootWord(memberType, result);
        result = processImperative(result, baseRootWord.getBaseWord());
        result = super.doPostProcessConjugation(memberType, result);
        return result;
    }

    protected ArabicWord doPostSecondPersonFeminineDual(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_DUAL, src);
    }

    protected ArabicWord doPostSecondPersonFemininePlural(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_PLURAL, src);
    }

    protected ArabicWord doPostSecondPersonFeminineSingular(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_SINGULAR, src);
    }

    protected ArabicWord doPostSecondPersonMasculineDual(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_DUAL, src);
    }

    protected ArabicWord doPostSecondPersonMasculineSingular(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_SINGULAR, src);
    }

    protected ArabicWord doSecondPersonFeminineDual() {
        return new ArabicWord(doSecondPersonMasculineDual());
    }

    protected ArabicWord doSecondPersonFemininePlural() {
        return new ArabicWord(conjugations[9]);
    }

    protected ArabicWord doSecondPersonFeminineSingular() {
        return new ArabicWord(conjugations[11]).removeLast();
    }

    protected ArabicWord doSecondPersonMasculineDual() {
        return new ArabicWord(conjugations[7]).removeLast();
    }

    protected ArabicWord doSecondPersonMasculinePlural() {
        return new ArabicWord(conjugations[6]).removeLast().append(LETTER_ALIF);
    }

    protected ArabicWord doSecondPersonMasculineSingular() {
        ArabicWord arabicWord = new ArabicWord(conjugations[8]);
        int lastLetterIndex = arabicWord.getLength() - 1;
        ArabicLetter lastLetter = arabicWord.getLetter(lastLetterIndex);
        DiacriticType[] diacritics = lastLetter.getDiacritics();
        if (diacritics != null && !isEmpty(diacritics)) {
            diacritics[diacritics.length - 1] = diacritics[0].equals(SHADDA) ? FATHA
                    : SUKUN;
        }
        arabicWord = arabicWord.replaceDiacritic(lastLetterIndex, diacritics);
        return arabicWord;
    }

    @Override
    public ArabicWord getDefaultConjugation() {
        return secondPersonMasculineSingular();
    }

    @Override
    public SarfMemberType getDefaultMember() {
        return SECOND_PERSON_MASCULINE_SINGULAR;
    }

    public ArabicLetter getImperativeLetter() {
        return imperativeLetter;
    }

    public void setImperativeLetter(ArabicLetter imperativeLetter) {
        this.imperativeLetter = imperativeLetter;
    }

    @Override
    public SarfTermType getTermType() {
        return forbidding ? FORBIDDING : IMPERATIVE;
    }

    private void initConjugations() {
        try {
            conjugations = builder.doConjugation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void initGenericClass() {
        Class klass = getClass();
        while (true) {
            Type type = klass.getGenericSuperclass();
            if (type == null) {
                throw new RuntimeException("Could not find generic type: "
                        + getClass().getName());
            }
            if (type instanceof ParameterizedType) {
                final Type[] types = ((ParameterizedType) type)
                        .getActualTypeArguments();
                presentTenseBuilderClass = (Class<P>) types[0];
                break;
            }
            klass = klass.getSuperclass();
            if (klass == null) {
                throw new RuntimeException("Could not find generic type: "
                        + getClass().getName());
            }
        }
    }

    protected void initPresentTenseBuilder(RootWord rootWord) {
        RootWord masculineCopy = new RootWord(rootWord)
                .withImplementationClass(presentTenseBuilderClass.getName())
                .withSarfTermType(PRESENT_TENSE);
        try {
            builder = presentTenseBuilderClass.newInstance();
            builder.setSkipRuleProcessing(isSkipRuleProcessing());
            builder.setTemplate(template);
            builder.setRootWord(masculineCopy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isForbidding() {
        return forbidding;
    }

    public void setForbidding(boolean forbidding) {
        this.forbidding = forbidding;
    }

    @Override
    protected void postInit() {
        initGenericClass();
        initPresentTenseBuilder(baseRootWord);
        initConjugations();
        super.postInit();
    }

    private ArabicWord processImperative(ArabicWord src, ArabicWord base) {
        if (forbidding) {
            return src;
        }
        ArabicWord result = new ArabicWord(src);
        result.remove(0);
        ArabicLetter imperativeLetter = this.imperativeLetter;
        if (imperativeLetter == null) {
            ArabicLetter secondRadical = base.getLetter(secondRadicalIndex);
            DiacriticType[] diacritics = secondRadical.getDiacritics();
            DiacriticType secondRadicalDiacritic = isEmpty(diacritics) ? FATHA
                    : diacritics[0];
            DiacriticType imperativeDiacritic = secondRadicalDiacritic
                    .equals(DAMMA) ? DAMMA : KASRA;
            imperativeLetter = imperativeDiacritic.equals(DAMMA) ? ALIF_HAMZA_ABOVE_WITH_DAMMA
                    : ALIF_HAMZA_BELOW_WITH_KASRA;
            DiacriticType firstLetterDiactric = result.getLetter(0)
                    .getDiacritics()[0];
            if (!firstLetterDiactric.equals(SUKUN)
                    && !firstLetterDiactric.equals(SHADDA)) {
                imperativeLetter = null;
            }
        }
        if (imperativeLetter != null) {
            result.preppend(imperativeLetter);
        }
        return result;
    }

    public ArabicWord secondPersonFeminineDual() {
        return doPostSecondPersonFeminineDual(doSecondPersonFeminineDual());
    }

    public ArabicWord secondPersonFemininePlural() {
        return doPostSecondPersonFemininePlural(doSecondPersonFemininePlural());
    }

    public ArabicWord secondPersonFeminineSingular() {
        return doPostSecondPersonFeminineSingular(doSecondPersonFeminineSingular());
    }

    public ArabicWord secondPersonMasculineDual() {
        return doPostSecondPersonMasculineDual(doSecondPersonMasculineDual());
    }

    public ArabicWord secondPersonMasculinePlural() {
        return doPostecondPersonMasculinePlural(doSecondPersonMasculinePlural());
    }

    public ArabicWord secondPersonMasculineSingular() {
        return doPostSecondPersonMasculineSingular(doSecondPersonMasculineSingular());
    }

}
