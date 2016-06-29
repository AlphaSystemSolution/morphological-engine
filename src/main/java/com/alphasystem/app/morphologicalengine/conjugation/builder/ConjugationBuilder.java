package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;

/**
 * @author sali
 */
public class ConjugationBuilder {

    static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    static final int NUM_OF_COLUMNS = 2;

    private final ConjugationRoots conjugationRoots;

    public ConjugationBuilder() {
        conjugationRoots = new ConjugationRoots();
    }

    public ConjugationRoots getConjugationRoots() {
        return conjugationRoots;
    }

    private static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    public MorphologicalChart doConjugation(ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootLetters rootLetters = new RootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical);
        checkFourthRadical(rootLetters);

        final ConjugationConfiguration conjugationConfiguration = conjugationRoots.conjugationConfiguration;
        RuleProcessor ruleEngine = null;
        if (!conjugationConfiguration.isSkipRuleProcessing()) {
            ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(conjugationRoots.template, rootLetters));
        }

        boolean removePassiveLine = conjugationConfiguration.isRemovePassiveLine() ||
                (conjugationRoots.pastPassiveTense == null);

        final AbbreviatedConjugationBuilder abbreviatedConjugationBuilder = new AbbreviatedConjugationBuilder(conjugationRoots, ruleEngine);
        final AbbreviatedConjugation abbreviatedConjugation = abbreviatedConjugationBuilder.createAbbreviatedConjugation(
                firstRadical, secondRadical, thirdRadical, fourthRadical, removePassiveLine);

        final DetailedConjugationBuilder detailedConjugationBuilder = new DetailedConjugationBuilder(conjugationRoots, ruleEngine);
        final DetailedConjugation detailedConjugation = detailedConjugationBuilder.createDetailedConjugation(rootLetters, removePassiveLine);

        final ConjugationHeaderBuilder conjugationHeaderBuilder = new ConjugationHeaderBuilder(conjugationRoots, ruleEngine);
        final ConjugationHeader conjugationHeader = conjugationHeaderBuilder.createConjugationHeader(rootLetters);

        return new MorphologicalChart(conjugationHeader, abbreviatedConjugation, detailedConjugation);
    }

    public ConjugationBuilder applyTemplate(NamedTemplate template) {
        ConjugationHelper.applyTemplate(conjugationRoots, template);
        return this;
    }

    public ConjugationBuilder applyTemplate(Form form) {
        ConjugationHelper.applyTemplate(conjugationRoots, form);
        return this;
    }

}
