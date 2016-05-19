/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.arabic.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import org.apache.commons.lang3.StringEscapeUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static java.lang.String.format;
import static java.util.Collections.addAll;
import static java.util.Collections.reverse;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class BuilderTest extends CommonTest {

    private static String toHtmlCodeString(char unicode) {
        String s = format("%04x", (int) unicode);
        int i = Integer.parseInt(s, 16);
        return format("&#%s;", i);
    }

    @SuppressWarnings("unused")
    private static String toHtmlCodeString(String unicode) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < unicode.length(); i++) {
            char c = unicode.charAt(i);
            builder.append(toHtmlCodeString(c));
        }
        return builder.toString();
    }

    private void printLabel(String src) {
        ArabicWord arabicWord = fromBuckWalterString(src);
        boolean empty = isEmpty(src);
        String arabicText = empty ? "&nbsp;" : format(ARABIC_TEXT_SPAN, arabicWord.toHtmlCode());
        String html = empty ? "&nbsp;" : format("%s<br/>%s", arabicText, src);
        log(format("<td>%s</td>", html));
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

    private <M extends SarfMemberType> void printSrafMemberType(List<M> list, int numOfColumns) {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            final List<M> subList = list.subList(fromIndex, toIndex);
            reverse(subList);

            log(START_TABLE_ROW);
            subList.forEach(mt -> {
                String code = mt == null ? "&nbsp;" : mt.getMemberTermName();
                log(format("<td>%s</td>", code));
            });
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            subList.forEach(mt -> {
                String code = mt == null ? "&nbsp;" : mt.getMemberTermLabel().toHtmlCode();
                code = format(ARABIC_TEXT_SPAN, code);
                log(format("<td>%s</td>", code));
            });
            log(END_TABLE_ROW);

            fromIndex = toIndex;
            toIndex += numOfColumns;
        }

        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printNounStatus() {
        List<HiddenNounStatus> list = new ArrayList<>();
        addAll(list, HiddenNounStatus.values());
        printSrafMemberType(list, 3);
    }

    @Test
    public void prinProtNounStatus() {
        List<HiddenPronounStatus> list = new ArrayList<>();
        addAll(list, HiddenPronounStatus.values());
        list.add(list.size() - 1, null);
        printSrafMemberType(list, 3);
    }

    @Test
    public void printNamedTemplate() {
        List<NamedTemplate> list = new ArrayList<>();
        addAll(list, NamedTemplate.values());
        int numOfColumns = 5;
        while (list.size() % numOfColumns != 0) {
            list.add(null);
        }
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            List<NamedTemplate> subList = list.subList(fromIndex, toIndex);
            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = (namedTemplate == null) ? "&nbsp;" : namedTemplate.getCode();
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = (namedTemplate == null) ? "&nbsp;" : namedTemplate.getLabel().toHtmlCode();
                code = format(ARABIC_TEXT_SPAN, code);
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = (namedTemplate == null) ? "&nbsp;" : namedTemplate.getType().toHtmlCode();
                code = format(ARABIC_TEXT_SPAN, code);
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);
            fromIndex = toIndex;
            toIndex += numOfColumns;
        }

        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printSarfTermTypes() {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        for (SarfTermType sarfTermType : SarfTermType.values()) {
            log(START_TABLE_ROW);
            String value = StringEscapeUtils.escapeXml11(sarfTermType.value());
            ArabicWord aw = sarfTermType.getLabel();
            String arabicText = format(ARABIC_TEXT_SPAN, aw.toHtmlCode());
            log(format("<td>%s</td><td>%s</td><td>%s</td>",
                    sarfTermType.name(), value, arabicText));
            log(END_TABLE_ROW);
        }
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printVerbalNouns() {
        List<VerbalNoun> list = new ArrayList<>();
        addAll(list, VerbalNoun.values());
        int numOfColumns = 4;
        while (list.size() % numOfColumns != 0) {
            list.add(null);
        }
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            List<VerbalNoun> subList = list.subList(fromIndex, toIndex);

            log(START_TABLE_ROW);
            for (VerbalNoun verbalNounsContainer : subList) {
                String text = verbalNounsContainer == null ? "&nbsp;"
                        : verbalNounsContainer.name();
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (VerbalNoun verbalNounsContainer : subList) {
                RootWord rootWord = verbalNounsContainer == null ? null
                        : verbalNounsContainer.getRootWord();
                String text = rootWord == null ? "&nbsp;" : format(
                        ARABIC_TEXT_SPAN, rootWord.getRootWord().toHtmlCode());
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            fromIndex = toIndex;
            toIndex += numOfColumns;
        }
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
