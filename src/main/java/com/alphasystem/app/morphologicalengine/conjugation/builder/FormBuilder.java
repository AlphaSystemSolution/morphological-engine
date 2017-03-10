package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

/**
 * @author sali
 */
public interface FormBuilder {

    /**
     * Perform conjugation with given parameters.
     *
     * @param conjugationRoots base root words
     * @param rootLetters      given root letters
     * @return {@link MorphologicalChart}
     */
    MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, RootLetters rootLetters);

    /**
     * Perform conjugation with given parameters.
     *
     * @param conjugationRoots base root words
     * @param firstRadical     <code>firstRadical</code> letter
     * @param secondRadical    <code>secondRadical</code> letter
     * @param thirdRadical     <code>thirdRadical</code> letter
     * @param fourthRadical    <code>fourthRadical</code> letter
     * @return {@link MorphologicalChart}
     */
    MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                     ArabicLetterType fourthRadical);
}
