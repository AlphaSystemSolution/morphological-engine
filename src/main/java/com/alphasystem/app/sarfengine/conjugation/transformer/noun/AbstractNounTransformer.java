package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

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

    protected final int variableIndex;
    protected final RootWord rootWord;

    /**
     * @param rootWord
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @throws NullPointerException if given <code>rootWord</code> is null.
     */
    protected AbstractNounTransformer(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                      ArabicLetterType fourthRadical) {
        this(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical, THIRD_RADICAL_INDEX);
    }

    /**
     * @param rootWord
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @param variableIndex index of letter which "harkah" needs to be changed or add letters to it
     * @throws NullPointerException if given <code>rootWord</code> is null.
     */
    public AbstractNounTransformer(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                   ArabicLetterType fourthRadical, int variableIndex) {
        if (rootWord == null) {
            throw new NullPointerException("source can not be null.");
        }
        this.rootWord = new RootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final int size = this.rootWord.getLabel().getLength();
        if (variableIndex >= size) {
            this.variableIndex = size - 1;
        } else if (variableIndex <= THIRD_RADICAL_INDEX) {
            this.variableIndex = this.rootWord.getThirdRadicalIndex();
        } else {
            this.variableIndex = variableIndex;
        }
    }

    protected static RootWord copyRootWord(RootWord rootWord, SarfMemberType memberType) {
        return new RootWord(rootWord).withMemberType(memberType);
    }

}
