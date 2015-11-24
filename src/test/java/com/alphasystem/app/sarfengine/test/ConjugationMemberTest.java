package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.model.VerbalNoun;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    private MemberBuilderFactory factory = GuiceSupport.getInstance().getMemberBuilderFactory();
    private RuleProcessorFactory ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return processReplacements(src, firstRadical, secondRadical, thirdRadical, null);
    }

    @Test
    public void runConjugations() {
        runConjugations(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA, VerbalNoun.VERBAL_NOUN_V1.getRootWord());
        runConjugations(FORM_IV_TEMPLATE, SEEN, LAM, MEEM, VerbalNoun.VERBAL_NOUN_FORM_IV.getRootWord());
    }

//    @Test
//    public void testVerbalNouns() {
//        ConjugationMemberBuilder rightBuilder;
//        ConjugationMemberBuilder leftBuilder;
//        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleEngine(new RuleInfo());
//
//        VerbalNoun[] verbalNouns = VerbalNoun.values();
//        int index = 0;
//        while (index < 38) {
//            rightBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor,
//                    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, false, FA, AIN, LAM, verbalNouns[index++].getRootWord());
//            leftBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor,
//                    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, false, FA, AIN, LAM, verbalNouns[index++].getRootWord());
//            printConjugations(leftBuilder, rightBuilder, false);
//        }
//    }

    private void runConjugations(NamedTemplate namedTemplate, ArabicLetterType firstRadical,
                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical, RootWord verbalNoun) {
        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleEngine(new RuleInfo(namedTemplate));
        FormTemplate formTemplate = FormTemplate.getByNamedTemplate(namedTemplate);
        // Active Present and Past Tenses
        ConjugationMemberBuilder rightBuilder = factory.getTriLiteralPastTenseBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPastTenseRoot(), firstRadical, secondRadical, thirdRadical));
        ConjugationMemberBuilder leftBuilder = factory.getTriLiteralPresentTenseBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPresentTenseRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

        // Verbal noun
        leftBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor, false, verbalNoun);
        printConjugations(leftBuilder, null, true);

        // Active Participle Masculine and Feminine
        rightBuilder = factory.getTriLiteralActiveParticipleMasculineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getActiveParticipleMasculineRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralActiveParticipleFeminineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getActiveParticipleFeminineRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, false);

        // Passive Present and Past Tenses
        rightBuilder = factory.getTriLiteralPastPassiveBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPastPassiveTenseRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralPresentPassiveBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPresentPassiveTenseRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

        // Passive Participle Masculine and Feminine
        rightBuilder = factory.getTriLiteralPassiveParticipleMasculineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPassiveParticipleMasculineRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralPassiveParticipleFeminineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPassiveParticipleFeminineRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, false);

        // Imperative and Forbidding
        rightBuilder = namedTemplate.equals(FORM_IV_TEMPLATE) ?
                factory.getTriLiteralImperativeFormIVBuilder(ruleProcessor, false,
                        processReplacements(formTemplate.getImperativeRoot(), firstRadical, secondRadical, thirdRadical)) :
                factory.getTriLiteralImperativeBuilder(ruleProcessor, false,
                        processReplacements(formTemplate.getImperativeRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralForbiddingBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getForbiddingRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

    }

    private void printConjugations(ConjugationMemberBuilder leftBuilder, ConjugationMemberBuilder rightBuilder,
                                   boolean displayStatus) {
        RootWord[] leftSideRootWords = leftBuilder.doConjugation();
        SarfTermType leftTermType = leftBuilder.getTermType();
        RootWord[] rightSideRootWords = new RootWord[leftSideRootWords.length];
        SarfTermType rightTermType = null;
        if (rightBuilder != null) {
            rightSideRootWords = rightBuilder.doConjugation();
            rightTermType = rightBuilder.getTermType();
        }
        printTable(leftSideRootWords, rightSideRootWords, leftTermType, rightTermType, displayStatus);
    }

    private void printTable(RootWord[] leftSideRootWords, RootWord[] rightSideRootWords,
                            SarfTermType leftTermType, SarfTermType rightTermType, boolean displayStatus) {
        log(TABLE_DECLERATION_START);
        log("<col width=\"16%\"/>");
        log("<col width=\"16%\"/>");
        log("<col width=\"16%\"/>");
        log("<col width=\"4%\"/>");
        log("<col width=\"16%\"/>");
        log("<col width=\"16%\"/>");
        log("<col width=\"16%\"/>");

        log(TABLE_HEADER_DECLERATION_START);
        log(START_TABLE_TH_COLSPAN3);
        log(printArabicText(ARABIC_TEXT_CAPTION_SPAN, leftTermType.getLabel()));
        log(END_TABLE_TH);
        log(START_TABLE_TH);
        log(HTML_SPACE);
        log(END_TABLE_TH);
        log(START_TABLE_TH_COLSPAN3);
        if (rightTermType == null) {
            log(HTML_SPACE);
        } else {
            log(printArabicText(ARABIC_TEXT_CAPTION_SPAN, rightTermType.getLabel()));
        }

        log(END_TABLE_TH);
        log(TABLE_HEADER_DECLERATION_END);

        log(TABLE_BODY_DECLERATION_START);
        int start = 0;
        int end = NUM_OF_COLUMNS;
        while (start < leftSideRootWords.length) {
            log(START_TABLE_ROW);
            for (int i = start; i < end; i++) {
                RootWord rootWord = leftSideRootWords[i];
                log(START_TABLE_COLUMN);
                if (rootWord == null) {
                    log(HTML_SPACE);
                } else {
                    if (displayStatus) {
                        log(printArabicText(ARABIC_TEXT_SUP_SPAN, rootWord.getMemberType().getLabel()));
                    }
                    log(HTML_SPACE);
                    log(printArabicText(rootWord));
                }
                log(END_TABLE_COLUMN);
            }
            log(START_TABLE_COLUMN);
            log(HTML_SPACE);
            log(END_TABLE_COLUMN);
            for (int i = start; i < end; i++) {
                RootWord rootWord = rightSideRootWords[i];
                log(START_TABLE_COLUMN);
                if (rootWord == null) {
                    log(HTML_SPACE);
                } else {
                    if (displayStatus) {
                        log(printArabicText(ARABIC_TEXT_SUP_SPAN, rootWord.getMemberType().getLabel()));
                    }
                    log(HTML_SPACE);
                    log(printArabicText(rootWord));
                }
                log(END_TABLE_COLUMN);
            }
            log(END_TABLE_ROW);
            start = end;
            end += NUM_OF_COLUMNS;
        }

        log(TABLE_BODY_DECLERATION_END);

        log(TABLE_DECLERATION_END);
    }

}
