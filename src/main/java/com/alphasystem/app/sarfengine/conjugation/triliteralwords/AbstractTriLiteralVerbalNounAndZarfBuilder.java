/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.app.sarfengine.conjugation.AbstractConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
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
    private ConjugationMember[] masculineConjugations;
    private ConjugationMember[] feminineConjugations;
    private boolean feminineBased;
    private boolean verbalNoun;
    private int variableLetterIndex = -1;
    private boolean postInitCalled;

    public ArabicWord accusativeDual() {
        return doPostAccusativeDual(new ArabicWord(getWord(4)));
    }

    public ArabicWord accusativePlural() {
        return doPostAccusativePlural(new ArabicWord(feminineConjugations[3].getConjugation()));
    }

    public ArabicWord accusativeSigular() {
        return doPostAccusativeSigular(new ArabicWord(getWord(5)));
    }

    @Override
    public ConjugationMember[] doConjugation() {
        ConjugationMember[] words = new ConjugationMember[9];

        words[2] = new ConjugationMember(NOMINATIVE_SINGULAR, nominativeSigular());
        words[1] = new ConjugationMember(NOMINATIVE_DUAL, nominativeDual());
        words[0] = new ConjugationMember(NOMINATIVE_PLURAL, nominativePlural());

        words[5] = new ConjugationMember(ACCUSATIVE_SINGULAR, accusativeSigular());
        words[4] = new ConjugationMember(ACCUSATIVE_DUAL, accusativeDual());
        words[3] = new ConjugationMember(ACCUSATIVE_PLURAL, accusativePlural());

        words[8] = new ConjugationMember(GENITIVE_SINGULAR, genitiveSigular());
        words[7] = new ConjugationMember(GENITIVE_DUAL, genitiveDual());
        words[6] = new ConjugationMember(GENITIVE_PLURAL, genitivePlural());

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
    protected void initDefaultConjugation() {
        if (defaultConjugation == null) {
            defaultConjugation = verbalNoun ? new ConjugationMember(ACCUSATIVE_SINGULAR, accusativeSigular()) :
                    new ConjugationMember(NOMINATIVE_SINGULAR, nominativeSigular());
        }
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
        if (!postInitCalled) {
            postInit();
        }
        return feminineBased ? feminineConjugations[index].getConjugation()
                : masculineConjugations[index].getConjugation();
    }

    protected void initConjugations() {
        if (logger.isDebugEnabled()) {
            logger.debug("Inside initConjugations");
        }
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
        return doPostNominativePlural(new ArabicWord(feminineConjugations[0].getConjugation()));
    }

    public ArabicWord nominativeSigular() {
        return doPostNominativeSigular(new ArabicWord(getWord(2)));
    }

    @Override
    protected void postInit() {
        if (logger.isDebugEnabled()) {
            logger.debug("postInit(): Enter");
        }
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
        postInitCalled = true;
    }

}
