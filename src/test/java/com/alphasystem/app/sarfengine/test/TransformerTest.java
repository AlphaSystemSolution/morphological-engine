package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.TransformerFactory;
import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugation;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.conjugation.model.FormTemplate.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static java.lang.String.format;

/**
 * @author sali
 */
public class TransformerTest extends CommonTest {

    private static TransformerFactory transformerFactory;

    static {
        transformerFactory = guiceSupport.getTransformerFactory();
    }

    @Test
    public void testConjugations() {
        addTable(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
        addTable(FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, DDAD, RA, BA);
        addTable(FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, FA, TA, HHA);
        addTable(FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, SEEN, MEEM, AIN);
        addTable(FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, HHA, SEEN, BA);
        addTable(FORM_I_CATEGORY_U_TEMPLATE, KAF, RA, MEEM);
        addTable(FORM_II_TEMPLATE, AIN, LAM, MEEM);
        addTable(FORM_III_TEMPLATE, JEEM, HA, DAL);
        addTable(FORM_IV_TEMPLATE, SEEN, LAM, MEEM);
        addTable(FORM_V_TEMPLATE, AIN, LAM, MEEM);
        addTable(FORM_VI_TEMPLATE, SEEN, HAMZA, LAM);
        addTable(FORM_VII_TEMPLATE, KAF, SEEN, RA);
        addTable(FORM_VIII_TEMPLATE, QAF, RA, BA);
        addTable(FORM_X_TEMPLATE, GHAIN, FA, RA);
    }

    private void addTable(FormTemplate formTemplate, ArabicLetterType firstRadical,
                          ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createRootWords(formTemplate, firstRadical, secondRadical, thirdRadical);
        addTable(formTemplate.name(), rootWords);
    }

    private void addTable(String title, RootWord... rootWords) {
        lines.add(format(".%s", title));
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(addGenderHeader());
        lines.add(addNumberHeader());
        addRow(lines, NOMINATIVE_SINGULAR, rootWords, 0);
        addRow(lines, ACCUSATIVE_SINGULAR, rootWords, 6);
        addRow(lines, GENITIVE_SINGULAR, rootWords, 12);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private RootWord[] createRootWords(FormTemplate formTemplate, ArabicLetterType firstRadical,
                                       ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        RootWord rootWord = formTemplate.getActiveParticipleMasculineRoot();
        NounTransformer nounTransformer = transformerFactory.getMasculineEndingSoundTransformer(null);
        NounConjugation nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 5);

        nounTransformer = transformerFactory.getMasculineDualTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 4);

        nounTransformer = transformerFactory.getMasculinePluralTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 3);

        rootWord = formTemplate.getActiveParticipleFeminineRoot();
        nounTransformer = transformerFactory.getFeminineEndingSoundTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 2);

        nounTransformer = transformerFactory.getFeminineDualTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 1);

        nounTransformer = transformerFactory.getFemininePluralTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 0);

        return rootWords;
    }

}
