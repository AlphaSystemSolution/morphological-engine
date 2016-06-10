/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class BuilderTest extends CommonTest {

    private void printLabel(String src) {
        ArabicWord arabicWord = fromBuckWalterString(src);
        boolean empty = isEmpty(src);
        String arabicText = empty ? HTML_SPACE : format(ARABIC_TEXT_SPAN, arabicWord.toHtmlCode());
        String html = empty ? HTML_SPACE : format("%s<br/>%s", arabicText, src);
        log(format(TABLE_COLUMN, html));
    }

    private void printLabelRow(String... encodings) {
        if (isEmpty(encodings)) {
            return;
        }
        log(START_TABLE_ROW);
        for (String encoding : encodings) {
            printLabel(encoding);
        }
        log(END_TABLE_ROW);
    }

    @Test
    public void printLabels() {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        printLabelRow("SHyH", "sAlm", "mDAEf", "wzn");
        printLabelRow("fEl vlAvy mjrd", "fEl vlAvy mzyd fyh", "fEl rbAEy mjrd",
                "fEl rbAEy mzyd fyh");
        printLabelRow("mhmwz AlfA'", "mhmwz AlEyn", "mhmwz AlAm", "mEtl AlfA'");
        printLabelRow("mEtl AlEyn", "mEtl AlAm", "mvAl AlwAwy", "mvAl AlyA}y");
        printLabelRow("Ojwf AlwAwy", "Ojwf AlyA}y", "nAqS AlwAwy",
                "nAqS AlyA}y");
        printLabelRow("lfyf mfrwq", "lfyf mqrwn", null, null);
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void testConstructorCopy() {
        ArabicWord src = ArabicWord.fromBuckWalterString("yasotafoEilu");
        log(format("<div>Original Word: %s</div>", printArabicText(src)));
        ArabicWord target = new ArabicWord(src).replaceDiacritic(5, SUKUN);
        log(format("<div>Original Word after copy: %s</div>",
                printArabicText(src)));
        log(format("<div>New Word after changing: %s</div>",
                printArabicText(target)));
        RootWord srcRootWord = new RootWord().withRootWord(src)
                .withBaseWord(src).withFirstRadicalIndex(3)
                .withSecondRadicalIndex(4).withThirdRadicalIndex(5)
                .withSarfTermType(PRESENT_TENSE);
        log(format("<div>Original Root Word: %s</div>",
                printArabicText(srcRootWord.getRootWord())));
        RootWord targetRootWord = new RootWord(srcRootWord)
                .withRootWord(new ArabicWord(srcRootWord.getRootWord())
                        .replaceDiacritic(5, SUKUN));
        log(format("<div>Original Root Word: after copy %s</div>",
                printArabicText(srcRootWord.getRootWord())));
        log(format("<div>Target Root Word: %s</div>",
                printArabicText(targetRootWord.getRootWord())));
    }

}
