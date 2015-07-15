/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.app.sarfengine.conjugation.AbstractConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.alphasystem.arabic.model.ArabicLetterType.TA_MARBUTA;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.NOUN_OF_PLACE_AND_TIME;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public abstract class AbstractTriLiteralVerbalNounAndZarfBuilder<M extends TriLiteralActiveParticipleMasculineBuilder, F extends TriLiteralActiveParticipleFeminineBuilder>
        extends AbstractConjugationMemberBuilder {

    private Class<M> masculineBuilderClass;
    private Class<F> feminineBuilderClass;
    private M masculineBuilder;
    private F feminineBuilder;
    private ArabicWord[] masculineConjugations;
    private ArabicWord[] feminineConjugations;
    private boolean feminineBased;
    private boolean verbalNoun;
    private int variableLetterIndex = -1;

    public ArabicWord accusativeDual() {
        return doPostAccusativeDual(new ArabicWord(getWord(4)));
    }

    public ArabicWord accusativePlural() {
        return doPostAccusativePlural(new ArabicWord(feminineConjugations[3]));
    }

    public ArabicWord accusativeSigular() {
        return doPostAccusativeSigular(new ArabicWord(getWord(5)));
    }

    @Override
    public ArabicWord[] doConjugation() {
        ArabicWord[] words = new ArabicWord[9];

        words[2] = nominativeSigular();
        words[1] = nominativeDual();
        words[0] = nominativePlural();

        words[5] = accusativeSigular();
        words[4] = accusativeDual();
        words[3] = accusativePlural();

        words[8] = genitiveSigular();
        words[7] = genitiveDual();
        words[6] = genitivePlural();

        return words;
    }

    protected ArabicWord doPostAccusativeDual(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_DUAL, src);
    }

    protected ArabicWord doPostAccusativePlural(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_PLURAL, src);
    }

    protected ArabicWord doPostAccusativeSigular(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_SINGULAR, src);
    }

    protected ArabicWord doPostGenitiveDual(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_DUAL, src);
    }

    protected ArabicWord doPostGenitivePlural(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_PLURAL, src);
    }

    protected ArabicWord doPostGenitiveSigular(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_SINGULAR, src);
    }

    protected ArabicWord doPostNominativeDual(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_DUAL, src);
    }

    protected ArabicWord doPostNominativePlural(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_PLURAL, src);
    }

    protected ArabicWord doPostNominativeSigular(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_SINGULAR, src);
    }

    public ArabicWord genitiveDual() {
        return doPostGenitiveDual(new ArabicWord(accusativeDual()));
    }

    public ArabicWord genitivePlural() {
        return doPostGenitivePlural(new ArabicWord(accusativePlural()));
    }

    public ArabicWord genitiveSigular() {
        return doPostGenitiveSigular(new ArabicWord(getWord(8)));
    }

    @Override
    public ArabicWord getDefaultConjugation() {
        return verbalNoun ? accusativeSigular() : nominativeSigular();
    }

    @Override
    public SarfMemberType getDefaultMember() {
        return verbalNoun ? ACCUSATIVE_SINGULAR : NOMINATIVE_SINGULAR;
    }

    @Override
    public SarfTermType getTermType() {
        return verbalNoun ? VERBAL_NOUN : NOUN_OF_PLACE_AND_TIME;
    }

    public int getVariableLetterIndex() {
        if (variableLetterIndex <= -1) {
            setVariableLetterIndex(-1);
        }
        return variableLetterIndex;
    }

    public void setVariableLetterIndex(int variableLetterIndex) {
        this.variableLetterIndex = variableLetterIndex <= -1 ? getThirdRadicalIndex()
                : variableLetterIndex;
    }

    private ArabicWord getWord(int index) {
        return feminineBased ? feminineConjugations[index]
                : masculineConjugations[index];
    }

    protected void initConjugations() {
        String implementationClass = feminineBased ? feminineBuilderClass
                .getName() : masculineBuilderClass.getName();
        RootWord masculineCopy = new RootWord(baseRootWord)
                .withImplementationClass(implementationClass).withSarfTermType(
                        getTermType());
        RootWord feminineCopy = feminineBased ? masculineCopy : new RootWord(
                baseRootWord).withImplementationClass(
                feminineBuilderClass.getName()).withSarfTermType(getTermType());
        ArabicWord arabicWord = feminineBased ? feminineCopy.getRootWord()
                : feminineCopy.getRootWord()
                .replaceDiacritic(getVariableLetterIndex(), FATHA)
                .append(TA_MARBUTA_WITH_DAMMATAN);
        feminineCopy.setRootWord(arabicWord);
        try {
            masculineBuilder = masculineBuilderClass.newInstance();
            masculineBuilder.setTemplate(template);
            masculineBuilder.setSkipRuleProcessing(skipRuleProcessing);
            masculineBuilder.setVariableLetterIndex(variableLetterIndex);
            masculineBuilder.setRootWord(masculineCopy);

            feminineBuilder = feminineBuilderClass.newInstance();
            feminineBuilder.setTemplate(template);
            feminineBuilder.setSkipRuleProcessing(skipRuleProcessing);
            feminineBuilder.setVariableLetterIndex(variableLetterIndex);
            feminineBuilder.setRootWord(feminineCopy);

            masculineConjugations = masculineBuilder.doConjugation();
            feminineConjugations = feminineBuilder.doConjugation();
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
                masculineBuilderClass = (Class<M>) types[0];
                feminineBuilderClass = (Class<F>) types[1];
                break;
            }
            klass = klass.getSuperclass();
            if (klass == null) {
                throw new RuntimeException("Could not find generic type: "
                        + getClass().getName());
            }
        }
    }

    public boolean isFeminineBased() {
        return feminineBased;
    }

    public void setFeminineBased(boolean feminineBased) {
        this.feminineBased = feminineBased;
    }

    public boolean isVerbalNoun() {
        return verbalNoun;
    }

    public void setVerbalNoun(boolean verbalNoun) {
        this.verbalNoun = verbalNoun;
    }

    public ArabicWord nominativeDual() {
        return doPostNominativeDual(new ArabicWord(getWord(1)));
    }

    public ArabicWord nominativePlural() {
        return doPostNominativePlural(new ArabicWord(feminineConjugations[0]));
    }

    public ArabicWord nominativeSigular() {
        return doPostNominativeSigular(new ArabicWord(getWord(2)));
    }

    @Override
    protected void postInit() {
        getVariableLetterIndex();
        initGenericClass();
        feminineBased = this.rootWord.getLastLetter().getLetter()
                .equals(TA_MARBUTA);
        initConjugations();
        super.postInit();

        boolean pastTenseHasTransformed = getRuleProcessor()
                .isPastTenseHasTransformed();
        DiacriticType diacriticForWeakSecondRadicalWaw = getRuleProcessor()
                .getDiacriticForWeakSecondRadicalWaw();
        RuleProcessor rp = masculineBuilder.getRuleProcessor();
        rp.setPastTenseHasTransformed(pastTenseHasTransformed);
        rp.setDiacriticForWeakSecondRadicalWaw(diacriticForWeakSecondRadicalWaw);

        rp = feminineBuilder.getRuleProcessor();
        rp.setPastTenseHasTransformed(pastTenseHasTransformed);
        rp.setDiacriticForWeakSecondRadicalWaw(diacriticForWeakSecondRadicalWaw);
    }

}
