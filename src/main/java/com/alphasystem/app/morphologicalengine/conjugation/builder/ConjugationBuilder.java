package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.MorphologicalChart;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.NounDetailedConjugationPair;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbDetailedConjugationPair;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyImperativeAndForbiddingGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyMasculineAndFeminineActiveParticipleGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyMasculineAndFemininePassiveParticipleGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyNounOfPlaceAndTimeGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyPastAndPresentActiveTenseGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyPastAndPresentPassiveTenseGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.applyVerbalNounGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.RULE_ENGINE;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getNounTransformerFactory;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getRuleProcessor;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getVerbTransformerFactory;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;

/**
 * @author sali
 */
public class ConjugationBuilder {

    private static final int NUM_OF_COLUMNS = 2;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots) {
        return doConjugation(conjugationRoots, OutputFormat.UNICODE);
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, final OutputFormat outputFormat) {
        RootLetters rootLetters = conjugationRoots.getRootLetters();
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.getConjugationConfiguration();
        final NamedTemplate template = conjugationRoots.getTemplate();
        final RuleInfo ruleInfo = new RuleInfo(template, rootLetters, conjugationConfiguration.isSkipRuleProcessing());
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (conjugationRoots.getPastPassiveTense() == null);

        AbbreviatedConjugation abbreviatedConjugation = new AbbreviatedConjugation();
        DetailedConjugation detailedConjugation = new DetailedConjugation();

        final VerbDetailedConjugationPair pastAndPresentActiveTenseGroup =
                createVerbDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters, PRESENT_TENSE,
                        conjugationRoots.getPresentTense(), PAST_TENSE, conjugationRoots.getPastTense());
        applyPastAndPresentActiveTenseGroup(pastAndPresentActiveTenseGroup, abbreviatedConjugation,
                detailedConjugation, conjugationRoots, outputFormat);

        final NounDetailedConjugationPair masculineAndFeminineActiveParticiplePair =
                createNounDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters, ACTIVE_PARTICIPLE_FEMININE,
                        conjugationRoots.getActiveParticipleFeminine(), ACTIVE_PARTICIPLE_MASCULINE,
                        conjugationRoots.getActiveParticipleMasculine());
        applyMasculineAndFeminineActiveParticipleGroup(masculineAndFeminineActiveParticiplePair, abbreviatedConjugation,
                detailedConjugation, outputFormat);

        final VerbDetailedConjugationPair imperativeAndForbiddingGroup =
                createVerbDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters, FORBIDDING,
                        conjugationRoots.getForbidding(), IMPERATIVE, conjugationRoots.getImperative());
        applyImperativeAndForbiddingGroup(imperativeAndForbiddingGroup, abbreviatedConjugation, detailedConjugation,
                outputFormat);

        final NounDetailedConjugationPair[] verbalNounConjugationFutureGroups =
                getNounConjugationGroups(VERBAL_NOUN, outputFormat, ruleProcessor, rootLetters, conjugationRoots.getVerbalNouns());
        if (!ArrayUtils.isEmpty(verbalNounConjugationFutureGroups)) {
            for (NounDetailedConjugationPair nounDetailedConjugationPair : verbalNounConjugationFutureGroups) {
                if (nounDetailedConjugationPair != null) {
                    applyVerbalNounGroup(nounDetailedConjugationPair, abbreviatedConjugation, detailedConjugation, outputFormat);
                }
            }
        }

        final NounDetailedConjugationPair[] nounOfPlaceAndTimeConjugationFutureGroups =
                getNounConjugationGroups(NOUN_OF_PLACE_AND_TIME, outputFormat, ruleProcessor, rootLetters,
                        conjugationRoots.getAdverbs());
        if (!ArrayUtils.isEmpty(nounOfPlaceAndTimeConjugationFutureGroups)) {
            for (NounDetailedConjugationPair nounDetailedConjugationPair : nounOfPlaceAndTimeConjugationFutureGroups) {
                if (nounDetailedConjugationPair != null) {
                    applyNounOfPlaceAndTimeGroup(nounDetailedConjugationPair, abbreviatedConjugation,
                            detailedConjugation, outputFormat);
                }
            }
        }

        if (!removePassiveLine) {
            final VerbDetailedConjugationPair pastAndPresentPassiveTenseGroup =
                    createVerbDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters, PRESENT_PASSIVE_TENSE,
                            conjugationRoots.getPresentPassiveTense(), PAST_PASSIVE_TENSE,
                            conjugationRoots.getPastPassiveTense());
            applyPastAndPresentPassiveTenseGroup(pastAndPresentPassiveTenseGroup, abbreviatedConjugation, detailedConjugation);

            final NounDetailedConjugationPair masculineAndFemininePassiveParticipleGroup =
                    createNounDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters,
                            PASSIVE_PARTICIPLE_FEMININE, conjugationRoots.getPassiveParticipleFeminine(),
                            PASSIVE_PARTICIPLE_MASCULINE, conjugationRoots.getPassiveParticipleMasculine());
            applyMasculineAndFemininePassiveParticipleGroup(masculineAndFemininePassiveParticipleGroup, abbreviatedConjugation,
                    detailedConjugation, outputFormat);
        }

        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

    private VerbConjugationGroup getVerbConjugationGroup(Request<VerbRootBase> request) {
        return getVerbTransformerFactory(request.getRootBase().getType()).doConjugation(request.getRuleProcessor(),
                request.getSarfTermType(), request.getOutputFormat(), request.getRootBase(), request.getRootLetters());
    }

    private NounConjugationGroup getNounConjugationGroup(Request<NounRootBase> request) {
        NounRootBase rootBase = null;
        if (request != null) {
            rootBase = request.getRootBase();
        }
        NounTransformerFactoryType.Type type = null;
        if (rootBase != null) {
            type = rootBase.getType();
        }
        return (type == null) ? null : getNounTransformerFactory(type).doConjugation(request.getRuleProcessor(),
                request.getSarfTermType(), request.getOutputFormat(), rootBase, request.getRootLetters());
    }

    private VerbDetailedConjugationPair createVerbDetailedConjugationPair(OutputFormat outputFormat,
                                                                          RuleProcessor ruleProcessor,
                                                                          RootLetters rootLetters,
                                                                          SarfTermType leftTerm,
                                                                          VerbRootBase leftWord,
                                                                          SarfTermType rightTerm,
                                                                          VerbRootBase rightWord) {
        logger.debug("<<<<< Start creating VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);

        final Request<VerbRootBase> rightSideRequest = new Request<>(outputFormat, ruleProcessor, rootLetters, rightTerm, rightWord);
        final VerbConjugationGroup rightSideGroup = getVerbConjugationGroup(rightSideRequest);

        final Request<VerbRootBase> leftSideRequest = new Request<>(outputFormat, ruleProcessor, rootLetters, leftTerm, leftWord);
        final VerbConjugationGroup leftSideGroup = getVerbConjugationGroup(leftSideRequest);

        final VerbDetailedConjugationPair conjugationPair = ConjugationBuilderHelper.createVerbDetailedConjugationPair(
                leftSideGroup, rightSideGroup);

        logger.debug("<<<<< Finish creating VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);
        return conjugationPair;
    }

    private NounDetailedConjugationPair createNounDetailedConjugationPair(OutputFormat outputFormat,
                                                                          RuleProcessor ruleProcessor,
                                                                          RootLetters rootLetters,
                                                                          SarfTermType leftTerm,
                                                                          NounRootBase leftWord,
                                                                          SarfTermType rightTerm,
                                                                          NounRootBase rightWord) {
        logger.debug("<<<<< Start creating NounDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);

        final Request<NounRootBase> rightSideRequest = new Request<>(outputFormat, ruleProcessor, rootLetters, rightTerm, rightWord);
        final NounConjugationGroup rightSideGroup = getNounConjugationGroup(rightSideRequest);

        final Request<NounRootBase> leftSideRequest = new Request<>(outputFormat, ruleProcessor, rootLetters, leftTerm, leftWord);
        final NounConjugationGroup leftSideGroup = getNounConjugationGroup(leftSideRequest);

        final NounDetailedConjugationPair conjugationPair = ConjugationBuilderHelper.createNounDetailedConjugationPair(
                leftSideGroup, rightSideGroup);

        logger.debug("<<<<< Finish creating NounDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);

        return conjugationPair;
    }

    @SuppressWarnings("unchecked")
    private NounDetailedConjugationPair[] getNounConjugationGroups(SarfTermType sarfTermType, OutputFormat outputFormat,
                                                                   RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                                   NounRootBase[] nounRootBases) {
        if (!ArrayUtils.isEmpty(nounRootBases)) {
            NounDetailedConjugationPair[] nounConjugationGroups = null;

            List<NounRootBase> rootBaseList = new ArrayList<>();
            Collections.addAll(rootBaseList, nounRootBases);
            while ((rootBaseList.size() % NUM_OF_COLUMNS) != 0) {
                rootBaseList.add(null);
            }

            int fromIndex = 0;
            int toIndex = NUM_OF_COLUMNS;
            while (fromIndex < rootBaseList.size()) {
                final List<NounRootBase> subList = rootBaseList.subList(fromIndex, toIndex);

                final NounDetailedConjugationPair nounDetailedConjugationPair =
                        createNounDetailedConjugationPair(outputFormat, ruleProcessor, rootLetters, sarfTermType, subList.get(1),
                                sarfTermType, subList.get(0));

                nounConjugationGroups = ArrayUtils.addAll(nounConjugationGroups, nounDetailedConjugationPair);
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }

            return nounConjugationGroups;
        }
        return null;
    }

    private static final class Request<R extends RootBase> {

        private final OutputFormat outputFormat;
        private final RuleProcessor ruleProcessor;
        private final RootLetters rootLetters;
        private final SarfTermType sarfTermType;
        private final R rootBase;

        private Request(OutputFormat outputFormat, RuleProcessor ruleProcessor, RootLetters rootLetters,
                        SarfTermType sarfTermType, R rootBase) {
            this.outputFormat = outputFormat;
            this.ruleProcessor = ruleProcessor;
            this.rootLetters = rootLetters;
            this.sarfTermType = sarfTermType;
            this.rootBase = rootBase;
        }

        OutputFormat getOutputFormat() {
            return outputFormat;
        }

        RuleProcessor getRuleProcessor() {
            return ruleProcessor;
        }

        RootLetters getRootLetters() {
            return rootLetters;
        }

        SarfTermType getSarfTermType() {
            return sarfTermType;
        }

        R getRootBase() {
            return rootBase;
        }
    }

}
