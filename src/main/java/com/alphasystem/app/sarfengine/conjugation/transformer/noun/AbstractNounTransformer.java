package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.app.sarfengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author sali
 */
public abstract class AbstractNounTransformer implements NounTransformer, ArabicLetters {

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
    public AbstractNounTransformer(int variableIndex) {
        this.variableIndex = variableIndex;
    }

    @Override
    public NounConjugationGroup doTransform(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = new RootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final int size = baseWord.getLabel().getLength();
        if (variableIndex >= size) {
            variableIndex = size - 1;
        } else if (variableIndex <= THIRD_RADICAL_INDEX) {
            variableIndex = baseWord.getThirdRadicalIndex();
        }
        return new NounConjugationGroup(doNominative(baseWord), doAccusative(baseWord), doGenitive(baseWord));
    }

    protected abstract RootWord doNominative(RootWord rootWord);

    protected abstract RootWord doAccusative(RootWord rootWord);

    protected abstract RootWord doGenitive(RootWord rootWord);

    protected static RootWord copyRootWord(RootWord rootWord, SarfMemberType memberType) {
        return new RootWord(rootWord).withMemberType(memberType);
    }

}
