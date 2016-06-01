package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.TransformerFactory;
import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugation;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.conjugation.model.FormTemplate.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_II;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_III_V1;
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
        addActiveParticiple(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
        addActiveParticiple(FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, DDAD, RA, BA);
        addActiveParticiple(FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, FA, TA, HHA);
        addActiveParticiple(FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, SEEN, MEEM, AIN);
        addActiveParticiple(FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, HHA, SEEN, BA);
        addActiveParticiple(FORM_I_CATEGORY_U_TEMPLATE, KAF, RA, MEEM);
        addActiveParticiple(FORM_II_TEMPLATE, AIN, LAM, MEEM);
        addActiveParticiple(FORM_III_TEMPLATE, JEEM, HA, DAL);
        addActiveParticiple(FORM_IV_TEMPLATE, SEEN, LAM, MEEM);
        addActiveParticiple(FORM_V_TEMPLATE, AIN, LAM, MEEM);
        addActiveParticiple(FORM_VI_TEMPLATE, SEEN, HAMZA, LAM);
        addActiveParticiple(FORM_VII_TEMPLATE, KAF, SEEN, RA);
        addActiveParticiple(FORM_VIII_TEMPLATE, QAF, RA, BA);
        addActiveParticiple(FORM_X_TEMPLATE, GHAIN, FA, RA);

        addVerbalNoun("Verbal Noun Form II & III", VERBAL_NOUN_FORM_III_V1, VERBAL_NOUN_FORM_II, AIN, LAM, MEEM);
    }

    private void addActiveParticiple(FormTemplate formTemplate, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createActiveParticipleRootWords(formTemplate, firstRadical, secondRadical, thirdRadical);
        addTable(formTemplate.name(), rootWords);
    }

    private void addVerbalNoun(String title, VerbalNoun leftSideWord, VerbalNoun rightSideWord,
                               ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createVerbalNounRootWords(leftSideWord, rightSideWord, firstRadical, secondRadical, thirdRadical);
        addTable(title, rootWords);
    }

    private void addTable(String title, RootWord... rootWords) {
        lines.add(format(".%s", title));
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(addGenderHeader());
        lines.add(addNumberHeader(false));
        addRow(lines, NOMINATIVE_SINGULAR, rootWords, 0);
        addRow(lines, ACCUSATIVE_SINGULAR, rootWords, 6);
        addRow(lines, GENITIVE_SINGULAR, rootWords, 12);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private RootWord[] createActiveParticipleRootWords(FormTemplate formTemplate, ArabicLetterType firstRadical,
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

    private RootWord[] createVerbalNounRootWords(VerbalNoun leftSideWord, VerbalNoun rightSideWord,
                                                 ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                 ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        RootWord rootWord = leftSideWord.getRootWord();
        NounTransformer nounTransformer = transformerFactory.getMasculineEndingSoundTransformer(null);
        NounConjugation nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 5);

        nounTransformer = transformerFactory.getMasculineDualTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 4);

        nounTransformer = transformerFactory.getFeminineMasculineBasedPluralTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 3);

        rootWord = rightSideWord.getRootWord();
        nounTransformer = transformerFactory.getMasculineEndingSoundTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 5);

        nounTransformer = transformerFactory.getMasculineDualTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 4);

        nounTransformer = transformerFactory.getFeminineMasculineBasedPluralTransformer(null);
        nounConjugation = nounTransformer.doTransform(rootWord, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, nounConjugation, 3);

        return rootWords;
    }

}
