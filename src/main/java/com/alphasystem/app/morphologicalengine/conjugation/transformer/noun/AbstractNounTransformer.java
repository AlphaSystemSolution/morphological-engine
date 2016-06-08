package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

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
    protected AbstractNounTransformer(RuleProcessor ruleProcessor) {
        this(ruleProcessor, THIRD_RADICAL_INDEX);
    }

    /**
     * @param variableIndex index of letter which "harkah" needs to be changed or add letters to it
     * @throws NullPointerException if given <code>rootWord</code> is null.
     */
    protected AbstractNounTransformer(RuleProcessor ruleProcessor, int variableIndex) {
        super(ruleProcessor);
        this.variableIndex = variableIndex;
    }

    @Override
    public NounConjugation doTransform(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                       ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final int size = baseWord.getLabel().getLength();
        if (variableIndex >= size) {
            variableIndex = size - 1;
        } else if (variableIndex <= THIRD_RADICAL_INDEX) {
            variableIndex = baseWord.getThirdRadicalIndex();
        }
        return new NounConjugation(doNominative(baseWord), doAccusative(baseWord), doGenitive(baseWord));
    }

    protected abstract RootWord doNominative(RootWord rootWord);

    protected abstract RootWord doAccusative(RootWord rootWord);

    protected abstract RootWord doGenitive(RootWord rootWord);

}
