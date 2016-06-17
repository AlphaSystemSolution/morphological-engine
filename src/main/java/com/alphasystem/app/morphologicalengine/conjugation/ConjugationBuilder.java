package com.alphasystem.app.morphologicalengine.conjugation;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * @author sali
 */
public class ConjugationBuilder {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    private static final int NUM_OF_COLUMNS = 2;

    private ConjugationConfiguration conjugationConfiguration;
    private ChartConfiguration chartConfiguration;
    private NamedTemplate template;
    private RuleProcessor ruleEngine;
    private String translation;
    private VerbRootBase pastTense;
    private VerbRootBase presentTense;
    private VerbRootBase pastPassiveTense;
    private VerbRootBase presentPassiveTense;
    private NounRootBase activeParticipleMasculine;
    private NounRootBase activeParticipleFeminine;
    private NounRootBase passiveParticipleMasculine;
    private NounRootBase passiveParticipleFeminine;
    private VerbRootBase imperative;
    private VerbRootBase forbidding;
    private NounRootBase[] verbalNouns;
    private NounRootBase[] adverbs;

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    public MorphologicalChart doConjugation(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootLetters rootLetters = new RootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical);
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = getConjugationConfiguration();
        ruleEngine = null;
        if (!conjugationConfiguration.isSkipRuleProcessing()) {
            ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(template));
        }

        boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (pastPassiveTense == null);

        final AbbreviatedConjugation abbreviatedConjugation = createAbbreviatedConjugation(firstRadical, secondRadical,
                thirdRadical, fourthRadical, removePassiveLine);
        final DetailedConjugation detailedConjugation = createDetailedConjugation(rootLetters, removePassiveLine);
        return new MorphologicalChart(null, abbreviatedConjugation, detailedConjugation);
    }

    private AbbreviatedConjugation createAbbreviatedConjugation(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
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
        RootWord pastTense = getTenseRootWord(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, this.pastTense, firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        RootWord presentTense = getTenseRootWord(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, this.presentTense,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord activeParticipleMasculine = getNounRootWord(this.activeParticipleMasculine, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(this.verbalNouns, true, firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new ActiveLine(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

    private PassiveLine createPassiveLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                          ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord pastPassiveTense = getTenseRootWord(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, this.pastPassiveTense,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord presentPassiveTense = getTenseRootWord(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, this.presentPassiveTense,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord passiveParticipleMasculine = getNounRootWord(this.passiveParticipleMasculine, firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        RootWord[] verbalNouns = getNounRootWords(this.verbalNouns, true, firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new PassiveLine(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine, verbalNouns);
    }

    private ImperativeAndForbiddingLine createImperativeAndForbiddingLine(ArabicLetterType firstRadical,
                                                                          ArabicLetterType secondRadical,
                                                                          ArabicLetterType thirdRadical,
                                                                          ArabicLetterType fourthRadical) {
        final String name = this.template.equals(FORM_IV_TEMPLATE) ? FORM_IV_IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER :
                IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER;
        RootWord imperative = getTenseRootWord(name, this.imperative, firstRadical, secondRadical, thirdRadical, fourthRadical);
        RootWord forbidding = getTenseRootWord(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER, this.forbidding,
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new ImperativeAndForbiddingLine(imperative, forbidding);
    }

    private AdverbLine createAdverbLine(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new AdverbLine(getNounRootWords(this.adverbs, false, firstRadical, secondRadical, thirdRadical, fourthRadical));
    }

    private RootWord getTenseRootWord(String name, VerbRootBase rootBase, ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                      ArabicLetterType fourthRadical) {
        final VerbTransformer verbTransformer = GUICE_SUPPORT.getVerbTransformer(name);
        final ConjugationTuple tuple = verbTransformer.doTransform(ruleEngine, rootBase.getRoot().getRootWord(),
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

    private DetailedConjugation createDetailedConjugation(RootLetters rootLetters, boolean removePassiveLine) {
        final VerbDetailedConjugationPair activeTensePair = createActiveTensePair(rootLetters);
        final NounDetailedConjugationPair activeParticiplePair = createActiveParticiplePair(rootLetters);
        final NounDetailedConjugationPair[] verbalNounPairs = createVerbalNounPairs(rootLetters);
        final VerbDetailedConjugationPair passiveTensePair = removePassiveLine ? null : createPassiveTensePair(rootLetters);
        final NounDetailedConjugationPair passiveParticiplePair = removePassiveLine ? null : createPassiveParticiplePair(rootLetters);
        final NounDetailedConjugationPair[] adverbPairs = createAdverbPairs(rootLetters);
        final VerbDetailedConjugationPair imperativeAndForbiddingPair = createImperativeAndForbiddingPair(rootLetters);

        return new DetailedConjugation(activeTensePair, verbalNounPairs, activeParticiplePair, passiveTensePair,
                passiveParticiplePair, imperativeAndForbiddingPair, adverbPairs);
    }

    private VerbDetailedConjugationPair createTensePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                        VerbRootBase leftBase, VerbRootBase rightBase,
                                                        RootLetters rootLetters) {
        VerbConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            TenseMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, template, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleEngine, rightBase, rootLetters);
        }

        VerbConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            TenseMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, template, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleEngine, leftBase, rootLetters);
        }
        return new VerbDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private VerbDetailedConjugationPair createActiveTensePair(RootLetters rootLetters) {
        return createTensePair(PRESENT_TENSE, PAST_TENSE, presentTense, pastTense, rootLetters);
    }

    private VerbDetailedConjugationPair createPassiveTensePair(RootLetters rootLetters) {
        return createTensePair(PRESENT_PASSIVE_TENSE, PAST_PASSIVE_TENSE, presentPassiveTense, pastPassiveTense, rootLetters);
    }

    private VerbDetailedConjugationPair createImperativeAndForbiddingPair(RootLetters rootLetters) {
        return createTensePair(FORBIDDING, IMPERATIVE, forbidding, imperative, rootLetters);
    }

    private NounDetailedConjugationPair createParticiplePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                             NounRootBase leftBase, NounRootBase rightBase,
                                                             RootLetters rootLetters) {
        NounConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            ParticipleMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleEngine, leftBase, rootLetters);
        }

        NounConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            ParticipleMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleEngine, rightBase, rootLetters);
        }
        return new NounDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private NounDetailedConjugationPair createActiveParticiplePair(RootLetters rootLetters) {
        return createParticiplePair(ACTIVE_PARTICIPLE_FEMININE, ACTIVE_PARTICIPLE_MASCULINE, activeParticipleFeminine,
                activeParticipleMasculine, rootLetters);
    }

    private NounDetailedConjugationPair createPassiveParticiplePair(RootLetters rootLetters) {
        return createParticiplePair(PASSIVE_PARTICIPLE_FEMININE, PASSIVE_PARTICIPLE_MASCULINE, passiveParticipleFeminine,
                passiveParticipleMasculine, rootLetters);
    }

    private NounDetailedConjugationPair[] createVerbalNounPairs(RootLetters rootLetters) {
        return createPairs(verbalNouns, VERBAL_NOUN, rootLetters);
    }

    private NounDetailedConjugationPair[] createAdverbPairs(RootLetters rootLetters) {
        return createPairs(adverbs, NOUN_OF_PLACE_AND_TIME, rootLetters);
    }

    private NounDetailedConjugationPair[] createPairs(NounRootBase[] pairs, SarfTermType termType, RootLetters rootLetters) {
        NounDetailedConjugationPair[] result = new NounDetailedConjugationPair[0];

        if (!isEmpty(pairs)) {
            List<NounRootBase> rootBaseList = new ArrayList<>();
            Collections.addAll(rootBaseList, pairs);
            while (rootBaseList.size() % NUM_OF_COLUMNS != 0) {
                rootBaseList.add(null);
            }

            int fromIndex = 0;
            int toIndex = NUM_OF_COLUMNS;
            while (fromIndex < rootBaseList.size()) {
                final List<NounRootBase> subList = rootBaseList.subList(fromIndex, toIndex);
                result = add(result, createPair(subList.get(1), subList.get(0), termType, rootLetters));
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }
        }
        return result;
    }

    private NounDetailedConjugationPair createPair(NounRootBase leftBase, NounRootBase rightBase, SarfTermType termType,
                                                   RootLetters rootLetters) {
        SarfTermType leftTerm = (leftBase == null) ? null : termType;
        SarfTermType rightTerm = (rightBase == null) ? null : termType;
        return createParticiplePair(leftTerm, rightTerm, leftBase, rightBase, rootLetters);
    }

    public ConjugationConfiguration getConjugationConfiguration() {
        if (conjugationConfiguration == null) {
            conjugationConfiguration = new ConjugationConfiguration();
        }
        return conjugationConfiguration;
    }

    public void setConjugationConfiguration(ConjugationConfiguration conjugationConfiguration) {
        this.conjugationConfiguration = conjugationConfiguration;
    }

    public ChartConfiguration getChartConfiguration() {
        if (chartConfiguration == null) {
            chartConfiguration = new ChartConfiguration();
        }
        return chartConfiguration;
    }

    public void setChartConfiguration(ChartConfiguration chartConfiguration) {
        this.chartConfiguration = chartConfiguration;
    }

    public ConjugationBuilder applyTemplate(NamedTemplate template) {
        return applyTemplate(Form.getByTemplate(template));
    }

    public ConjugationBuilder applyTemplate(Form form) {
        if (form == null) {
            throw new NullPointerException("Form cannot be null.");
        }
        template = form.getTemplate();
        pastTense(form.getPastTense()).presentTense(form.getPresentTense()).pastPassiveTense(form.getPastPassiveTense())
                .presentPassiveTense(form.getPresentPassiveTense()).activeParticipleMasculine(form.getActiveParticipleMasculine())
                .activeParticipleFeminine(form.getActiveParticipleFeminine()).passiveParticipleMasculine(form.getPassiveParticipleMasculine())
                .passiveParticipleFeminine(form.getPassiveParticipleFeminine()).imperative(form.getImperative()).forbidding(form.getForbidding());
        setVerbalNouns(form.getVerbalNouns());
        setAdverbs(form.getAdverbs());
        return this;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setPastTense(VerbRootBase pastTense) {
        this.pastTense = pastTense;
    }

    public void setPresentTense(VerbRootBase presentTense) {
        this.presentTense = presentTense;
    }

    public void setPastPassiveTense(VerbRootBase pastPassiveTense) {
        this.pastPassiveTense = pastPassiveTense;
    }

    public void setPresentPassiveTense(VerbRootBase presentPassiveTense) {
        this.presentPassiveTense = presentPassiveTense;
    }

    public void setActiveParticipleMasculine(NounRootBase activeParticipleMasculine) {
        this.activeParticipleMasculine = activeParticipleMasculine;
    }

    public void setActiveParticipleFeminine(NounRootBase activeParticipleFeminine) {
        this.activeParticipleFeminine = activeParticipleFeminine;
    }

    public void setPassiveParticipleMasculine(NounRootBase passiveParticipleMasculine) {
        this.passiveParticipleMasculine = passiveParticipleMasculine;
    }

    public void setPassiveParticipleFeminine(NounRootBase passiveParticipleFeminine) {
        this.passiveParticipleFeminine = passiveParticipleFeminine;
    }

    public void setImperative(VerbRootBase imperative) {
        this.imperative = imperative;
    }

    public void setForbidding(VerbRootBase forbidding) {
        this.forbidding = forbidding;
    }

    public void setVerbalNouns(NounRootBase[] verbalNouns) {
        this.verbalNouns = ArrayUtils.addAll(new NounRootBase[0], verbalNouns);
    }

    public void setAdverbs(NounRootBase[] adverbs) {
        this.adverbs = ArrayUtils.addAll(new NounRootBase[0], adverbs);
    }

    public ConjugationBuilder conjugationConfiguration(ConjugationConfiguration conjugationConfiguration) {
        setConjugationConfiguration(conjugationConfiguration);
        return this;
    }

    public ConjugationBuilder conjugationConfiguration(boolean removePassiveLine, boolean skipRuleProcessing) {
        setConjugationConfiguration(new ConjugationConfiguration().removePassiveLine(removePassiveLine)
                .skipRuleProcessing(skipRuleProcessing));
        return this;
    }

    public ConjugationBuilder chartConfiguration(ChartConfiguration chartConfiguration) {
        setChartConfiguration(chartConfiguration);
        return this;
    }

    public ConjugationBuilder translation(String translation) {
        setTranslation(translation);
        return this;
    }

    public ConjugationBuilder pastTense(VerbRootBase pastTense) {
        setPastTense(pastTense);
        return this;
    }

    public ConjugationBuilder presentTense(VerbRootBase presentTense) {
        setPresentTense(presentTense);
        return this;
    }

    public ConjugationBuilder pastPassiveTense(VerbRootBase pastPassiveTense) {
        setPastPassiveTense(pastPassiveTense);
        return this;
    }

    public ConjugationBuilder presentPassiveTense(VerbRootBase presentPassiveTense) {
        setPresentPassiveTense(presentPassiveTense);
        return this;
    }

    public ConjugationBuilder activeParticipleMasculine(NounRootBase activeParticipleMasculine) {
        setActiveParticipleMasculine(activeParticipleMasculine);
        return this;
    }

    public ConjugationBuilder activeParticipleFeminine(NounRootBase activeParticipleFeminine) {
        setActiveParticipleFeminine(activeParticipleFeminine);
        return this;
    }

    public ConjugationBuilder passiveParticipleMasculine(NounRootBase passiveParticipleMasculine) {
        setPassiveParticipleMasculine(passiveParticipleMasculine);
        return this;
    }

    public ConjugationBuilder passiveParticipleFeminine(NounRootBase passiveParticipleFeminine) {
        setPassiveParticipleFeminine(passiveParticipleFeminine);
        return this;
    }

    public ConjugationBuilder imperative(VerbRootBase imperative) {
        setImperative(imperative);
        return this;
    }

    public ConjugationBuilder forbidding(VerbRootBase forbidding) {
        setForbidding(forbidding);
        return this;
    }

    public ConjugationBuilder verbalNouns(NounRootBase... verbalNouns) {
        if (!isEmpty(verbalNouns)) {
            this.verbalNouns = ArrayUtils.addAll(this.verbalNouns, verbalNouns);
        }
        return this;
    }

    public ConjugationBuilder adverbs(NounRootBase... adverbs) {
        if (!isEmpty(adverbs)) {
            this.adverbs = ArrayUtils.addAll(this.adverbs, adverbs);
        }
        return this;
    }

    public ConjugationBuilder reset() {
        setPastTense(null);
        return this;
    }

}
