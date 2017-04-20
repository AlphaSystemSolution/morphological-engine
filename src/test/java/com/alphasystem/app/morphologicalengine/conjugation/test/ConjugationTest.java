package com.alphasystem.app.morphologicalengine.conjugation.test;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationRoots;
import com.alphasystem.app.morphologicalengine.conjugation.builder.DetailedConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounOfPlaceAndTimeFactory;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineConfiguration;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalengine.model.MorphologicalChart;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationHelper.getConjugationRoots;
import static com.alphasystem.app.morphologicalengine.conjugation.model.VerbalNounFactory.getByVerbalNoun;
import static com.alphasystem.arabic.model.ArabicLetterType.DDAD;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.arabic.model.ArabicLetterType.KAF;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.MEEM;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.QAF;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.SAD;
import static com.alphasystem.arabic.model.ArabicLetterType.SEEN;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IX_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;

/**
 * @author sali
 */
@ContextConfiguration(classes = {MorphologicalEngineConfiguration.class})
public class ConjugationTest extends ConjugationCommon {

    private static final NounRootBase[] FORM_I_ADVERBS = new NounRootBase[]{NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V1,
            NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V2, NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V3};

    @Autowired
    private ConjugationBuilder conjugationBuilder;

    @Autowired
    private DetailedConjugationBuilder detailedConjugationBuilder;

   /* @Test(dataProvider = "data")
    public void runConjugationBuilder(NamedTemplate namedTemplate, String translation, NounRootBase[] verbalNouns,
                                      NounRootBase[] nounOfPlaceAndTime, RootLetters rootLetters) {
        final ConjugationRoots conjugationRoots = getConjugationRoots(namedTemplate, rootLetters, translation, verbalNouns,
                nounOfPlaceAndTime);
        printMorphologicalChart(conjugationBuilder.doConjugation(conjugationRoots, OutputFormat.HTML));
    }*/

    @Test(dataProvider = "data")
    public void doDetailConjugation(NamedTemplate namedTemplate, String translation, NounRootBase[] verbalNouns,
                                    NounRootBase[] nounOfPlaceAndTime, RootLetters rootLetters) {
        final ConjugationRoots conjugationRoots = getConjugationRoots(namedTemplate, rootLetters, translation, verbalNouns,
                nounOfPlaceAndTime);
        final NounRootBase[] nounRootBases = new NounRootBase[]{conjugationRoots.getActiveParticipleMasculine()};
        if (!ArrayUtils.isEmpty(nounRootBases)) {
            final boolean skipRuleProcessing = conjugationRoots.getConjugationConfiguration().isSkipRuleProcessing();
            final NounConjugationGroup[] groups = detailedConjugationBuilder.doConjugate(ACTIVE_PARTICIPLE_MASCULINE,
                    namedTemplate, rootLetters, nounRootBases, skipRuleProcessing, OutputFormat.HTML);
            Arrays.stream(groups).forEach(this::printNounConjugation);
        }
    }

    @DataProvider(name = "data")
    private Object[][] conjugationData() {
        return new Object[][]{
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Help", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(NOON, SAD, RA)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Say", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(QAF, WAW, LAM)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Eat", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(HAMZA, KAF, LAM)},
                {FORM_IV_TEMPLATE, "To submit", null, null, new RootLetters(SEEN, LAM, MEEM)},
                {FORM_IV_TEMPLATE, "To send down", null, null, new RootLetters(NOON, ZAIN, LAM)},
                {FORM_IV_TEMPLATE, "To Establish", null, null, new RootLetters(QAF, WAW, MEEM)},
                {FORM_IX_TEMPLATE, "To collapse", null, null, new RootLetters(NOON, QAF, DDAD)}
        };
    }

    private void printMorphologicalChart(MorphologicalChart chart) {
        System.out.println(chart.namedTemplate() + ":" + chart.rootLetters());
        printAbbreviatedConjugation(chart.getAbbreviatedConjugation());
        printDetailedConjugation(chart.getDetailedConjugation());
        System.out.println("______________________________________________________");
        System.out.println();
    }

}
