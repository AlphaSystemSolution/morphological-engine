package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.app.sarfengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * Interface to transform given {@link RootWord} into different case.
 *
 * @author sali
 */
public interface NounTransformer {

    /**
     * Transform given <code>rootWord</code> into its singular, dual, and plural forms.
     *
     * @param rootWord      base word
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @return {@link NounConjugationGroup} representing singular, dual, and plural forms of given <code>rootWord</code>
     */
    NounConjugationGroup doTransform(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                     ArabicLetterType thirdRadical, ArabicLetterType fourthRadical);

}
