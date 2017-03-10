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
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alphasystem.app.morphologicalengine.spring.ApplicationContextProvider.getNounTransformerFactory;
import static com.alphasystem.app.morphologicalengine.spring.ApplicationContextProvider.getVerbTransformerFactory;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;

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

}
