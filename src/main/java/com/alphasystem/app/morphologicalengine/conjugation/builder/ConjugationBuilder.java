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
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alphasystem.app.morphologicalengine.spring.ApplicationContextProvider.getNounTransformerFactory;
import static com.alphasystem.app.morphologicalengine.spring.ApplicationContextProvider.getVerbTransformerFactory;

/**
 * @author sali
 */
public class ConjugationBuilder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @RuleProcessorType(RuleProcessorType.Type.RULE_ENGINE)
    private RuleProcessorFactory ruleEngineFactory;

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
        final RuleProcessor ruleProcessor = ruleEngineFactory.createRuleProcessor(ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (conjugationRoots.getPastPassiveTense() == null);

        final ChartConfiguration chartConfiguration = conjugationRoots.getChartConfiguration();

        final VerbConjugationGroup pastActiveTenseGroup = getPastActiveTenseGroup(ruleProcessor, conjugationRoots, rootLetters);
        final VerbConjugationGroup presentActiveTenseGroup = getPresentActiveTenseGroup(ruleProcessor, conjugationRoots, rootLetters);
        final NounConjugationGroup masculineActiveParticipleGroup = getMasculineActiveParticipleGroup(ruleProcessor, conjugationRoots, rootLetters);
        final NounConjugationGroup feminineActiveParticipleGroup = getFeminineActiveParticipleGroup(ruleProcessor, conjugationRoots, rootLetters);
        final VerbConjugationGroup imperativeGroup = getImperativeGroup(ruleProcessor, conjugationRoots, rootLetters);
        final VerbConjugationGroup forbiddenGroup = getForbiddenGroup(ruleProcessor, conjugationRoots, rootLetters);

        VerbConjugationGroup pastPassiveTenseGroup = null;
        VerbConjugationGroup presentPassiveTenseGroup = null;
        NounConjugationGroup masculinePassiveParticipleGroup = null;
        NounConjugationGroup femininePassiveParticipleGroup = null;
        if (!removePassiveLine) {
            pastPassiveTenseGroup = getPastPassiveTenseGroup(ruleProcessor, conjugationRoots, rootLetters);
            presentPassiveTenseGroup = getPresentPassiveTenseGroup(ruleProcessor, conjugationRoots, rootLetters);
            masculinePassiveParticipleGroup = getMasculinePassiveParticipleGroup(ruleProcessor, conjugationRoots, rootLetters);
            femininePassiveParticipleGroup = getFemininePassiveParticipleGroup(ruleProcessor, conjugationRoots, rootLetters);
        }

        AbbreviatedConjugation abbreviatedConjugation = null;
        if (!chartConfiguration.isOmitAbbreviatedConjugation()) {
            abbreviatedConjugation = ConjugationBuilderHelper.createAbbreviatedConjugation(conjugationRoots, rootLetters,
                    removePassiveLine, pastActiveTenseGroup, presentActiveTenseGroup, pastPassiveTenseGroup,
                    presentPassiveTenseGroup, imperativeGroup, forbiddenGroup, masculineActiveParticipleGroup,
                    masculinePassiveParticipleGroup, null, null);
        }

        DetailedConjugation detailedConjugation = null;
        if (!chartConfiguration.isOmitDetailedConjugation()) {
            detailedConjugation = ConjugationBuilderHelper.createDetailedConjugation(pastActiveTenseGroup,
                    presentActiveTenseGroup, pastPassiveTenseGroup, presentPassiveTenseGroup, imperativeGroup,
                    forbiddenGroup, masculineActiveParticipleGroup, feminineActiveParticipleGroup,
                    masculinePassiveParticipleGroup, femininePassiveParticipleGroup, null, null);
        }
        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, ArabicLetterType firstRadical,
                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                            ArabicLetterType fourthRadical) {
        return doConjugation(conjugationRoots, new RootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical));
    }

    private VerbConjugationGroup getPastActiveTenseGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                         RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PAST_TENSE;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getPastTense();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private VerbConjugationGroup getPresentActiveTenseGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                            RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PRESENT_TENSE;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getPresentTense();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private VerbConjugationGroup getPastPassiveTenseGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                          RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PAST_PASSIVE_TENSE;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getPastPassiveTense();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private VerbConjugationGroup getPresentPassiveTenseGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                             RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PRESENT_PASSIVE_TENSE;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getPresentPassiveTense();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private VerbConjugationGroup getImperativeGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                    RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.IMPERATIVE;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getImperative();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private VerbConjugationGroup getForbiddenGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                   RootLetters rootLetters) {
        VerbConjugationGroup verbConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.FORBIDDING;
        try {
            final VerbRootBase verbRootBase = conjugationRoots.getForbidding();
            verbConjugationGroup = getVerbTransformerFactory(verbRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, verbRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return verbConjugationGroup;
    }

    private NounConjugationGroup getMasculineActiveParticipleGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                                   RootLetters rootLetters) {
        NounConjugationGroup nounConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
        try {
            final NounRootBase nounRootBase = conjugationRoots.getActiveParticipleMasculine();
            nounConjugationGroup = getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, nounRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

    private NounConjugationGroup getFeminineActiveParticipleGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                                  RootLetters rootLetters) {
        NounConjugationGroup nounConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.ACTIVE_PARTICIPLE_FEMININE;
        try {
            final NounRootBase nounRootBase = conjugationRoots.getActiveParticipleFeminine();
            nounConjugationGroup = getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, nounRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

    private NounConjugationGroup getMasculinePassiveParticipleGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                                    RootLetters rootLetters) {
        NounConjugationGroup nounConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;
        try {
            final NounRootBase nounRootBase = conjugationRoots.getPassiveParticipleMasculine();
            nounConjugationGroup = getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, nounRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

    private NounConjugationGroup getFemininePassiveParticipleGroup(RuleProcessor ruleProcessor, ConjugationRoots conjugationRoots,
                                                                   RootLetters rootLetters) {
        NounConjugationGroup nounConjugationGroup = null;
        final SarfTermType sarfTermType = SarfTermType.PASSIVE_PARTICIPLE_FEMININE;
        try {
            final NounRootBase nounRootBase = conjugationRoots.getPassiveParticipleFeminine();
            nounConjugationGroup = getNounTransformerFactory(nounRootBase.getType()).doConjugation(ruleProcessor,
                    sarfTermType, nounRootBase, rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

}
