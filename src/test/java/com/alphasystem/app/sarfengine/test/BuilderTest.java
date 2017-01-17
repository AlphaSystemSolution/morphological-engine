/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationHeaderBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationRoots;
import com.alphasystem.app.morphologicalengine.conjugation.model.ChartMode;
import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationHeader;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.QAF;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.testng.Assert.assertEquals;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class BuilderTest extends CommonTest {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();

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

    @Test
    public void testWordStatus() {
        WordStatus wordStatus = new WordStatus(new RootLetters(QAF, WAW, LAM));
        assertEquals(wordStatus.isHollow(), true);
        assertEquals(wordStatus.isWeak(), true);
    }

    @Test
    public void testConjugationHeader() {
        final Form form = Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
        RootLetters rootLetters = new RootLetters(QAF, WAW, LAM);
        final RuleProcessor ruleEngine = GUICE_SUPPORT.getRuleProcessorFactory().getRuleEngine(
                new RuleInfo(form.getTemplate(), rootLetters));
        ConjugationRoots conjugationRoots = new ConjugationRoots().template(form.getTemplate()).pastTense(form.getPastTense())
                .presentTense(form.getPresentTense());
        final ConjugationHeaderBuilder headerBuilder = GUICE_SUPPORT.getConjugationHeaderBuilder();
        final ConjugationHeader conjugationHeader = headerBuilder.createConjugationHeader(conjugationRoots, ruleEngine,
                rootLetters);
        final ChartMode chartMode = conjugationHeader.getChartMode();
        assertEquals(chartMode.getRootType(), RootType.WEAK);
        assertEquals(chartMode.getVerbType(), VerbType.SECOND_RADICAL_WEAK);
        assertEquals(chartMode.getWeakVerbType(), WeakVerbType.SECOND_RADICAL_WEAK_WAW);
    }

}
