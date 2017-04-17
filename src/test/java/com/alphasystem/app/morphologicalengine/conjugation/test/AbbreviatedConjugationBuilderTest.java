package com.alphasystem.app.morphologicalengine.conjugation.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.AbbreviatedConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationRoots;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineConfiguration;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;

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
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;

/**
 * @author sali
 */
@ContextConfiguration(classes = {MorphologicalEngineConfiguration.class})
public class AbbreviatedConjugationBuilderTest extends ConjugationCommon {

    @Autowired
    private AbbreviatedConjugationBuilder abbreviatedConjugationBuilder;

    @Test(dataProvider = "data")
    public void runConjugationBuilder(NamedTemplate namedTemplate, String translation, NounRootBase[] verbalNouns,
                                      NounRootBase[] nounOfPlaceAndTime, RootLetters rootLetters) {
        final ConjugationRoots conjugationRoots = getConjugationRoots(namedTemplate, rootLetters, translation, verbalNouns,
                nounOfPlaceAndTime);
        final AbbreviatedConjugation abbreviatedConjugation = abbreviatedConjugationBuilder.doAbbreviatedConjugation(
                conjugationRoots, OutputFormat.HTML);
        printAbbreviatedConjugation(abbreviatedConjugation);
    }

    @DataProvider(name = "data")
    private Object[][] conjugationData() {
        return new Object[][]{
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Help", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        null, new RootLetters(NOON, SAD, RA)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Say", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        null, new RootLetters(QAF, WAW, LAM)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Eat", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        null, new RootLetters(HAMZA, KAF, LAM)},
                {FORM_IV_TEMPLATE, "To submit", null, null, new RootLetters(SEEN, LAM, MEEM)},
                {FORM_IV_TEMPLATE, "To send down", null, null, new RootLetters(NOON, ZAIN, LAM)},
                {FORM_IV_TEMPLATE, "To Establish", null, null, new RootLetters(QAF, WAW, MEEM)},
                {FORM_IX_TEMPLATE, "To collapse", null, null, new RootLetters(NOON, QAF, DDAD)}
        };
    }
}
