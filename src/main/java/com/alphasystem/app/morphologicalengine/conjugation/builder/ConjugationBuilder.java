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
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import com.google.inject.Provider;

/**
 * @author sali
 */
public final class ConjugationBuilder {

    static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    static final int NUM_OF_COLUMNS = 2;

    private final AbbreviatedConjugationBuilder abbreviatedConjugationBuilder;
    private final DetailedConjugationBuilder detailedConjugationBuilder;

    private ConjugationBuilder() {
        abbreviatedConjugationBuilder = GUICE_SUPPORT.getAbbreviatedConjugationBuilder();
        detailedConjugationBuilder = GUICE_SUPPORT.getDetailedConjugationBuilder();
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

        boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() || (conjugationRoots.pastPassiveTense == null);

        final ChartConfiguration chartConfiguration = conjugationRoots.getChartConfiguration();

        AbbreviatedConjugation abbreviatedConjugation = null;
        if (!chartConfiguration.isOmitAbbreviatedConjugation()) {
            abbreviatedConjugation = abbreviatedConjugationBuilder.createAbbreviatedConjugation(conjugationRoots, ruleEngine,
                    rootLetters, removePassiveLine);
        }

        DetailedConjugation detailedConjugation = null;
        if (!chartConfiguration.isOmitDetailedConjugation()) {
            detailedConjugation = detailedConjugationBuilder.createDetailedConjugation(conjugationRoots, ruleEngine,
                    rootLetters, removePassiveLine);
        }

        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

    static class ProviderImpl implements Provider<ConjugationBuilder> {
        @Override
        public ConjugationBuilder get() {
            return new ConjugationBuilder();
        }
    }

}
