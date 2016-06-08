package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import java.util.List;

/**
 * @author sali
 */
public interface ConjugationBuilder {

    MorphologicalChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                     boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                     ArabicLetterType thirdRadical, ArabicLetterType fourthRadical,
                                     List<VerbalNoun> verbalNouns, List<NounOfPlaceAndTime> adverbs);

    MorphologicalChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                     boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                     ArabicLetterType thirdRadical, List<VerbalNoun> verbalNouns, List<NounOfPlaceAndTime> adverbs);
}
