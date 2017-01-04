/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.HiddenNounStatus;
import com.alphasystem.arabic.model.HiddenPronounStatus;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.arabic.model.ArabicWord.concatenateWithAnd;
import static com.alphasystem.arabic.model.ArabicWord.concatenateWithSpace;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_FEMININE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.util.AppUtil.NEW_LINE;
import static java.lang.String.format;
import static java.nio.file.Files.write;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.asciidoctor.SafeMode.UNSAFE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class CommonTest implements ArabicLetters, Constants {

    private static final String DEST_FOLDER = "C:\\Users\\sali\\git-hub\\AlphaSystemSolution\\morphological-engine\\build\\test-files";
    protected static Asciidoctor asciidoctor = Asciidoctor.Factory.create();
    protected List<String> lines;

    public static String getGenderCaption(HiddenPronounStatus status) {
        return format("[arabicTableCaption]#%s#", status.getGenderLabel().toHtmlCode());
    }

    public static String getNumberCaption(HiddenNounStatus status) {
        return format("[arabicTableCaptionSmall]#%s#", status.getNumberLabel().toHtmlCode());
    }

    public static String getStatusCaption(ArabicWord status) {
        return status == null ? "| " : format("|[arabicTableCaptionSmall]#%s#", status.toHtmlCode());
    }

    public static String getRootWord(RootWord... rootWords) {
        return getRootWord(0, rootWords);
    }

    public static String getRootWord(int columnSpan, RootWord... rootWords) {
        return getRootWord(columnSpan, null, rootWords);
    }

    public static String getRootWord(int columnSpan, ArabicWord prefix, RootWord... rootWords) {
        if (isEmpty(rootWords)) {
            return columnSpan <= 0 ? "|&nbsp; " : getEmptyRow(columnSpan);
        }
        RootWord rootWord = rootWords[0];
        if (rootWord == null) {
            return columnSpan <= 0 ? "|&nbsp; " : getEmptyRow(columnSpan);
        }
        ArabicWord arabicWord = rootWord.toLabel();
        for (int i = 1; i < rootWords.length; i++) {
            arabicWord = concatenateWithAnd(arabicWord, rootWords[i].toLabel());
        }
        if (prefix != null) {
            arabicWord = concatenateWithSpace(prefix, arabicWord);
        }
        return columnSpan <= 0 ? format("|[arabicNormal]#%s#", arabicWord.toHtmlCode()) :
                format("%s+|[arabicNormal]#%s#", columnSpan, arabicWord.toHtmlCode());
    }

    public static String getRootWordLabel(RootWord rootWord) {
        return (rootWord == null) ? "" : format("[arabicNormal]#%s#", rootWord.toLabel().toHtmlCode());
    }

    public static String getRootWordSarfTermType(RootWord rootWord) {
        SarfTermType sarfTermType = (rootWord == null) ? null : rootWord.getSarfTermType();
        return (sarfTermType == null) ? "" : format("[small]#%s#", sarfTermType.name());
    }

    public static String addGenderHeader() {
        return format("3+|%s .5+| 3+|%s .2+| %s", getGenderCaption(THIRD_PERSON_FEMININE_SINGULAR), getGenderCaption(THIRD_PERSON_MASCULINE_SINGULAR), NEW_LINE);
    }

    public static String getSarfTermTypeHeader(SarfTermType leftTerm, SarfTermType rightTerm, int numOfRows) {
        return getSarfTermTypeHeader(leftTerm, rightTerm, numOfRows, "3+|%s .%s+| 3+|%s .2+| %s");
    }

    public static String getSarfTermTypeHeader(SarfTermType leftTerm, SarfTermType rightTerm, int numOfRows, String format) {
        String leftTermCaption = format("[arabicTableCaption]#%s#", (leftTerm == null) ? HTML_SPACE : leftTerm.toLabel().toHtmlCode());
        String rightTermCaption = format("[arabicTableCaption]#%s#", (rightTerm == null) ? HTML_SPACE : rightTerm.toLabel().toHtmlCode());
        return format(format, leftTermCaption, numOfRows, rightTermCaption, NEW_LINE);
    }

    public static String addNumberHeader(boolean emptyLeftSide) {
        return format("|%s%s|%s%s|%s%s|%s%s|%s%s|%s%s", emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_PLURAL),
                NEW_LINE, emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_DUAL), NEW_LINE,
                emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_SINGULAR), NEW_LINE,
                getNumberCaption(NOMINATIVE_PLURAL), NEW_LINE, getNumberCaption(NOMINATIVE_DUAL), NEW_LINE,
                getNumberCaption(NOMINATIVE_SINGULAR), NEW_LINE);
    }

    public static void addRootWords(RootWord[] rootWords, NounConjugation nounConjugation, int initialIndex) {
        rootWords[initialIndex] = (nounConjugation == null) ? null : nounConjugation.getNominative();
        rootWords[initialIndex + 6] = (nounConjugation == null) ? null : nounConjugation.getAccusative();
        rootWords[initialIndex + 12] = (nounConjugation == null) ? null : nounConjugation.getGenitive();
    }

    public static void addRootWords(RootWord[] rootWords, ConjugationTuple conjugationTuple, int initialIndex) {
        rootWords[initialIndex] = (conjugationTuple == null) ? null : conjugationTuple.getSingular();
        rootWords[initialIndex - 1] = (conjugationTuple == null) ? null : conjugationTuple.getDual();
        rootWords[initialIndex - 2] = (conjugationTuple == null) ? null : conjugationTuple.getPlural();
    }

    public static void addRow(List<String> lines, ArabicWord status, RootWord[] rootWords, int initialIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(getRootWord(rootWords[initialIndex]));
        for (int i = initialIndex + 1; i < initialIndex + 6; i++) {
            builder.append(NEW_LINE).append(getRootWord(rootWords[i]));
        }
        builder.append(NEW_LINE).append(getStatusCaption(status)).append(NEW_LINE);
        lines.add(builder.toString());
    }

    static String getEmptyRow(int numOfColumns) {
        return format("%s+| ", numOfColumns);
    }

    public static String printArabicText(String format, ArabicWord src) {
        return format(format, src.toHtmlCode());
    }

    public static String printArabicText(ArabicWord src) {
        return printArabicText(ARABIC_TEXT_SPAN, src);
    }

    @AfterMethod
    public void afterMethod(Method method) {
        log(format("<div>End of test %s</div>", method.getName()));
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        log("<p><hr/>");
        log(format("<div>Start of test %s</div>", method.getName()));
    }

    @BeforeClass
    public void beforeClass() {
        lines = new ArrayList<>();
        lines.add(format("= %s", getClass().getSimpleName()));
        lines.add(":encoding: utf-8");
        lines.add(":lang: en");
        lines.add(":numbered!:");
        lines.add(":stylesdir: css");
        lines.add(":linkcss:");
        lines.add(":experimental:");
        lines.add(":toc!:");
        lines.add(":table-caption!:");
        lines.add(":compact:");
        lines.add(":docinfo2:");
        lines.add(":last-update-label!:");
        lines.add(":safe-mode-name: UNSAFE");
        lines.add("");
    }

    @AfterClass
    public void afterClass() {
        try {
            final Path path = Paths.get(DEST_FOLDER, format("%s.adoc", getClass().getSimpleName()));
            write(path, lines);
            convert(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convert(Path srcPath) {
        AttributesBuilder attributesBuilder = AttributesBuilder.attributes().stylesDir("css").linkCss(true);
        final Path parent = srcPath.getParent();
        final Path destPath = Paths.get(parent.toString(), format("%s.html", getClass().getSimpleName()));
        OptionsBuilder optionsBuilder = OptionsBuilder.options().baseDir(parent.toFile()).inPlace(true).safe(UNSAFE)
                .toFile(destPath.toFile()).attributes(attributesBuilder);
        asciidoctor.convertFile(srcPath.toFile(), optionsBuilder);
    }
}
