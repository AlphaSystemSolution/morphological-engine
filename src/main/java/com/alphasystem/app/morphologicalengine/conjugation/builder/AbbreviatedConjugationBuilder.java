package com.alphasystem.app.morphologicalengine.conjugation.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.checkFourthRadical;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createDefaultNoun;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilderHelper.createDefaultVerb;
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
import static java.lang.String.format;

/**
 * @author sali
 */
public class AbbreviatedConjugationBuilder {

    public AbbreviatedConjugation doAbbreviatedConjugation(final ConjugationRoots conjugationRoots) {
        return doAbbreviatedConjugation(conjugationRoots, OutputFormat.UNICODE);
    }

    public AbbreviatedConjugation doAbbreviatedConjugation(final ConjugationRoots conjugationRoots,
                                                           final OutputFormat outputFormat) {
        RootLetters rootLetters = conjugationRoots.getRootLetters();
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.getConjugationConfiguration();
        final NamedTemplate template = conjugationRoots.getTemplate();
        final RuleInfo ruleInfo = new RuleInfo(template, rootLetters,
                conjugationConfiguration.isSkipRuleProcessing());
        final RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, ruleInfo);

        final boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() ||
                (conjugationRoots.getPastPassiveTense() == null);

        final AbbreviatedConjugation abbreviatedConjugation = new AbbreviatedConjugation();
        abbreviatedConjugation.setId(format("%s_%s", template.name(), rootLetters.getName()));

        // past tense
        String pastTense = createDefaultVerb(PAST_TENSE, conjugationRoots.getPastTense(), ruleProcessor, rootLetters, outputFormat);
        abbreviatedConjugation.pastTense(pastTense);

        // present tense
        String presentTense = createDefaultVerb(PRESENT_TENSE, conjugationRoots.getPresentTense(), ruleProcessor,
                rootLetters, outputFormat);
        abbreviatedConjugation.presentTense(presentTense);

        abbreviatedConjugation.setConjugationHeader(ConjugationBuilderHelper.createConjugationHeader(conjugationRoots,
                pastTense, presentTense, outputFormat));

        // active participle masculine
        String defaultValue = createDefaultNoun(ACTIVE_PARTICIPLE_MASCULINE, conjugationRoots.getActiveParticipleMasculine(),
                ruleProcessor, rootLetters, outputFormat);
        abbreviatedConjugation.activeParticipleMasculine(defaultValue);

        // active participle feminine
        defaultValue = createDefaultNoun(ACTIVE_PARTICIPLE_FEMININE, conjugationRoots.getActiveParticipleFeminine(),
                ruleProcessor, rootLetters, outputFormat);
        abbreviatedConjugation.activeParticipleFeminine(defaultValue);

        // imperative
        defaultValue = createDefaultVerb(IMPERATIVE, conjugationRoots.getImperative(), ruleProcessor, rootLetters, outputFormat);
        abbreviatedConjugation.imperative(defaultValue);

        // forbidden
        defaultValue = createDefaultVerb(FORBIDDING, conjugationRoots.getForbidding(), ruleProcessor, rootLetters, outputFormat);
        abbreviatedConjugation.forbidding(defaultValue);

        if (!removePassiveLine) {
            // past passive tense
            defaultValue = createDefaultVerb(PAST_PASSIVE_TENSE, conjugationRoots.getPastPassiveTense(), ruleProcessor,
                    rootLetters, outputFormat);
            abbreviatedConjugation.pastPassiveTense(defaultValue);

            // present passive tense
            defaultValue = createDefaultVerb(PRESENT_PASSIVE_TENSE, conjugationRoots.getPresentPassiveTense(), ruleProcessor,
                    rootLetters, outputFormat);
            abbreviatedConjugation.presentPassiveTense(defaultValue);

            // passive participle masculine
            defaultValue = createDefaultNoun(PASSIVE_PARTICIPLE_MASCULINE, conjugationRoots.getPassiveParticipleMasculine(),
                    ruleProcessor, rootLetters, outputFormat);
            abbreviatedConjugation.passiveParticipleMasculine(defaultValue);

            // passive participle masculine
            defaultValue = createDefaultNoun(PASSIVE_PARTICIPLE_FEMININE, conjugationRoots.getPassiveParticipleFeminine(),
                    ruleProcessor, rootLetters, outputFormat);
            abbreviatedConjugation.passiveParticipleFeminine(defaultValue);
        }

        // verbal nouns
        abbreviatedConjugation.verbalNouns(createMultiValues(VERBAL_NOUN, ruleProcessor, conjugationRoots.getVerbalNouns(),
                rootLetters, outputFormat));

        abbreviatedConjugation.adverbs(createMultiValues(NOUN_OF_PLACE_AND_TIME, ruleProcessor,
                conjugationRoots.getAdverbs(), rootLetters, outputFormat));


        return abbreviatedConjugation;
    }

    private String[] createMultiValues(SarfTermType sarfTermType, RuleProcessor ruleProcessor,
                                       NounRootBase[] nouns, RootLetters rootLetters, OutputFormat outputFormat) {
        if (ArrayUtils.isEmpty(nouns)) {
            return null;
        }
        final List<String> values = new ArrayList<>();

        Arrays.stream(nouns).forEach(nounRootBase -> values.add(createDefaultNoun(sarfTermType, nounRootBase, ruleProcessor,
                rootLetters, outputFormat)));

        return values.toArray(new String[values.size()]);
    }
}
