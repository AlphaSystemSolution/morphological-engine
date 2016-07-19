package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.DetailedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.google.inject.Provider;

/**
 * @author sali
 */
public class ConjugationBuilder {

    static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    static final int NUM_OF_COLUMNS = 2;

    private final AbbreviatedConjugationBuilder abbreviatedConjugationBuilder;
    private final DetailedConjugationBuilder detailedConjugationBuilder;

    ConjugationBuilder() {
        abbreviatedConjugationBuilder = GUICE_SUPPORT.getInstance(AbbreviatedConjugationBuilder.class);
        detailedConjugationBuilder = GUICE_SUPPORT.getInstance(DetailedConjugationBuilder.class);
    }

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, ArabicLetterType firstRadical,
                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                            ArabicLetterType fourthRadical) {
        RootLetters rootLetters = new RootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical);
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.conjugationConfiguration;
        RuleProcessor ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(conjugationRoots.template, rootLetters,
                conjugationConfiguration.isSkipRuleProcessing()));

        boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() ||
                (conjugationRoots.pastPassiveTense == null);

        final AbbreviatedConjugation abbreviatedConjugation = abbreviatedConjugationBuilder.createAbbreviatedConjugation(
                conjugationRoots, ruleEngine, rootLetters, removePassiveLine);

        final DetailedConjugation detailedConjugation = detailedConjugationBuilder.createDetailedConjugation(conjugationRoots,
                ruleEngine, rootLetters, removePassiveLine);

        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

    static class ProviderImpl implements Provider<ConjugationBuilder> {
        @Override
        public ConjugationBuilder get() {
            return new ConjugationBuilder();
        }
    }

}
