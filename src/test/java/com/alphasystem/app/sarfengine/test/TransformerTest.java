package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.sarfengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.sarfengine.conjugation.transformer.noun.TransformerFactory;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.HiddenNounStatus;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.conjugation.model.FormTemplate.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.util.AppUtil.NEW_LINE;

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
        lines.add(String.format(".%s", title));
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(addGenderHeader());
        lines.add(addNumberHeader());
        addRow(NOMINATIVE_SINGULAR, rootWords, 0);
        addRow(ACCUSATIVE_SINGULAR, rootWords, 6);
        addRow(GENITIVE_SINGULAR, rootWords, 12);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addRow(HiddenNounStatus status, RootWord[] rootWords, int initialIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(getRootWord(rootWords[initialIndex]));
        for (int i = initialIndex + 1; i < initialIndex + 6; i++) {
            builder.append(NEW_LINE).append(getRootWord(rootWords[i]));
        }
        builder.append(NEW_LINE).append(getStatusCaption(status)).append(NEW_LINE);
        lines.add(builder.toString());
    }

    private RootWord[] createRootWords(FormTemplate formTemplate, ArabicLetterType firstRadical,
                                       ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        RootWord rootWord = formTemplate.getActiveParticipleMasculineRoot();
        NounTransformer nounTransformer = transformerFactory.getMasculineEndingSoundTransformer();
        NounConjugationGroup conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 5);

        nounTransformer = transformerFactory.getMasculineDualTransformer();
        conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 4);

        nounTransformer = transformerFactory.getMasculinePluralTransformer();
        conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 3);

        rootWord = formTemplate.getActiveParticipleFeminineRoot();
        nounTransformer = transformerFactory.getFeminineEndingSoundTransformer();
        conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 2);

        nounTransformer = transformerFactory.getFeminineDualTransformer();
        conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 1);

        nounTransformer = transformerFactory.getFemininePluralTransformer();
        conjugationGroup = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, conjugationGroup, 0);

        return rootWords;
    }

    private void addRootWords(RootWord[] rootWords, NounConjugationGroup conjugationGroup, int initialIndex) {
        rootWords[initialIndex] = conjugationGroup.getNominative();
        rootWords[initialIndex + 6] = conjugationGroup.getAccusative();
        rootWords[initialIndex + 12] = conjugationGroup.getGenitive();
    }

}
