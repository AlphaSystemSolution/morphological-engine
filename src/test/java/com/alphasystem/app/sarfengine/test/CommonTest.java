/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationStack;
import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.app.sarfengine.conjugation.model.SarfKabeer;
import com.alphasystem.app.sarfengine.conjugation.model.SarfKabeerPair;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static java.lang.String.format;
import static org.testng.Reporter.log;

;

/**
 * @author sali
 */
public class CommonTest extends Assert implements ArabicLetters, Constants {

    public static String printArabicText(ArabicLetter src) {
        return format(ARABIC_TEXT_SPAN, src.toHtmlCode());
    }

    public static String printArabicText(String format, ArabicWord src) {
        return format(format, src.toHtmlCode());
    }

    public static String printArabicText(ArabicWord src) {
        return printArabicText(ARABIC_TEXT_SPAN, src);
    }

    public static String printArabicText(RootWord src) {
        return printArabicText(src.getRootWord());
    }

    public static void printTable(RootWord[] leftSideRootWords, RootWord[] rightSideRootWords,
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

    public static void printConjugation(SarfChart sarfChart) {
        SarfKabeer sarfKabeer = sarfChart.getSarfKabeer();
        SarfKabeerPair sarfKabeerPair = sarfKabeer.getActiveTensePair();

        ConjugationStack leftSideStack = sarfKabeerPair.getLeftSideStack();
        ConjugationStack rightSideStack = sarfKabeerPair.getRightSideStack();
        printTable(leftSideStack.getConjugations(), rightSideStack.getConjugations(), leftSideStack.getLabel(),
                rightSideStack.getLabel(), true);

        sarfKabeerPair = sarfKabeer.getActiveParticiplePair();
        leftSideStack = sarfKabeerPair.getLeftSideStack();
        rightSideStack = sarfKabeerPair.getRightSideStack();
        printTable(leftSideStack.getConjugations(), rightSideStack.getConjugations(), leftSideStack.getLabel(),
                rightSideStack.getLabel(), false);
    }

    public static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                               ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                               ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    public static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                               ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return processReplacements(src, firstRadical, secondRadical, thirdRadical, null);
    }

    @AfterMethod
    public void afterMethod(Method method) {
        log(format("<div>End of test %s</div>", method.getName()));
        log("<hr/></p><br/>");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        log("<p><hr/>");
        log(format("<div>Start of test %s</div>", method.getName()));
    }
}
