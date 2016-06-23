package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
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
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.GUICE_SUPPORT;
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
        RootWord pastTense = getTenseRootWord(conjugationRoots.pastTense, PAST_TENSE, firstRadical, secondRadical,
                thirdRadical, fourthRadical);

        RootWord presentTense = getTenseRootWord(conjugationRoots.presentTense, PRESENT_TENSE, firstRadical, secondRadical,
                thirdRadical, fourthRadical);

        RootWord activeParticipleMasculine = getNounRootWord(conjugationRoots.activeParticipleMasculine, ACTIVE_PARTICIPLE_MASCULINE,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, VERBAL_NOUN, true, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        return new ActiveLine(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

    private PassiveLine createPassiveLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                          ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord pastPassiveTense = getTenseRootWord(conjugationRoots.pastPassiveTense, PAST_PASSIVE_TENSE, firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        RootWord presentPassiveTense = getTenseRootWord(conjugationRoots.presentPassiveTense, PRESENT_PASSIVE_TENSE, firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        RootWord passiveParticipleMasculine = getNounRootWord(conjugationRoots.passiveParticipleMasculine,
                PASSIVE_PARTICIPLE_MASCULINE, firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, VERBAL_NOUN, true, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        return new PassiveLine(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine, verbalNouns);
    }

    private ImperativeAndForbiddingLine createImperativeAndForbiddingLine(ArabicLetterType firstRadical,
                                                                          ArabicLetterType secondRadical,
                                                                          ArabicLetterType thirdRadical,
                                                                          ArabicLetterType fourthRadical) {
        RootWord imperative = getTenseRootWord(conjugationRoots.imperative, IMPERATIVE, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        RootWord forbidding = getTenseRootWord(conjugationRoots.forbidding, FORBIDDING, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        return new ImperativeAndForbiddingLine(imperative, forbidding);
    }

    private AdverbLine createAdverbLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new AdverbLine(getNounRootWords(conjugationRoots.adverbs, NOUN_OF_PLACE_AND_TIME, false, firstRadical,
                secondRadical, thirdRadical, fourthRadical));
    }

    private RootWord getTenseRootWord(VerbRootBase verbRootBase, SarfTermType termType,
                                      ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (verbRootBase == null) {
            return null;
        }
        final Verb verb = verbRootBase.getRoot();
        boolean imperativeOrForbidden = IMPERATIVE.equals(termType) || FORBIDDING.equals(termType);
        String name = imperativeOrForbidden ? verb.getSecondPersonMasculineName() : verb.getThirdPersonMasculineName();
        final VerbTransformer verbTransformer = GUICE_SUPPORT.getVerbTransformer(name);
        final RootWord rootWord = new RootWord(verb.getRootWord()).withSarfTermType(termType);
        final ConjugationTuple tuple = verbTransformer.doTransform(ruleEngine, rootWord, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        return tuple.getSingular();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, SarfTermType termType, boolean verbalNoun,
                                     ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                     ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        final NounSupport singularBaseWord = rootBase.getSingularBaseWord();
        final NounTransformer transformer = GUICE_SUPPORT.getNounTransformer(singularBaseWord.getSingularRootName());
        final RootWord rootWord = new RootWord(singularBaseWord.getRootWord()).withSarfTermType(termType);
        final NounConjugation conjugation = transformer.doTransform(ruleEngine, rootWord,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        return verbalNoun ? conjugation.getAccusative() : conjugation.getNominative();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, SarfTermType termType, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                     ArabicLetterType fourthRadical) {
        return getNounRootWord(rootBase, termType, false, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    private RootWord[] getNounRootWords(NounRootBase[] rootBases, SarfTermType termType, boolean verbalNoun,
                                        ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord[] rootWords = null;
        if (isNotEmpty(rootBases)) {
            rootWords = new RootWord[rootBases.length];
            for (int i = 0; i < rootBases.length; i++) {
                rootWords[i] = getNounRootWord(rootBases[i], termType, verbalNoun, firstRadical, secondRadical,
                        thirdRadical, fourthRadical);
            }
        }
        return rootWords;
    }
}
