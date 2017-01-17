package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

import com.google.inject.Provider;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.GUICE_SUPPORT;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

/**
 * @author sali
 */
public final class AbbreviatedConjugationBuilder {

    private ConjugationHeaderBuilder conjugationHeaderBuilder;

    private AbbreviatedConjugationBuilder() {
        conjugationHeaderBuilder = GuiceSupport.getInstance().getConjugationHeaderBuilder();
    }

    public AbbreviatedConjugation createAbbreviatedConjugation(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                               RootLetters rootLetters, boolean removePassiveLine) {
        ActiveLine activeLine = createActiveLine(conjugationRoots, ruleProcessor, rootLetters);
        PassiveLine passiveLine = removePassiveLine ? null : createPassiveLine(conjugationRoots, ruleProcessor, rootLetters);
        ImperativeAndForbiddingLine imperativeAndForbiddingLine = createImperativeAndForbiddingLine(conjugationRoots,
                ruleProcessor, rootLetters);
        AdverbLine adverbLine = createAdverbLine(conjugationRoots, ruleProcessor, rootLetters);
        ConjugationHeader conjugationHeader = conjugationHeaderBuilder.createConjugationHeader(conjugationRoots, ruleProcessor, rootLetters);
        return new AbbreviatedConjugation(conjugationHeader, activeLine, passiveLine, imperativeAndForbiddingLine, adverbLine);
    }

    private ActiveLine createActiveLine(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor, RootLetters rootLetters) {
        RootWord pastTense = getTenseRootWord(conjugationRoots.pastTense, PAST_TENSE, ruleProcessor, rootLetters);

        RootWord presentTense = getTenseRootWord(conjugationRoots.presentTense, PRESENT_TENSE, ruleProcessor, rootLetters);

        RootWord activeParticipleMasculine = getNounRootWord(conjugationRoots.activeParticipleMasculine, ACTIVE_PARTICIPLE_MASCULINE,
                ruleProcessor, rootLetters);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, VERBAL_NOUN, true, ruleProcessor, rootLetters);
        return new ActiveLine(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

    private PassiveLine createPassiveLine(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor, RootLetters rootLetters) {
        RootWord pastPassiveTense = getTenseRootWord(conjugationRoots.pastPassiveTense, PAST_PASSIVE_TENSE, ruleProcessor,
                rootLetters);
        RootWord presentPassiveTense = getTenseRootWord(conjugationRoots.presentPassiveTense, PRESENT_PASSIVE_TENSE, ruleProcessor,
                rootLetters);
        RootWord passiveParticipleMasculine = getNounRootWord(conjugationRoots.passiveParticipleMasculine,
                PASSIVE_PARTICIPLE_MASCULINE, ruleProcessor, rootLetters);
        RootWord[] verbalNouns = getNounRootWords(conjugationRoots.verbalNouns, VERBAL_NOUN, true, ruleProcessor, rootLetters);
        return new PassiveLine(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine, verbalNouns);
    }

    private ImperativeAndForbiddingLine createImperativeAndForbiddingLine(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                                          RootLetters rootLetters) {
        RootWord imperative = getTenseRootWord(conjugationRoots.imperative, IMPERATIVE, ruleProcessor, rootLetters);
        RootWord forbidding = getTenseRootWord(conjugationRoots.forbidding, FORBIDDING, ruleProcessor, rootLetters);
        return new ImperativeAndForbiddingLine(imperative, forbidding);
    }

    private AdverbLine createAdverbLine(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor, RootLetters rootLetters) {
        return new AdverbLine(getNounRootWords(conjugationRoots.adverbs, NOUN_OF_PLACE_AND_TIME, false, ruleProcessor, rootLetters));
    }

    private RootWord getTenseRootWord(VerbRootBase verbRootBase, SarfTermType termType, RuleProcessor ruleProcessor,
                                      RootLetters rootLetters) {
        if (verbRootBase == null) {
            return null;
        }
        final Verb verb = verbRootBase.getRoot();
        boolean imperativeOrForbidden = IMPERATIVE.equals(termType) || FORBIDDING.equals(termType);
        String name = imperativeOrForbidden ? verb.getSecondPersonMasculineName() : verb.getThirdPersonMasculineName();
        final VerbTransformer verbTransformer = GUICE_SUPPORT.getVerbTransformer(name);
        final RootWord rootWord = new RootWord(verb.getRootWord()).withSarfTermType(termType);
        final ConjugationTuple tuple = verbTransformer.doTransform(ruleProcessor, rootWord, rootLetters.getFirstRadical(),
                rootLetters.getSecondRadical(), rootLetters.getThirdRadical(), rootLetters.getFourthRadical());
        return tuple.getSingular();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, SarfTermType termType, boolean verbalNoun,
                                     RuleProcessor ruleProcessor, RootLetters rootLetters) {
        final NounSupport singularBaseWord = rootBase.getSingularBaseWord();
        final NounTransformer transformer = GUICE_SUPPORT.getNounTransformer(singularBaseWord.getSingularRootName());
        final RootWord rootWord = new RootWord(singularBaseWord.getRootWord()).withSarfTermType(termType);
        final NounConjugation conjugation = transformer.doTransform(ruleProcessor, rootWord, rootLetters.getFirstRadical(),
                rootLetters.getSecondRadical(), rootLetters.getThirdRadical(), rootLetters.getFourthRadical());
        return verbalNoun ? conjugation.getAccusative() : conjugation.getNominative();
    }

    private RootWord getNounRootWord(NounRootBase rootBase, SarfTermType termType, RuleProcessor ruleProcessor,
                                     RootLetters rootLetters) {
        return getNounRootWord(rootBase, termType, false, ruleProcessor, rootLetters);
    }

    private RootWord[] getNounRootWords(NounRootBase[] rootBases, SarfTermType termType, boolean verbalNoun,
                                        RuleProcessor ruleProcessor, RootLetters rootLetters) {
        RootWord[] rootWords = null;
        if (isNotEmpty(rootBases)) {
            rootWords = new RootWord[rootBases.length];
            for (int i = 0; i < rootBases.length; i++) {
                rootWords[i] = getNounRootWord(rootBases[i], termType, verbalNoun, ruleProcessor, rootLetters);
            }
        }
        return rootWords;
    }

    static class ProviderImpl implements Provider<AbbreviatedConjugationBuilder>{

        @Override
        public AbbreviatedConjugationBuilder get() {
            return new AbbreviatedConjugationBuilder();
        }
    }
}
