package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import com.alphasystem.morphologicalanalysis.wordbyword.model.support.NumberType;
import org.testng.annotations.Test;

import static com.alphasystem.app.morphologicalengine.conjugation.model.Form.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_II;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_III_V1;
import static com.alphasystem.morphologicalanalysis.wordbyword.model.support.NumberType.*;
import static java.lang.String.format;

/**
 * @author sali
 */
public class TransformerTest extends CommonTest {

    private static GuiceSupport guiceSupport = GuiceSupport.getInstance();

    @Test
    public void testConjugations() {
        addActiveParticiple(Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
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
    }

    @SuppressWarnings({"unused"})
    private void unused() {
        addActiveTense(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);


        addVerbalNoun("Verbal Noun Form II & III", VERBAL_NOUN_FORM_III_V1, VERBAL_NOUN_FORM_II, AIN, LAM, MEEM);
    }

    private void addActiveTense(Form form, ArabicLetterType firstRadical,
                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createActiveTenseRootWords(form, firstRadical, secondRadical, thirdRadical);
        addVerbTable(form.name(), rootWords);
    }

    private void addActiveParticiple(Form formTemplate, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createActiveParticipleRootWords(formTemplate, firstRadical, secondRadical, thirdRadical);
        addNounTable(formTemplate.name(), rootWords);
    }

    private void addVerbalNoun(String title, VerbalNoun leftSideWord, VerbalNoun rightSideWord,
                               ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createVerbalNounRootWords(leftSideWord, rightSideWord, firstRadical, secondRadical, thirdRadical);
        addNounTable(title, rootWords);
    }

    private void addVerbTable(String title, RootWord... rootWords) {
        lines.add(format(".%s", title));
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(getSarfTermTypeHeader(PRESENT_TENSE, PAST_TENSE, 7));
        lines.add(addNumberHeader(false));
        addRow(lines, THIRD_PERSON_MASCULINE_SINGULAR.getGenderAndConversation(), rootWords, 0);
        addRow(lines, THIRD_PERSON_FEMININE_SINGULAR.getGenderAndConversation(), rootWords, 6);
        addRow(lines, SECOND_PERSON_MASCULINE_SINGULAR.getGenderAndConversation(), rootWords, 12);
        addRow(lines, SECOND_PERSON_FEMININE_SINGULAR.getGenderAndConversation(), rootWords, 18);
        addRow(lines, FIRST_PERSON_SINGULAR.getGenderAndConversation(), rootWords, 24);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addNounTable(String title, RootWord... rootWords) {
        lines.add(format(".%s", title));
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(addGenderHeader());
        lines.add(addNumberHeader(false));
        addRow(lines, NOMINATIVE_SINGULAR.getStatus(), rootWords, 0);
        addRow(lines, ACCUSATIVE_SINGULAR.getStatus(), rootWords, 6);
        addRow(lines, GENITIVE_SINGULAR.getStatus(), rootWords, 12);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private RootWord[] createActiveTenseRootWords(Form form, ArabicLetterType firstRadical,
                                                  ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[30];

        /*final RootWord pastTenseRoot = form.getPastTenseRoot();
        final RootWord presentTenseRoot = form.getPresentTenseRoot();
        VerbTransformer verbTransformer = verbTransformerFactory.getPastTenseThirdPersonMasculineTransformer(null);
        VerbConjugation verbConjugation = verbTransformer.doTransform(pastTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 5);

        verbTransformer = verbTransformerFactory.getPresentTenseThirdPersonMasculineTransformer(null);
        verbConjugation = verbTransformer.doTransform(presentTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 2);

        verbTransformer = verbTransformerFactory.getPastTenseThirdPersonFeminineTransformer(null);
        verbConjugation = verbTransformer.doTransform(pastTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 11);

        verbTransformer = verbTransformerFactory.getPresentTenseThirdPersonFeminineTransformer(null);
        verbConjugation = verbTransformer.doTransform(presentTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 8);

        verbTransformer = verbTransformerFactory.getPastTenseSecondPersonMasculineTransformer(null);
        verbConjugation = verbTransformer.doTransform(pastTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 17);

        verbTransformer = verbTransformerFactory.getPresentTenseSecondPersonMasculineTransformer(null);
        verbConjugation = verbTransformer.doTransform(presentTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 14);

        verbTransformer = verbTransformerFactory.getPastTenseSecondPersonFeminineTransformer(null);
        verbConjugation = verbTransformer.doTransform(pastTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 23);

        verbTransformer = verbTransformerFactory.getPresentTenseSecondPersonFeminineTransformer(null);
        verbConjugation = verbTransformer.doTransform(presentTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 20);

        verbTransformer = verbTransformerFactory.getPastTenseFirstPersonTransformer(null);
        verbConjugation = verbTransformer.doTransform(pastTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 29);

        verbTransformer = verbTransformerFactory.getPresentTenseFirstPersonTransformer(null);
        verbConjugation = verbTransformer.doTransform(presentTenseRoot, firstRadical, secondRadical, thirdRadical, null);
        addRootWords(rootWords, verbConjugation, 26);*/

        return rootWords;
    }

    private RootWord[] createActiveParticipleRootWords(Form form, ArabicLetterType firstRadical,
                                                       ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        NounConjugation nounConjugation;

        NounRootBase rootBase = form.getActiveParticipleMasculine();
        nounConjugation = getConjugation(rootBase.getSingularBaseWord(), SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 5);

        nounConjugation = getConjugation(rootBase.getDualBaseWord(), DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 4);

        nounConjugation = getConjugation(rootBase.getPluralBaseWord(), PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 3);

        rootBase = form.getActiveParticipleFeminine();
        nounConjugation = getConjugation(rootBase.getSingularBaseWord(), SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 2);

        nounConjugation = getConjugation(rootBase.getDualBaseWord(), DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 1);

        nounConjugation = getConjugation(rootBase.getPluralBaseWord(), PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 0);

        return rootWords;
    }

    private RootWord[] createVerbalNounRootWords(VerbalNoun leftSideWord, VerbalNoun rightSideWord,
                                                 ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                 ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        NounConjugation nounConjugation = getConjugation(leftSideWord, SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 5);

        nounConjugation = getConjugation(leftSideWord, DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 4);

        nounConjugation = getConjugation(leftSideWord, PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 3);

        nounConjugation = getConjugation(rightSideWord, SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 2);

        nounConjugation = getConjugation(rightSideWord, DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 1);

        nounConjugation = getConjugation(rightSideWord, PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 0);

        return rootWords;
    }

    private static NounTransformer getTransformer(NounSupport noun, NumberType numberType) {
        NounTransformer nounTransformer = null;
        switch (numberType) {
            case SINGULAR:
                nounTransformer = guiceSupport.getNounTransformer(noun.getSingularRootName());
                break;
            case DUAL:
                nounTransformer = guiceSupport.getNounTransformer(noun.getDualRootName());
                break;
            case PLURAL:
                nounTransformer = guiceSupport.getNounTransformer(noun.getPluralRootName());
                break;
        }
        return nounTransformer;
    }

    private NounConjugation getConjugation(NounSupport noun, NumberType numberType, ArabicLetterType firstRadical,
                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        NounTransformer nounTransformer = getTransformer(noun, numberType);
        return nounTransformer.doTransform(null, noun.getRootWord(), firstRadical, secondRadical, thirdRadical, null);
    }

}
