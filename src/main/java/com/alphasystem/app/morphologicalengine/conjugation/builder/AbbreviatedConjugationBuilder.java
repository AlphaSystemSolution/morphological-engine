package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.GUICE_SUPPORT;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

/**
 * @author sali
 */
public final class AbbreviatedConjugationBuilder {

    private final ConjugationRoots conjugationRoots;
    private final RuleProcessor ruleEngine;

    public AbbreviatedConjugationBuilder(ConjugationRoots conjugationRoots, RuleProcessor ruleEngine) {
        this.conjugationRoots = conjugationRoots;
        this.ruleEngine = ruleEngine;
    }

    public AbbreviatedConjugation createAbbreviatedConjugation(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                                ArabicLetterType thirdRadical, ArabicLetterType fourthRadical,
                                                                boolean removePassiveLine) {
        ActiveLine activeLine = createActiveLine(firstRadical, secondRadical, thirdRadical, fourthRadical);
        PassiveLine passiveLine = removePassiveLine ? null : createPassiveLine(firstRadical, secondRadical, thirdRadical,
                fourthRadical);
        ImperativeAndForbiddingLine imperativeAndForbiddingLine = createImperativeAndForbiddingLine(firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        AdverbLine adverbLine = createAdverbLine(firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new AbbreviatedConjugation(activeLine, passiveLine, imperativeAndForbiddingLine, adverbLine);
    }

    private ActiveLine createActiveLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord rootWord;
        RootWord pastTense = null;
        if (conjugationRoots.pastTense != null) {
            rootWord = new RootWord(conjugationRoots.pastTense.getRoot().getRootWord()).withSarfTermType(PAST_TENSE);
            pastTense = getTenseRootWord(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, rootWord, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        RootWord presentTense = null;
        if (conjugationRoots.presentTense != null) {
            rootWord = new RootWord(conjugationRoots.presentTense.getRoot().getRootWord()).withSarfTermType(PRESENT_TENSE);
            presentTense = getTenseRootWord(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, rootWord, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        RootWord activeParticipleMasculine = getNounRootWord(conjugationRoots.activeParticipleMasculine,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, true, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        return new ActiveLine(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

    private PassiveLine createPassiveLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                          ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord rootWord;
        RootWord pastPassiveTense = null;
        if (conjugationRoots.pastPassiveTense != null) {
            rootWord = new RootWord(conjugationRoots.pastPassiveTense.getRoot().getRootWord()).withSarfTermType(PAST_PASSIVE_TENSE);
            pastPassiveTense = getTenseRootWord(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, rootWord, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        RootWord presentPassiveTense = null;
        if (conjugationRoots.presentPassiveTense != null) {
            rootWord = new RootWord(conjugationRoots.presentPassiveTense.getRoot().getRootWord()).withSarfTermType(PRESENT_PASSIVE_TENSE);
            presentPassiveTense = getTenseRootWord(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, rootWord, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        RootWord passiveParticipleMasculine = getNounRootWord(conjugationRoots.passiveParticipleMasculine, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, true, firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new PassiveLine(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine, verbalNouns);
    }

    private ImperativeAndForbiddingLine createImperativeAndForbiddingLine(ArabicLetterType firstRadical,
                                                                          ArabicLetterType secondRadical,
                                                                          ArabicLetterType thirdRadical,
                                                                          ArabicLetterType fourthRadical) {
        RootWord rootWord;
        RootWord imperative = null;
        if (conjugationRoots.imperative != null) {
            rootWord = new RootWord(conjugationRoots.imperative.getRoot().getRootWord()).withSarfTermType(IMPERATIVE);
            final String name = conjugationRoots.template.equals(FORM_IV_TEMPLATE) ? FORM_IV_IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER :
                    IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER;
            imperative = getTenseRootWord(name, rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        }

        RootWord forbidding = null;
        if (conjugationRoots.forbidding != null) {
            rootWord = new RootWord(conjugationRoots.forbidding.getRoot().getRootWord()).withSarfTermType(FORBIDDING);
            forbidding = getTenseRootWord(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER, rootWord, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        return new ImperativeAndForbiddingLine(imperative, forbidding);
    }

    private AdverbLine createAdverbLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new AdverbLine(getNounRootWords(conjugationRoots.adverbs, false, firstRadical, secondRadical, thirdRadical, fourthRadical));
    }

    private RootWord getTenseRootWord(String name, RootWord rootWord, ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                      ArabicLetterType fourthRadical) {
        final VerbTransformer verbTransformer = GUICE_SUPPORT.getVerbTransformer(name);
        final ConjugationTuple tuple = verbTransformer.doTransform(ruleEngine, rootWord,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        return tuple.getSingular();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, boolean verbalNoun, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                     ArabicLetterType fourthRadical) {
        final NounSupport singularBaseWord = rootBase.getSingularBaseWord();
        final NounTransformer transformer = GUICE_SUPPORT.getNounTransformer(singularBaseWord.getSingularRootName());
        final NounConjugation conjugation = transformer.doTransform(ruleEngine, singularBaseWord.getRootWord(),
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        return verbalNoun ? conjugation.getAccusative() : conjugation.getNominative();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                     ArabicLetterType fourthRadical) {
        return getNounRootWord(rootBase, false, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    private RootWord[] getNounRootWords(NounRootBase[] rootBases, boolean verbalNoun, ArabicLetterType firstRadical,
                                        ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                        ArabicLetterType fourthRadical) {
        RootWord[] rootWords = null;
        if (isNotEmpty(rootBases)) {
            rootWords = new RootWord[rootBases.length];
            for (int i = 0; i < rootBases.length; i++) {
                rootWords[i] = getNounRootWord(rootBases[i], verbalNoun, firstRadical, secondRadical, thirdRadical, fourthRadical);
            }
        }
        return rootWords;
    }
}
