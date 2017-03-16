package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.MorphologicalChart;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createAbbreviatedConjugation;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createDetailedConjugation;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.RULE_ENGINE;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getNounTransformerFactory;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getRuleProcessor;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getVerbTransformerFactory;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;

/**
 * @author sali
 */
public class ConjugationBuilder {

    static final int NUM_OF_COLUMNS = 2;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executor = Executors.newFixedThreadPool(24);

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("PreDestroy");
        executor.shutdown();
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots) {
        return doConjugation(conjugationRoots, OutputFormat.UNICODE);
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, OutputFormat outputFormat) {
        outputFormat = outputFormat == null ? OutputFormat.UNICODE : outputFormat;

        RootLetters rootLetters = conjugationRoots.getRootLetters();
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.getConjugationConfiguration();
        final NamedTemplate template = conjugationRoots.getTemplate();
        final RuleInfo ruleInfo = new RuleInfo(template, rootLetters, conjugationConfiguration.isSkipRuleProcessing());
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (conjugationRoots.getPastPassiveTense() == null);

        final ChartConfiguration chartConfiguration = conjugationRoots.getChartConfiguration();

        final Future<VerbConjugationGroup> pastActiveTenseGroupFuture = getVerbConjugationGroup(PAST_TENSE, outputFormat,
                ruleProcessor, rootLetters, conjugationRoots.getPastTense());

        final Future<VerbConjugationGroup> presentActiveTenseGroupFuture = getVerbConjugationGroup(PRESENT_TENSE,
                outputFormat, ruleProcessor, rootLetters, conjugationRoots.getPresentTense());

        final Future<NounConjugationGroup> masculineActiveParticipleGroupFuture = getNounConjugationGroup(
                ACTIVE_PARTICIPLE_MASCULINE, outputFormat, ruleProcessor, rootLetters, conjugationRoots.getActiveParticipleMasculine());

        final Future<NounConjugationGroup> feminineActiveParticipleGroupFuture = getNounConjugationGroup(
                ACTIVE_PARTICIPLE_FEMININE, outputFormat, ruleProcessor, rootLetters, conjugationRoots.getActiveParticipleFeminine());

        final Future<VerbConjugationGroup> imperativeGroupFuture = getVerbConjugationGroup(IMPERATIVE, outputFormat,
                ruleProcessor, rootLetters, conjugationRoots.getImperative());

        final Future<VerbConjugationGroup> forbiddenGroupFuture = getVerbConjugationGroup(FORBIDDING, outputFormat,
                ruleProcessor, rootLetters, conjugationRoots.getForbidding());

        final Future<NounConjugationGroup>[] verbalNounConjugationFutureGroups = getNounConjugationGroups(VERBAL_NOUN, outputFormat,
                ruleProcessor, rootLetters, conjugationRoots.getVerbalNouns());

        final Future<NounConjugationGroup>[] nounOfPlaceAndTimeConjugationFutureGroups = getNounConjugationGroups(NOUN_OF_PLACE_AND_TIME,
                outputFormat, ruleProcessor, rootLetters, conjugationRoots.getAdverbs());

        Future<VerbConjugationGroup> pastPassiveTenseGroupFuture = null;
        Future<VerbConjugationGroup> presentPassiveTenseGroupFuture = null;
        Future<NounConjugationGroup> masculinePassiveParticipleGroupFuture = null;
        Future<NounConjugationGroup> femininePassiveParticipleGroupFuture = null;
        VerbConjugationGroup pastPassiveTenseGroup = null;
        VerbConjugationGroup presentPassiveTenseGroup = null;
        NounConjugationGroup masculinePassiveParticipleGroup = null;
        NounConjugationGroup femininePassiveParticipleGroup = null;
        if (!removePassiveLine) {
            pastPassiveTenseGroupFuture = getVerbConjugationGroup(PAST_PASSIVE_TENSE, outputFormat, ruleProcessor,
                    rootLetters, conjugationRoots.getPastPassiveTense());
            presentPassiveTenseGroupFuture = getVerbConjugationGroup(PRESENT_PASSIVE_TENSE, outputFormat, ruleProcessor,
                    rootLetters, conjugationRoots.getPresentPassiveTense());
            masculinePassiveParticipleGroupFuture = getNounConjugationGroup(PASSIVE_PARTICIPLE_MASCULINE, outputFormat,
                    ruleProcessor, rootLetters, conjugationRoots.getPassiveParticipleMasculine());
            femininePassiveParticipleGroupFuture = getNounConjugationGroup(PASSIVE_PARTICIPLE_FEMININE, outputFormat,
                    ruleProcessor, rootLetters, conjugationRoots.getPassiveParticipleFeminine());
        }

        final VerbConjugationGroup pastActiveTenseGroup = getVerbConjugationGroup(pastActiveTenseGroupFuture,
                PAST_TENSE, template, rootLetters);
        final VerbConjugationGroup presentActiveTenseGroup = getVerbConjugationGroup(presentActiveTenseGroupFuture,
                PRESENT_TENSE, template, rootLetters);
        final NounConjugationGroup masculineActiveParticipleGroup = getNounConjugationGroup(masculineActiveParticipleGroupFuture,
                ACTIVE_PARTICIPLE_MASCULINE, template, rootLetters);
        final NounConjugationGroup feminineActiveParticipleGroup = getNounConjugationGroup(feminineActiveParticipleGroupFuture,
                ACTIVE_PARTICIPLE_FEMININE, template, rootLetters);
        final VerbConjugationGroup imperativeGroup = getVerbConjugationGroup(imperativeGroupFuture, IMPERATIVE, template, rootLetters);
        final VerbConjugationGroup forbiddenGroup = getVerbConjugationGroup(forbiddenGroupFuture, FORBIDDING, template, rootLetters);
        final NounConjugationGroup[] verbalNounConjugationGroups = getNounConjugationGroups(verbalNounConjugationFutureGroups,
                VERBAL_NOUN, template, rootLetters);
        final NounConjugationGroup[] nounOfPlaceAndTimeConjugationGroups = getNounConjugationGroups(nounOfPlaceAndTimeConjugationFutureGroups,
                NOUN_OF_PLACE_AND_TIME, template, rootLetters);
        if (!removePassiveLine) {
            pastPassiveTenseGroup = getVerbConjugationGroup(pastPassiveTenseGroupFuture, PAST_PASSIVE_TENSE, template, rootLetters);
            presentPassiveTenseGroup = getVerbConjugationGroup(presentPassiveTenseGroupFuture, PRESENT_PASSIVE_TENSE, template, rootLetters);
            masculinePassiveParticipleGroup = getNounConjugationGroup(masculinePassiveParticipleGroupFuture,
                    PASSIVE_PARTICIPLE_MASCULINE, template, rootLetters);
            femininePassiveParticipleGroup = getNounConjugationGroup(femininePassiveParticipleGroupFuture,
                    PASSIVE_PARTICIPLE_FEMININE, template, rootLetters);
        }

        AbbreviatedConjugation abbreviatedConjugation = null;
        if (!chartConfiguration.isOmitAbbreviatedConjugation()) {
            abbreviatedConjugation = createAbbreviatedConjugation(conjugationRoots, rootLetters,
                    removePassiveLine, pastActiveTenseGroup, presentActiveTenseGroup, pastPassiveTenseGroup,
                    presentPassiveTenseGroup, imperativeGroup, forbiddenGroup, masculineActiveParticipleGroup,
                    masculinePassiveParticipleGroup, verbalNounConjugationGroups, nounOfPlaceAndTimeConjugationGroups,
                    outputFormat);
        }

        DetailedConjugation detailedConjugation = null;
        if (!chartConfiguration.isOmitDetailedConjugation()) {
            detailedConjugation = createDetailedConjugation(pastActiveTenseGroup,
                    presentActiveTenseGroup, pastPassiveTenseGroup, presentPassiveTenseGroup, imperativeGroup,
                    forbiddenGroup, masculineActiveParticipleGroup, feminineActiveParticipleGroup,
                    masculinePassiveParticipleGroup, femininePassiveParticipleGroup, verbalNounConjugationGroups,
                    nounOfPlaceAndTimeConjugationGroups);
        }

        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

    private Future<VerbConjugationGroup> getVerbConjugationGroup(SarfTermType sarfTermType, OutputFormat outputFormat,
                                                                 RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                                 VerbRootBase verbRootBase) {
        return executor.submit(() -> (verbRootBase == null) ? null :
                getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor, sarfTermType, outputFormat,
                        verbRootBase, rootLetters));
    }

    private VerbConjugationGroup getVerbConjugationGroup(Future<VerbConjugationGroup> future, SarfTermType sarfTermType,
                                                         NamedTemplate template, RootLetters rootLetters) {
        if (future == null) {
            return null;
        }
        VerbConjugationGroup verbConjugationGroup = null;
        try {
            verbConjugationGroup = future.get();
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, template, rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private Future<NounConjugationGroup> getNounConjugationGroup(SarfTermType sarfTermType, OutputFormat outputFormat,
                                                                 RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                                 NounRootBase nounRootBase) {
        return executor.submit(() -> (nounRootBase == null) ? null :
                getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor, sarfTermType, outputFormat,
                        nounRootBase, rootLetters));
    }

    private NounConjugationGroup getNounConjugationGroup(Future<NounConjugationGroup> future, SarfTermType sarfTermType,
                                                         NamedTemplate template, RootLetters rootLetters) {
        if (future == null) {
            return null;
        }
        NounConjugationGroup nounConjugationGroup = null;
        try {
            nounConjugationGroup = future.get();
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, template, rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

    @SuppressWarnings("unchecked")
    private Future<NounConjugationGroup>[] getNounConjugationGroups(SarfTermType sarfTermType, OutputFormat outputFormat,
                                                                    RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                                    NounRootBase[] nounRootBases) {
        if (!ArrayUtils.isEmpty(nounRootBases)) {
            Future<NounConjugationGroup>[] nounConjugationGroups = null;

            List<NounRootBase> rootBaseList = new ArrayList<>();
            Collections.addAll(rootBaseList, nounRootBases);
            while ((rootBaseList.size() % NUM_OF_COLUMNS) != 0) {
                rootBaseList.add(null);
            }

            int fromIndex = 0;
            int toIndex = NUM_OF_COLUMNS;
            while (fromIndex < rootBaseList.size()) {
                final List<NounRootBase> subList = rootBaseList.subList(fromIndex, toIndex);
                final Future<NounConjugationGroup> rightSideGroup = getNounConjugationGroup(sarfTermType, outputFormat,
                        ruleProcessor, rootLetters, subList.get(0));
                final Future<NounConjugationGroup> leftSideGroup = getNounConjugationGroup(sarfTermType, outputFormat,
                        ruleProcessor, rootLetters, subList.get(1));
                nounConjugationGroups = ArrayUtils.addAll(nounConjugationGroups, leftSideGroup, rightSideGroup);
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }

            return nounConjugationGroups;
        }
        return null;
    }

    private NounConjugationGroup[] getNounConjugationGroups(Future<NounConjugationGroup>[] futures, SarfTermType sarfTermType,
                                                            NamedTemplate template, RootLetters rootLetters) {
        NounConjugationGroup[] nounConjugationGroups = new NounConjugationGroup[0];
        if (!ArrayUtils.isEmpty(futures)) {
            for (Future<NounConjugationGroup> future : futures) {
                final NounConjugationGroup nounConjugationGroup = getNounConjugationGroup(future, sarfTermType, template, rootLetters);
                nounConjugationGroups = ArrayUtils.add(nounConjugationGroups, nounConjugationGroup);
            }
        }
        return nounConjugationGroups;
    }


}
