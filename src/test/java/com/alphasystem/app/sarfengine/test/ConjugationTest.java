package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.ConjugationStack;
import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.app.sarfengine.conjugation.model.SarfKabeer;
import com.alphasystem.app.sarfengine.conjugation.model.SarfKabeerPair;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.VerbalNoun;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.sarfengine.xml.model.NounOfPlaceAndTime.NOUN_OF_PLACE_AND_TIME_FORM_IV;
import static java.util.Arrays.asList;

/**
 * @author sali
 */
public class ConjugationTest extends CommonTest {

    @Test
    public void runConjugationBuilder() {
        ConjugationBuilderFactory cbf = GuiceSupport.getInstance().getConjugationBuilderFactory();
        ConjugationBuilder cb = cbf.getConjugationBuilder();
        SarfChart sarfChart = cb.doConjugation(NamedTemplate.FORM_IV_TEMPLATE, "To send down", false, false,
                NOON, ZAIN, LAM, asList(VerbalNoun.VERBAL_NOUN_FORM_IV), asList(NOUN_OF_PLACE_AND_TIME_FORM_IV));
        SarfKabeer sarfKabeer = sarfChart.getSarfKabeer();

        SarfKabeerPair sarfKabeerPair = sarfKabeer.getActiveTensePair();

        ConjugationStack leftSideStack = sarfKabeerPair.getLeftSideStack();
        ConjugationStack rightSideStack = sarfKabeerPair.getRightSideStack();
        printTable(leftSideStack.getConjugations(), rightSideStack.getConjugations(), leftSideStack.getLabel(),
                rightSideStack.getLabel(), true);

        sarfKabeerPair = sarfKabeer.getActiveParticiplePair();
        leftSideStack = sarfKabeerPair.getLeftSideStack();
        rightSideStack = sarfKabeerPair.getRightSideStack();
        printTable(leftSideStack.getConjugations(), rightSideStack.getConjugations(), leftSideStack.getLabel(),
                rightSideStack.getLabel(), false);
    }
}
