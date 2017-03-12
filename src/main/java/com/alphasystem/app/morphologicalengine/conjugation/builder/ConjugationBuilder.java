package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.DetailedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, RootLetters rootLetters) {
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.getConjugationConfiguration();
        final RuleInfo ruleInfo = new RuleInfo(conjugationRoots.getTemplate(), rootLetters,
                conjugationConfiguration.isSkipRuleProcessing());
        final RuleProcessor ruleProcessor =  getRuleProcessor(RULE_ENGINE, ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (conjugationRoots.getPastPassiveTense() == null);

        final ChartConfiguration chartConfiguration = conjugationRoots.getChartConfiguration();

        final VerbConjugationGroup pastActiveTenseGroup = getVerbConjugationGroup(PAST_TENSE, conjugationRoots.getTemplate(),
                ruleProcessor, rootLetters, conjugationRoots.getPastTense());
        final VerbConjugationGroup presentActiveTenseGroup = getVerbConjugationGroup(PRESENT_TENSE, conjugationRoots.getTemplate(),
                ruleProcessor, rootLetters, conjugationRoots.getPresentTense());
        final NounConjugationGroup masculineActiveParticipleGroup = getNounConjugationGroup(ACTIVE_PARTICIPLE_MASCULINE,
                conjugationRoots.getTemplate(), ruleProcessor, rootLetters, conjugationRoots.getActiveParticipleMasculine());
        final NounConjugationGroup feminineActiveParticipleGroup = getNounConjugationGroup(ACTIVE_PARTICIPLE_FEMININE,
                conjugationRoots.getTemplate(), ruleProcessor, rootLetters, conjugationRoots.getActiveParticipleFeminine());
        final VerbConjugationGroup imperativeGroup = getVerbConjugationGroup(IMPERATIVE, conjugationRoots.getTemplate(),
                ruleProcessor, rootLetters, conjugationRoots.getImperative());
        final VerbConjugationGroup forbiddenGroup = getVerbConjugationGroup(FORBIDDING, conjugationRoots.getTemplate(),
                ruleProcessor, rootLetters, conjugationRoots.getForbidding());
        final NounConjugationGroup[] verbalNounConjugationGroups = getNounConjugationGroups(VERBAL_NOUN,
                conjugationRoots.getTemplate(), ruleProcessor, rootLetters, conjugationRoots.getVerbalNouns());
        final NounConjugationGroup[] nounOfPlaceAndTimeConjugationGroups = getNounConjugationGroups(NOUN_OF_PLACE_AND_TIME,
                conjugationRoots.getTemplate(), ruleProcessor, rootLetters, conjugationRoots.getAdverbs());

        VerbConjugationGroup pastPassiveTenseGroup = null;
        VerbConjugationGroup presentPassiveTenseGroup = null;
        NounConjugationGroup masculinePassiveParticipleGroup = null;
        NounConjugationGroup femininePassiveParticipleGroup = null;
        if (!removePassiveLine) {
            pastPassiveTenseGroup = getVerbConjugationGroup(PAST_PASSIVE_TENSE, conjugationRoots.getTemplate(),
                    ruleProcessor, rootLetters, conjugationRoots.getPastPassiveTense());
            presentPassiveTenseGroup = getVerbConjugationGroup(PRESENT_PASSIVE_TENSE, conjugationRoots.getTemplate(),
                    ruleProcessor, rootLetters, conjugationRoots.getPresentPassiveTense());
            masculinePassiveParticipleGroup = getNounConjugationGroup(PASSIVE_PARTICIPLE_MASCULINE,
                    conjugationRoots.getTemplate(), ruleProcessor, rootLetters, conjugationRoots.getPassiveParticipleMasculine());
            femininePassiveParticipleGroup = getNounConjugationGroup(PASSIVE_PARTICIPLE_FEMININE, conjugationRoots.getTemplate(),
                    ruleProcessor, rootLetters, conjugationRoots.getPassiveParticipleFeminine());
        }

        AbbreviatedConjugation abbreviatedConjugation = null;
        if (!chartConfiguration.isOmitAbbreviatedConjugation()) {
            abbreviatedConjugation = createAbbreviatedConjugation(conjugationRoots, rootLetters,
                    removePassiveLine, pastActiveTenseGroup, presentActiveTenseGroup, pastPassiveTenseGroup,
                    presentPassiveTenseGroup, imperativeGroup, forbiddenGroup, masculineActiveParticipleGroup,
                    masculinePassiveParticipleGroup, verbalNounConjugationGroups, nounOfPlaceAndTimeConjugationGroups);
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

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, ArabicLetterType firstRadical,
                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                            ArabicLetterType fourthRadical) {
        return doConjugation(conjugationRoots, new RootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical));
    }

    private VerbConjugationGroup getVerbConjugationGroup(SarfTermType sarfTermType, NamedTemplate namedTemplate,
                                                         RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                         VerbRootBase verbRootBase) {
        VerbConjugationGroup verbConjugationGroup = null;
        if (verbRootBase != null) {
            try {
                verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                        sarfTermType, verbRootBase, rootLetters);
            } catch (Exception e) {
                logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                        sarfTermType, namedTemplate, rootLetters, System.lineSeparator(), e.getMessage());
            }
        }
        return verbConjugationGroup;
    }

    private NounConjugationGroup getNounConjugationGroup(SarfTermType sarfTermType, NamedTemplate namedTemplate,
                                                         RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                         NounRootBase nounRootBase) {
        NounConjugationGroup nounConjugationGroup = null;
        if (nounRootBase != null) {
            try {
                nounConjugationGroup = getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor,
                        sarfTermType, nounRootBase, rootLetters);
            } catch (Exception e) {
                logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                        sarfTermType, namedTemplate, rootLetters, System.lineSeparator(), e.getMessage());
            }
        }
        return nounConjugationGroup;
    }

    private NounConjugationGroup[] getNounConjugationGroups(SarfTermType sarfTermType, NamedTemplate namedTemplate,
                                                            RuleProcessor ruleProcessor, RootLetters rootLetters,
                                                            NounRootBase[] nounRootBases) {
        if (!ArrayUtils.isEmpty(nounRootBases)) {
            NounConjugationGroup[] nounConjugationGroups = null;

            List<NounRootBase> rootBaseList = new ArrayList<>();
            Collections.addAll(rootBaseList, nounRootBases);
            while ((rootBaseList.size() % NUM_OF_COLUMNS) != 0) {
                rootBaseList.add(null);
            }

            int fromIndex = 0;
            int toIndex = NUM_OF_COLUMNS;
            while (fromIndex < rootBaseList.size()) {
                final List<NounRootBase> subList = rootBaseList.subList(fromIndex, toIndex);
                final NounConjugationGroup rightSideGroup = getNounConjugationGroup(sarfTermType, namedTemplate,
                        ruleProcessor, rootLetters, subList.get(0));
                final NounConjugationGroup leftSideGroup = getNounConjugationGroup(sarfTermType, namedTemplate,
                        ruleProcessor, rootLetters, subList.get(1));
                nounConjugationGroups = ArrayUtils.addAll(nounConjugationGroups, leftSideGroup, rightSideGroup);
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }

            return nounConjugationGroups;
        }
        return null;
    }


}
