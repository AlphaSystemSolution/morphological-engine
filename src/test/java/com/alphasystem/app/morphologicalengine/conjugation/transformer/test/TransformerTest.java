package com.alphasystem.app.morphologicalengine.conjugation.transformer.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.test.CommonTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.spring.MainConfiguration;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.SAD;
import static com.alphasystem.arabic.model.HiddenNounStatus.ACCUSATIVE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenNounStatus.GENITIVE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenNounStatus.NOMINATIVE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.SECOND_PERSON_FEMININE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.SECOND_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_FEMININE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static java.lang.String.format;

/**
 * @author sali
 */
@ContextConfiguration(classes = {MainConfiguration.class})
public class TransformerTest extends CommonTest {

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PAST_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory pastTenseTransformerFactory;

    @Autowired
    @VerbTransformerFactoryType(VerbTransformerFactoryType.Type.PRESENT_TENSE_TRANSFORMER_FACTORY)
    private VerbTransformerFactory presentTenseTransformerFcatory;

    @Test(dataProvider = "tense_data")
    public void runTenseConjugations(VerbTransformerFactory pastTenseTransformer, VerbTransformerFactory presentTenseTransformer,
                                     Form form, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                     ArabicLetterType thirdRadical) {
        addActiveTense(pastTenseTransformer, presentTenseTransformer, form, firstRadical, secondRadical, thirdRadical);
    }

    /*@Test
    public void testConjugations() {
        addActiveParticiple(Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
        addActiveParticiple(Form.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, DDAD, RA, BA);
        addActiveParticiple(Form.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, FA, TA, HHA);
        addActiveParticiple(Form.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, SEEN, MEEM, AIN);
        addActiveParticiple(Form.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, HHA, SEEN, BA);
        addActiveParticiple(Form.FORM_I_CATEGORY_U_TEMPLATE, KAF, RA, MEEM);
        addActiveParticiple(Form.FORM_II_TEMPLATE, AIN, LAM, MEEM);
        addActiveParticiple(Form.FORM_III_TEMPLATE, JEEM, HA, DAL);
        addActiveParticiple(Form.FORM_IV_TEMPLATE, SEEN, LAM, MEEM);
        addActiveParticiple(Form.FORM_V_TEMPLATE, AIN, LAM, MEEM);
        addActiveParticiple(Form.FORM_VI_TEMPLATE, SEEN, HAMZA, LAM);
        addActiveParticiple(Form.FORM_VII_TEMPLATE, KAF, SEEN, RA);
        addActiveParticiple(Form.FORM_VIII_TEMPLATE, QAF, RA, BA);
        addActiveParticiple(Form.FORM_X_TEMPLATE, GHAIN, FA, RA);
        addPassiveParticiple(Form.FORM_X_TEMPLATE, NOON, QAF, DDAD);
        addVerbalNoun();
    }*/

    @DataProvider(name = "tense_data")
    private Object[][] createTenseData() {
        return new Object[][]{
                {pastTenseTransformerFactory, presentTenseTransformerFcatory, Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA}
        };
    }

    private void addActiveTense(VerbTransformerFactory pastTenseTransformer, VerbTransformerFactory presentTenseTransformer,
                                Form form, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createActiveTenseRootWords(pastTenseTransformer, presentTenseTransformer, form, firstRadical,
                secondRadical, thirdRadical);
        addVerbTable(form.name(), rootWords);
    }

    private void addActiveParticiple(NounTransformerFactory masculineTransformer, NounTransformerFactory feminineTransformer,
                                     Form formTemplate, ArabicLetterType firstRadical,
                                     ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createActiveParticipleRootWords(masculineTransformer, feminineTransformer, formTemplate,
                firstRadical, secondRadical, thirdRadical);
        addNounTable(formTemplate.name(), rootWords);
    }

    private void addPassiveParticiple(NounTransformer masculineTransformer, NounTransformer feminineTransformer,
                                      Form formTemplate, ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = createPassiveParticipleRootWords(masculineTransformer, feminineTransformer, formTemplate,
                firstRadical, secondRadical, thirdRadical);
        addNounTable(formTemplate.name(), rootWords);
    }

    /*private void addVerbalNoun() {
        RootWord[] rootWords = new RootWord[18];
        createVerbalNounRootWords(rootWords, VERBAL_NOUN_FORM_IV, true, SEEN, LAM, MEEM);
        createVerbalNounRootWords(rootWords, VERBAL_NOUN_FORM_II, false, AIN, LAM, MEEM);
        addNounTable("Verbal Noun Form II & IV", rootWords);
    }*/

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

