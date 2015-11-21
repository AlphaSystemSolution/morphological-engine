package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    private MemberBuilderFactory factory = GuiceSupport.getInstance().getMemberBuilderFactory();

    @Test
    public void runConjugations() {
        runConjugations(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA);
    }

    private void runConjugations(NamedTemplate namedTemplate, ArabicLetterType firstRadical,
                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        // Active Present and Past Tenses
        ConjugationMemberBuilder rightBuilder = factory.getTriLiteralPastTenseBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        ConjugationMemberBuilder leftBuilder = factory.getTriLiteralPresentTenseBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        printConjugations(leftBuilder, rightBuilder);

        // Active Participle Masculine and Feminine
        rightBuilder = factory.getTriLiteralActiveParticipleMasculineBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        leftBuilder = factory.getTriLiteralActiveParticipleFeminineBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        printConjugations(leftBuilder, rightBuilder);

        // Passive Present and Past Tenses
        rightBuilder = factory.getTriLiteralPastPassiveBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        leftBuilder = factory.getTriLiteralPresentPassiveBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        printConjugations(leftBuilder, rightBuilder);

        // Imperative and Forbidding
        rightBuilder = factory.getTriLiteralImperativeBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical, null);
        leftBuilder = factory.getTriLiteralForbiddingBuilder(namedTemplate, false,
                firstRadical, secondRadical, thirdRadical);
        printConjugations(leftBuilder, rightBuilder);

    }

    private void printConjugations(ConjugationMemberBuilder leftBuilder, ConjugationMemberBuilder rightBuilder) {
        printTable(leftBuilder.doConjugation(), rightBuilder.doConjugation(), leftBuilder.getTermType(),
                rightBuilder.getTermType());
    }

    private void printTable(RootWord[] leftSideRootWords, RootWord[] rightSideRootWords,
                            SarfTermType leftTermType, SarfTermType rigtTermType) {
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
        log(printArabicText(ARABIC_TEXT_CAPTION_SPAN, rigtTermType.getLabel()));
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
                    log(printArabicText(ARABIC_TEXT_SUP_SPAN, rootWord.getMemberType().getLabel()));
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
                    log(printArabicText(ARABIC_TEXT_SUP_SPAN, rootWord.getMemberType().getLabel()));
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
