package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import java.util.List;

/**
 * @author sali
 */
public interface ConjugationBuilder {

    SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                            boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical,
                            List<VerbalNoun> verbalNouns, List<NounOfPlaceAndTime> adverbs);

    SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                            boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, List<VerbalNoun> verbalNouns, List<NounOfPlaceAndTime> adverbs);
}
