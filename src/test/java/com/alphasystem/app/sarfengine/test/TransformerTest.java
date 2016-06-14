package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.HiddenPronounStatus;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import com.alphasystem.morphologicalanalysis.wordbyword.model.support.NumberType;
import org.testng.annotations.Test;

import static com.alphasystem.app.morphologicalengine.conjugation.model.Form.*;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_II;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_FORM_IV;
import static com.alphasystem.morphologicalanalysis.wordbyword.model.support.NumberType.*;
import static java.lang.String.format;

/**
 * @author sali
 */
public class TransformerTest extends CommonTest {

    private static GuiceSupport guiceSupport = GuiceSupport.getInstance();

    @Test
    public void testConjugations() {
        addActiveTense(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
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
        addVerbalNoun();
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

    private void addVerbalNoun() {
        RootWord[] rootWords = new RootWord[18];
        createVerbalNounRootWords(rootWords, VERBAL_NOUN_FORM_IV, true, SEEN, LAM, MEEM);
        createVerbalNounRootWords(rootWords, VERBAL_NOUN_FORM_II, false, AIN, LAM, MEEM);
        addNounTable("Verbal Noun Form II & IV", rootWords);
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

        VerbRootBase pastTenseRoot = form.getPastTense();
        VerbRootBase presentTenseRoot = form.getPresentTense();

        ConjugationTuple conjugationTuple = getConjugation(pastTenseRoot, PAST_TENSE, THIRD_PERSON_MASCULINE_SINGULAR,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 5);

        conjugationTuple = getConjugation(presentTenseRoot, PRESENT_TENSE, THIRD_PERSON_MASCULINE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 2);

        conjugationTuple = getConjugation(pastTenseRoot, PAST_TENSE, THIRD_PERSON_FEMININE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 11);

        conjugationTuple = getConjugation(presentTenseRoot, PRESENT_TENSE, THIRD_PERSON_FEMININE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 8);

        conjugationTuple = getConjugation(pastTenseRoot, PAST_TENSE, SECOND_PERSON_MASCULINE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 17);

        conjugationTuple = getConjugation(presentTenseRoot, PRESENT_TENSE, SECOND_PERSON_MASCULINE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 14);

        conjugationTuple = getConjugation(pastTenseRoot, PAST_TENSE, SECOND_PERSON_FEMININE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 23);

        conjugationTuple = getConjugation(presentTenseRoot, PRESENT_TENSE, SECOND_PERSON_FEMININE_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 20);

        conjugationTuple = getConjugation(pastTenseRoot, PAST_TENSE, FIRST_PERSON_SINGULAR, firstRadical, secondRadical,
                thirdRadical);
        addRootWords(rootWords, conjugationTuple, 29);

        conjugationTuple = getConjugation(presentTenseRoot, PRESENT_TENSE, FIRST_PERSON_SINGULAR, firstRadical,
                secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 26);

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

    private void createVerbalNounRootWords(RootWord[] rootWords, VerbalNoun verbalNoun, boolean leftSide,
                                           ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                           ArabicLetterType thirdRadical) {
        NounConjugation nounConjugation = getConjugation(verbalNoun, SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 5 : 2);

        nounConjugation = getConjugation(verbalNoun, DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 4 : 1);

        nounConjugation = getConjugation(verbalNoun, PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 3 : 0);
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

    private static VerbTransformer getTransformer(SarfTermType termType, HiddenPronounStatus pronounStatus) {
        VerbTransformer verbTransformer = null;
        boolean past = PAST_TENSE.equals(termType) || PAST_PASSIVE_TENSE.equals(termType);
        switch (pronounStatus) {
            case THIRD_PERSON_MASCULINE_SINGULAR:
            case THIRD_PERSON_MASCULINE_DUAL:
            case THIRD_PERSON_MASCULINE_PLURAL:
                verbTransformer = past ? guiceSupport.getVerbTransformer(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER) :
                        guiceSupport.getVerbTransformer(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER);
                break;
            case THIRD_PERSON_FEMININE_SINGULAR:
            case THIRD_PERSON_FEMININE_DUAL:
            case THIRD_PERSON_FEMININE_PLURAL:
                verbTransformer = past ? guiceSupport.getVerbTransformer(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER) :
                        guiceSupport.getVerbTransformer(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER);
                break;
            case SECOND_PERSON_MASCULINE_SINGULAR:
            case SECOND_PERSON_MASCULINE_DUAL:
            case SECOND_PERSON_MASCULINE_PLURAL:
                verbTransformer = past ? guiceSupport.getVerbTransformer(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER) :
                        guiceSupport.getVerbTransformer(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER);
                break;
            case SECOND_PERSON_FEMININE_SINGULAR:
            case SECOND_PERSON_FEMININE_DUAL:
            case SECOND_PERSON_FEMININE_PLURAL:
                verbTransformer = past ? guiceSupport.getVerbTransformer(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER) :
                        guiceSupport.getVerbTransformer(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER);
                break;
            case FIRST_PERSON_SINGULAR:
            case FIRST_PERSON_PLURAL:
                verbTransformer = past ? guiceSupport.getVerbTransformer(PAST_TENSE_FIRST_PERSON_TRANSFORMER) :
                        guiceSupport.getVerbTransformer(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER);
                break;
        }
        return verbTransformer;
    }

    private NounConjugation getConjugation(NounSupport noun, NumberType numberType, ArabicLetterType firstRadical,
                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        NounTransformer nounTransformer = getTransformer(noun, numberType);
        return nounTransformer.doTransform(null, noun.getRootWord(), firstRadical, secondRadical, thirdRadical, null);
    }

    private ConjugationTuple getConjugation(VerbRootBase verbRootBase, SarfTermType termType, HiddenPronounStatus pronounStatus,
                                            ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        VerbTransformer verbTransformer = getTransformer(termType, pronounStatus);
        return verbTransformer.doTransform(null, verbRootBase.getRoot().getRootWord(), firstRadical, secondRadical, thirdRadical, null);
    }

}
