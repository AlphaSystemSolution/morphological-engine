package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactory;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

/**
 * @author sali
 */
public interface FormBuilder {

    /**
     * Returns {@link VerbTransformerFactory}  for <i>past active tense</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>past active tense</i> conjugation
     */
    VerbTransformerFactory pastActiveTenseTransformerFactory();

    /**
     * Returns {@link VerbTransformerFactory}  for <i>present active tense</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>present active tense</i> conjugation
     */
    VerbTransformerFactory presentActiveTenseTransformerFactory();

    /**
     * Returns {@link VerbTransformerFactory}  for <i>past passive tense</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>past passive tense</i> conjugation
     */
    VerbTransformerFactory pastPassiveTenseTransformerFactory();

    /**
     * Returns {@link VerbTransformerFactory}  for <i>present passive tense</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>present passive tense</i> conjugation
     */
    VerbTransformerFactory presentPassiveTenseTransformerFactory();

    /**
     * Returns {@link VerbTransformerFactory}  for <i>imperative</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>imperative</i> conjugation
     */
    VerbTransformerFactory imperativeTransformerFactory();

    /**
     * Returns {@link VerbTransformerFactory}  for <i>forbidding</i> conjugation.
     *
     * @return {@link VerbTransformerFactory}  for <i>forbidding</i> conjugation
     */
    VerbTransformerFactory forbiddingTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>masculine active participle</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>masculine active participle</i> conjugation
     */
    NounTransformerFactory masculineActiveParticipleTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>feminine active participle</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>feminine active participle</i> conjugation
     */
    NounTransformerFactory feminineActiveParticipleTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>masculine passive participle</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>masculine passive participle</i> conjugation
     */
    NounTransformerFactory masculinePassiveParticipleTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>feminine passive participle</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>feminine passive participle</i> conjugation
     */
    NounTransformerFactory femininePassiveParticipleTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>verbal noun</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>verbal noun</i> conjugation
     */
    NounTransformerFactory verbalNounTransformerFactory();

    /**
     * Returns {@link NounTransformerFactory}  for <i>noun of place and time</i> conjugation.
     *
     * @return {@link NounTransformerFactory}  for <i>noun of place and time</i> conjugation
     */
    NounTransformerFactory nounOfPlaceAndTimeTransformerFactory();

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
