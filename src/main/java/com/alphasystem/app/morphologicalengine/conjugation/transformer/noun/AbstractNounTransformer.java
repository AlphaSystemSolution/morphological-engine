package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalengine.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author sali
 */
public abstract class AbstractNounTransformer extends AbstractTransformer<NounConjugation> implements NounTransformer {

    public static final int THIRD_RADICAL_INDEX = -1;
    public static final int LAST_LETTER = MAX_VALUE;

    protected int variableIndex;

    /**
     * @throws NullPointerException if given <code>rootWord</code> is null.
     */
    protected AbstractNounTransformer() {
        this(THIRD_RADICAL_INDEX);
    }

    /**
     * @param variableIndex index of letter which "harkah" needs to be changed or add letters to it
     * @throws NullPointerException if given <code>rootWord</code> is null.
     */
    protected AbstractNounTransformer(int variableIndex) {
        super();
        this.variableIndex = variableIndex;
    }

    @Override
    public NounConjugation doTransform(RuleProcessor ruleProcessor, RootWord rootWord, SarfTermType sarfTermType,
                                       ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                       ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final int size = baseWord.toLabel().getLength();
        if (variableIndex >= size) {
            variableIndex = size - 1;
        } else if (variableIndex <= THIRD_RADICAL_INDEX) {
            variableIndex = baseWord.getThirdRadicalIndex();
        }
        final RootWord nominative = processRules(ruleProcessor, doNominative(baseWord));
        final RootWord accusative = processRules(ruleProcessor, doAccusative(baseWord));
        final RootWord genitive = processRules(ruleProcessor, doGenitive(baseWord));
        return new NounConjugation(nominative, accusative, genitive);
    }

    protected abstract RootWord doNominative(RootWord rootWord);

    protected abstract RootWord doAccusative(RootWord rootWord);

    protected abstract RootWord doGenitive(RootWord rootWord);

}
