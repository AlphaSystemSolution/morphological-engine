package com.alphasystem.app.morphologicalengine.conjugation.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.checkFourthRadical;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createDefaultVerb;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createNounConjugationGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createVerbConjugationGroup;
import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.RULE_ENGINE;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getRuleProcessor;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.NOUN_OF_PLACE_AND_TIME;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_PASSIVE_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_PASSIVE_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public class DetailedConjugationBuilder {

    public DetailedConjugation doDetailedConjugation(final ConjugationRoots conjugationRoots) {
        return doDetailedConjugation(conjugationRoots, OutputFormat.UNICODE);
    }

    public DetailedConjugation doDetailedConjugation(final ConjugationRoots conjugationRoots,
                                                     final OutputFormat outputFormat) {
        RootLetters rootLetters = conjugationRoots.getRootLetters();
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.getConjugationConfiguration();
        final RuleInfo ruleInfo = new RuleInfo(conjugationRoots.getTemplate(), rootLetters,
                conjugationConfiguration.isSkipRuleProcessing());
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() ||
                (conjugationRoots.getPastPassiveTense() == null);

        final DetailedConjugation detailedConjugation = new DetailedConjugation();
        detailedConjugation.setPastTense(createVerbConjugationGroup(PAST_TENSE, conjugationRoots.getPastTense(),
                ruleProcessor, rootLetters, outputFormat));
        detailedConjugation.setPresentTense(createVerbConjugationGroup(PRESENT_TENSE, conjugationRoots.getPresentTense(),
                ruleProcessor, rootLetters, outputFormat));
        detailedConjugation.setActiveParticipleMasculine(createNounConjugationGroup(ACTIVE_PARTICIPLE_MASCULINE,
                conjugationRoots.getActiveParticipleMasculine(), ruleProcessor, rootLetters, outputFormat));
        detailedConjugation.setActiveParticipleFeminine(createNounConjugationGroup(ACTIVE_PARTICIPLE_FEMININE,
                conjugationRoots.getActiveParticipleFeminine(), ruleProcessor, rootLetters, outputFormat));

        detailedConjugation.setImperative(createVerbConjugationGroup(IMPERATIVE, conjugationRoots.getImperative(),
                ruleProcessor, rootLetters, outputFormat));
        detailedConjugation.setForbidding(createVerbConjugationGroup(FORBIDDING, conjugationRoots.getForbidding(),
                ruleProcessor, rootLetters, outputFormat));

        if (!removePassiveLine) {
            detailedConjugation.setPastPassiveTense(createVerbConjugationGroup(PAST_PASSIVE_TENSE,
                    conjugationRoots.getPastPassiveTense(), ruleProcessor, rootLetters, outputFormat));
            detailedConjugation.setPresentPassiveTense(createVerbConjugationGroup(PRESENT_PASSIVE_TENSE,
                    conjugationRoots.getPastPassiveTense(), ruleProcessor, rootLetters, outputFormat));
            detailedConjugation.setPassiveParticipleMasculine(createNounConjugationGroup(PASSIVE_PARTICIPLE_MASCULINE,
                    conjugationRoots.getPassiveParticipleMasculine(), ruleProcessor, rootLetters, outputFormat));
            detailedConjugation.setPassiveParticipleFeminine(createNounConjugationGroup(PASSIVE_PARTICIPLE_FEMININE,
                    conjugationRoots.getPassiveParticipleFeminine(), ruleProcessor, rootLetters, outputFormat));
        }

        detailedConjugation.setVerbalNouns(createMultiValues(VERBAL_NOUN, ruleProcessor, conjugationRoots.getVerbalNouns(),
                rootLetters, outputFormat));
        detailedConjugation.setAdverbs(createMultiValues(NOUN_OF_PLACE_AND_TIME, ruleProcessor, conjugationRoots.getAdverbs(),
                rootLetters, outputFormat));

        return detailedConjugation;
    }

    public VerbConjugationGroup[] doConjugate(SarfTermType sarfTermType, NamedTemplate template, RootLetters rootLetters,
                                              VerbRootBase[] verbRootBases, boolean skipRuleProcessing, OutputFormat outputFormat) {
        checkFourthRadical(rootLetters);

        final RuleInfo ruleInfo = new RuleInfo(template, rootLetters, skipRuleProcessing);
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);
        if (!SarfTermType.PAST_TENSE.equals(sarfTermType)) {
            doPastTenseConjugation(template, rootLetters, ruleProcessor, outputFormat);
        }
        return createMultiValues(sarfTermType, ruleProcessor, verbRootBases, rootLetters, outputFormat);
    }

    public NounConjugationGroup[] doConjugate(SarfTermType sarfTermType, NamedTemplate template, RootLetters rootLetters,
                                              NounRootBase[] nounRootBases, boolean skipRuleProcessing, OutputFormat outputFormat) {
        checkFourthRadical(rootLetters);

        final RuleInfo ruleInfo = new RuleInfo(template, rootLetters, skipRuleProcessing);
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);
        doPastTenseConjugation(template, rootLetters, ruleProcessor, outputFormat);
        return createMultiValues(sarfTermType, ruleProcessor, nounRootBases, rootLetters, outputFormat);
    }

    private void doPastTenseConjugation(NamedTemplate template, RootLetters rootLetters, RuleProcessor ruleProcessor,
                                        OutputFormat outputFormat) {
        createDefaultVerb(PAST_TENSE, Form.getByTemplate(template).getPastTense(), ruleProcessor, rootLetters, outputFormat);
    }

    private NounConjugationGroup[] createMultiValues(SarfTermType sarfTermType, RuleProcessor ruleProcessor,
                                                     NounRootBase[] nouns, RootLetters rootLetters, OutputFormat outputFormat) {
        if (ArrayUtils.isEmpty(nouns)) {
            return null;
        }
        final List<NounConjugationGroup> values = new ArrayList<>();

        Arrays.stream(nouns).forEach(nounRootBase -> values.add(createNounConjugationGroup(sarfTermType,
                nounRootBase, ruleProcessor, rootLetters, outputFormat)));

        return values.toArray(new NounConjugationGroup[values.size()]);
    }

    private VerbConjugationGroup[] createMultiValues(SarfTermType sarfTermType, RuleProcessor ruleProcessor,
                                                     VerbRootBase[] verbs, RootLetters rootLetters, OutputFormat outputFormat) {
        if (ArrayUtils.isEmpty(verbs)) {
            return null;
        }
        final List<VerbConjugationGroup> values = new ArrayList<>();

        Arrays.stream(verbs).forEach(verbRootBase -> values.add(createVerbConjugationGroup(sarfTermType,
                verbRootBase, ruleProcessor, rootLetters, outputFormat)));

        return values.toArray(new VerbConjugationGroup[values.size()]);
    }

}
