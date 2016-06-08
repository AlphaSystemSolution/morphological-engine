/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.util.PatternHelper;
import com.alphasystem.arabic.model.ArabicWord;
import org.testng.annotations.Test;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor.REMOVE_MARKER;
import static com.alphasystem.app.morphologicalengine.util.PatternHelper.*;
import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static java.lang.String.format;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class PatternHelperTest extends CommonTest {

    private static void printResult(ArabicWord src, ArabicWord result) {
        log(format("<div>Source: %s, Result: %s</div>", printArabicText(src),
                printArabicText(result)));
    }

    private static void printResult(String src, String result) {
        printResult(fromBuckWalterString(src), fromBuckWalterString(result));
    }

    @Test
    public void applyRule3Test() {
        ArabicWord arabicWord = new ArabicWord(YA_WITH_DAMMA, QAF_WITH_KASRA,
                WAW_WITH_SUKUN, MEEM_WITH_DAMMA);
        String src = arabicWord.toBuckWalter();
        String result = PatternHelper.applyRule3(src);
        printResult(src, result);
    }

    @Test
    public void dropAlifBeforeSakinTest1() {
        ArabicWord arabicWord = new ArabicWord(DAL_WITH_FATHA, AIN_WITH_FATHA,
                LETTER_ALIF, WAW_WITH_SUKUN, LETTER_ALIF);
        String src = arabicWord.toBuckWalter();
        String result = dropAlifBeforeSakin(src);
        printResult(src, result);
    }

    @Test
    public void dropAlifBeforeSakinTest2() {
        ArabicWord arabicWord = new ArabicWord(TA_WITH_FATHA, KHA_WITH_SUKUN,
                SHEEN_WITH_FATHA, LETTER_ALIF, WAW_WITH_SUKUN, NOON_WITH_FATHA);
        String src = arabicWord.toBuckWalter();
        String result = dropAlifBeforeSakin(src);
        printResult(src, result);
    }

    @Test
    public void dropAlifBeforeSakinTest3() {
        ArabicWord arabicWord = new ArabicWord(TA_WITH_FATHA, KHA_WITH_SUKUN,
                SHEEN_WITH_FATHA, LETTER_ALIF, YA_WITH_SUKUN, NOON_WITH_FATHA);
        String src = arabicWord.toBuckWalter();
        String result = dropAlifBeforeSakin(src);
        printResult(src, result);
    }

    @Test
    public void dropAlifBeforeSakinTest4() {
        ArabicWord arabicWord = new ArabicWord(TA_WITH_FATHA, KHA_WITH_FATHA,
                LETTER_ALIF, FA_WITH_SUKUN);
        String src = arabicWord.toBuckWalter();
        String result = dropAlifBeforeSakin(src);
        printResult(src, result);
    }

    @Test
    public void removeMarkerTest() {
        ArabicWord arabicWord = new ArabicWord(YA_WITH_FATHA, REMOVE_MARKER, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        String src = arabicWord.toBuckWalter();
        String result = removeMarker(src);
        printResult(src, result);

        arabicWord = new ArabicWord(YA_WITH_FATHA, REMOVE_MARKER, REMOVE_MARKER, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        src = arabicWord.toBuckWalter();
        result = removeMarker(src);
        printResult(src, result);

        arabicWord = new ArabicWord(YA_WITH_FATHA, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        src = arabicWord.toBuckWalter();
        result = removeMarker(src);
        printResult(src, result);
    }

    @Test
    public void removeTatweelTest() {
        ArabicWord arabicWord = new ArabicWord(YA_WITH_FATHA, LETTER_TATWEEL, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        String src = arabicWord.toBuckWalter();
        String result = removeMarker(src);
        printResult(src, result);

        arabicWord = new ArabicWord(YA_WITH_FATHA, LETTER_TATWEEL, LETTER_TATWEEL, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        src = arabicWord.toBuckWalter();
        result = removeMarker(src);
        printResult(src, result);

        arabicWord = new ArabicWord(YA_WITH_FATHA, JEEM_WITH_KASRA, DAL_WITH_FATHA);
        src = arabicWord.toBuckWalter();
        result = removeMarker(src);
        printResult(src, result);
    }

    @Test
    public void repeatPatternTest() {
        ArabicWord arabicWord = new ArabicWord(YA_WITH_FATHA, YA_WITH_SUKUN,
                MEEM_WITH_DAMMA, NOON_WITH_SUKUN, NOON_WITH_FATHA);
        String src = arabicWord.toBuckWalter();
        String result = mergeRepeats(src);
        printResult(src, result);
    }

    @Test
    public void replaceConsecutiveHamzahTest() {
        ArabicWord arabicWord = new ArabicWord(HAMZA_WITH_KASRA,
                HAMZA_WITH_SUKUN, MEEM_WITH_FATHA, LETTER_ALIF,
                NOON_WITH_FATHATAN, LETTER_ALIF);
        String src = arabicWord.toBuckWalter();
        String result = replaceConsecutiveHamzah(src);
        printResult(src, result);
    }

    @Test
    public void replaceConsecutiveHamzahTest2() {
        ArabicWord arabicWord = new ArabicWord(ALIF_HAMZA_BELOW_WITH_KASRA,
                ALIF_HAMZA_ABOVE_WITH_SUKUN, MEEM_WITH_FATHA, LETTER_ALIF,
                NOON_WITH_FATHATAN, LETTER_ALIF);
        String src = arabicWord.toBuckWalter();
        String result = replaceConsecutiveHamzah(src);
        printResult(src, result);
    }

}
