package com.alphasystem.app.morphologicalengine.conjugation.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.MorphologicalChart;

/**
 * @author sali
 */
public class ConjugationBuilder {

    private static final int NUM_OF_COLUMNS = 2;
    private Logger logger = LoggerFactory.getLogger(getClass());

    static void checkFourthRadical(RootLetters rootLetters) {
        if (rootLetters.hasFourthRadical()) {
            throw new RuntimeException("Fourth radical has not been implemented yet.");
        }
    }

    private final AbbreviatedConjugationBuilder abbreviatedConjugationBuilder;
    private final DetailedConjugationBuilder detailedConjugationBuilder;

    @Autowired
    public ConjugationBuilder(AbbreviatedConjugationBuilder abbreviatedConjugationBuilder,
                              DetailedConjugationBuilder detailedConjugationBuilder) {
        this.abbreviatedConjugationBuilder = abbreviatedConjugationBuilder;
        this.detailedConjugationBuilder = detailedConjugationBuilder;
    }


    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots) {
        return doConjugation(conjugationRoots, OutputFormat.UNICODE);
    }

    public MorphologicalChart doConjugation(ConjugationRoots conjugationRoots, final OutputFormat outputFormat) {
        RootLetters rootLetters = conjugationRoots.getRootLetters();
        checkFourthRadical(rootLetters);

        AbbreviatedConjugation abbreviatedConjugation = abbreviatedConjugationBuilder.doAbbreviatedConjugation(
                conjugationRoots, outputFormat);
        DetailedConjugation detailedConjugation = detailedConjugationBuilder.doDetailedConjugation(conjugationRoots, outputFormat);
        return new MorphologicalChart(abbreviatedConjugation, detailedConjugation);
    }

}