    private RootWord[] createActiveTenseRootWords(VerbTransformerFactory pastTenseTransformer, VerbTransformerFactory presentTenseTransformer,
                                                  Form form, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                  ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[30];

        VerbRootBase pastTenseRoot = form.getPastTense();
        VerbRootBase presentTenseRoot = form.getPresentTense();

        ConjugationTuple conjugationTuple = getConjugation(pastTenseTransformer.thirdPersonMasculineTransformer(),
                pastTenseRoot, PAST_TENSE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 5);

        conjugationTuple = getConjugation(presentTenseTransformer.thirdPersonMasculineTransformer(), presentTenseRoot,
                PRESENT_TENSE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 2);

        conjugationTuple = getConjugation(pastTenseTransformer.thirdPersonFeminineTransformer(), pastTenseRoot, PAST_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 11);

        conjugationTuple = getConjugation(presentTenseTransformer.thirdPersonFeminineTransformer(), presentTenseRoot,
                PRESENT_TENSE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 8);

        conjugationTuple = getConjugation(pastTenseTransformer.secondPersonMasculineTransformer(), pastTenseRoot, PAST_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 17);

        conjugationTuple = getConjugation(presentTenseTransformer.secondPersonMasculineTransformer(), presentTenseRoot,
                PRESENT_TENSE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 14);

        conjugationTuple = getConjugation(pastTenseTransformer.secondPersonFeminineTransformer(), pastTenseRoot, PAST_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 23);

        conjugationTuple = getConjugation(presentTenseTransformer.secondPersonFeminineTransformer(), presentTenseRoot, PRESENT_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 20);

        conjugationTuple = getConjugation(pastTenseTransformer.firstPersonTransformer(), pastTenseRoot, PAST_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 29);

        conjugationTuple = getConjugation(presentTenseTransformer.firstPersonTransformer(), presentTenseRoot, PRESENT_TENSE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, conjugationTuple, 26);

        return rootWords;
    }

    private RootWord[] createActiveParticipleRootWords(NounTransformerFactory masculineTransformer,
                                                       NounTransformerFactory feminineTransformer, Form form,
                                                       ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                       ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        NounConjugation nounConjugation;

        NounRootBase rootBase = form.getActiveParticipleMasculine();
        nounConjugation = getConjugation(masculineTransformer.singularTransformer(), rootBase.getSingularBaseWord(),
                ACTIVE_PARTICIPLE_MASCULINE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 5);

        nounConjugation = getConjugation(masculineTransformer.dualTransformer(), rootBase.getDualBaseWord(),
                ACTIVE_PARTICIPLE_MASCULINE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 4);

        nounConjugation = getConjugation(masculineTransformer.pluralTransformer(), rootBase.getPluralBaseWord(),
                ACTIVE_PARTICIPLE_MASCULINE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 3);

        rootBase = form.getActiveParticipleFeminine();
        nounConjugation = getConjugation(feminineTransformer.singularTransformer(), rootBase.getSingularBaseWord(),
                ACTIVE_PARTICIPLE_FEMININE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 2);

        nounConjugation = getConjugation(feminineTransformer.dualTransformer(), rootBase.getDualBaseWord(),
                ACTIVE_PARTICIPLE_FEMININE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 1);

        nounConjugation = getConjugation(feminineTransformer.pluralTransformer(), rootBase.getPluralBaseWord(),
                ACTIVE_PARTICIPLE_FEMININE, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 0);

        return rootWords;
    }

    private RootWord[] createPassiveParticipleRootWords(NounTransformer masculineTransformer, NounTransformer feminineTransformer,
                                                        Form form, ArabicLetterType firstRadical,
                                                        ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        RootWord[] rootWords = new RootWord[18];

        NounConjugation nounConjugation;

        NounRootBase rootBase = form.getPassiveParticipleMasculine();
        nounConjugation = getConjugation(masculineTransformer, rootBase.getSingularBaseWord(), PASSIVE_PARTICIPLE_MASCULINE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 5);

        nounConjugation = getConjugation(masculineTransformer, rootBase.getDualBaseWord(), PASSIVE_PARTICIPLE_MASCULINE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 4);

        nounConjugation = getConjugation(masculineTransformer, rootBase.getPluralBaseWord(), PASSIVE_PARTICIPLE_MASCULINE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 3);

        rootBase = form.getPassiveParticipleFeminine();
        nounConjugation = getConjugation(feminineTransformer, rootBase.getSingularBaseWord(), PASSIVE_PARTICIPLE_FEMININE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 2);

        nounConjugation = getConjugation(feminineTransformer, rootBase.getDualBaseWord(), PASSIVE_PARTICIPLE_FEMININE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 1);

        nounConjugation = getConjugation(feminineTransformer, rootBase.getPluralBaseWord(), PASSIVE_PARTICIPLE_FEMININE,
                firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, 0);

        return rootWords;
    }

    /*private void createVerbalNounRootWords(RootWord[] rootWords, VerbalNoun verbalNoun, boolean leftSide,
                                           ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                           ArabicLetterType thirdRadical) {
        NounConjugation nounConjugation = getConjugation(verbalNoun, SINGULAR, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 5 : 2);

        nounConjugation = getConjugation(verbalNoun, DUAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 4 : 1);

        nounConjugation = getConjugation(verbalNoun, PLURAL, firstRadical, secondRadical, thirdRadical);
        addRootWords(rootWords, nounConjugation, leftSide ? 3 : 0);
    }*/

    private NounConjugation getConjugation(NounTransformer nounTransformer, NounSupport noun, SarfTermType termType, ArabicLetterType firstRadical,
                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return nounTransformer.doTransform(null, noun.getRootWord(), termType, firstRadical, secondRadical, thirdRadical, null);
    }

    private ConjugationTuple getConjugation(VerbTransformer verbTransformer, VerbRootBase verbRootBase, SarfTermType termType,
                                            ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return verbTransformer.doTransform(null, verbRootBase.getRoot().getRootWord(), termType, firstRadical, secondRadical, thirdRadical, null);
    }

}
