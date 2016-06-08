package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IX_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.NOUN_OF_PLACE_AND_TIME_FORM_IV;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_IV;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_IX;
import static java.util.Arrays.asList;

/**
 * @author sali
 */
public class ConjugationTest extends CommonTest {

    @Test
    public void runConjugationBuilder() {
        ConjugationBuilderFactory cbf = GuiceSupport.getInstance().getConjugationBuilderFactory();
        ConjugationBuilder cb = cbf.getConjugationBuilder();
        MorphologicalChart morphologicalChart = cb.doConjugation(FORM_IV_TEMPLATE, "To send down", false, false, NOON, ZAIN, LAM,
                asList(VERBAL_NOUN_FORM_IV), asList(NOUN_OF_PLACE_AND_TIME_FORM_IV));
        printConjugation(morphologicalChart);

        morphologicalChart = cb.doConjugation(FORM_IX_TEMPLATE, "To collapse", true, false, NOON, QAF, DDAD,
                asList(VERBAL_NOUN_FORM_IX), null);
        printConjugation(morphologicalChart);
    }

}
