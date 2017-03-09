package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.DetailedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleEngineFactory;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public abstract class AbstractFormBuilder implements FormBuilder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PAST_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory pastActiveTenseTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PRESENT_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory presentActiveTenseTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PAST_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory pastPassiveTenseTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PRESENT_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory presentPassiveTenseTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.IMPERATIVE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory imperativeTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.FORBIDDING_TRANSFORMER_FACTORY)
    private VerbTransformerFactory forbiddingTransformerFactory;

    @Autowired
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    private NounTransformerFactory masculineActiveParticipleTransformerFactory;

    @Autowired
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    private NounTransformerFactory feminineActiveParticipleTransformerFactory;

    @Autowired
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.MASCULINE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    private NounTransformerFactory masculinePassiveParticipleTransformerFactory;

    @Autowired
    @NounTransformerFactoryType(NounTransformerFactoryType.Type.FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY)
    private NounTransformerFactory femininePassiveParticipleTransformerFactory;

    @Autowired
    @RuleProcessorType(RuleProcessorType.Type.RULE_ENGINE)
    private RuleEngineFactory ruleEngineFactory;

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    @Override
    public VerbTransformerFactory pastActiveTenseTransformerFactory() {
        return pastActiveTenseTransformerFactory;
    }

    @Override
    public VerbTransformerFactory presentActiveTenseTransformerFactory() {
        return presentActiveTenseTransformerFactory;
    }

    @Override
    public VerbTransformerFactory pastPassiveTenseTransformerFactory() {
        return pastPassiveTenseTransformerFactory;
    }

    @Override
    public VerbTransformerFactory presentPassiveTenseTransformerFactory() {
        return presentPassiveTenseTransformerFactory;
    }

    @Override
    public VerbTransformerFactory imperativeTransformerFactory() {
        return imperativeTransformerFactory;
    }

    @Override
    public VerbTransformerFactory forbiddingTransformerFactory() {
        return forbiddingTransformerFactory;
    }

    @Override
    public NounTransformerFactory masculineActiveParticipleTransformerFactory() {
        return masculineActiveParticipleTransformerFactory;
    }

    @Override
    public NounTransformerFactory feminineActiveParticipleTransformerFactory() {
        return feminineActiveParticipleTransformerFactory;
    }

    @Override
    public NounTransformerFactory masculinePassiveParticipleTransformerFactory() {
        return masculinePassiveParticipleTransformerFactory;
    }

    @Override
    public NounTransformerFactory femininePassiveParticipleTransformerFactory() {
        return femininePassiveParticipleTransformerFactory;
    }

    @Override
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

    @Override
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
            verbConjugationGroup = pastActiveTenseTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPastTense(), rootLetters);
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
            verbConjugationGroup = presentActiveTenseTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPresentTense(), rootLetters);
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
            verbConjugationGroup = pastPassiveTenseTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPastPassiveTense(), rootLetters);
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
            verbConjugationGroup = presentPassiveTenseTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPresentPassiveTense(), rootLetters);
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
            verbConjugationGroup = imperativeTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getImperative(), rootLetters);
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
            verbConjugationGroup = imperativeTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getForbidding(), rootLetters);
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
            nounConjugationGroup = masculineActiveParticipleTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getActiveParticipleMasculine(), rootLetters);
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
            nounConjugationGroup = masculineActiveParticipleTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getActiveParticipleFeminine(), rootLetters);
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
            nounConjugationGroup = masculineActiveParticipleTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPassiveParticipleMasculine(), rootLetters);
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
            nounConjugationGroup = masculineActiveParticipleTransformerFactory().doConjugation(ruleProcessor,
                    sarfTermType, conjugationRoots.getPassiveParticipleFeminine(), rootLetters);
        } catch (Exception e) {
            logger.warn("Unable to get {} group for template \"{}\", root letters are \"{}\"{}    Error message is: {}",
                    sarfTermType, conjugationRoots.getTemplate(), rootLetters, System.lineSeparator(), e.getMessage());
        }
        return nounConjugationGroup;
    }

}
