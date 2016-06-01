/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.model.*;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.*;
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

import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_FEMININE_SINGULAR;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.util.AppUtil.NEW_LINE;
import static java.lang.String.format;
import static java.nio.file.Files.write;
import static org.asciidoctor.SafeMode.UNSAFE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class CommonTest implements ArabicLetters, Constants {

    private static final String DEST_FOLDER = "C:\\Users\\sali\\git-hub\\AlphaSystemSolution\\morphological-engine\\build\\test-files";
    protected static GuiceSupport guiceSupport = GuiceSupport.getInstance();
    protected static Asciidoctor asciidoctor = Asciidoctor.Factory.create();
    protected List<String> lines;

    public static String getGenderCaption(HiddenPronounStatus status) {
        return format("[arabicTableCaption]#%s#", status.getGenderLabel().toHtmlCode());
    }

    public static String getNumberCaption(HiddenNounStatus status) {
        return format("[arabicTableCaption]#%s#", status.getNumberLabel().toHtmlCode());
    }

    public static String getStatusCaption(HiddenNounStatus status) {
        return status == null ? "| " : format("|[arabicTableCaption]#%s#", status.getStatus().toHtmlCode());
    }

    public static String getRootWord(RootWord rootWord) {
        return (rootWord == null) ? "| " : format("|[arabicNormal]#%s#", rootWord.getLabel().toHtmlCode());
    }

    public static String addGenderHeader() {
        return format("3+|%s .5+| 3+|%s .2+| %s", getGenderCaption(THIRD_PERSON_FEMININE_SINGULAR), getGenderCaption(THIRD_PERSON_MASCULINE_SINGULAR), NEW_LINE);
    }

    public static String getSarfTermTypeHeader(SarfTermType leftTerm, SarfTermType rightTerm) {
        String leftTermCaption = format("[arabicTableCaption]#%s#", (leftTerm == null) ? HTML_SPACE : leftTerm.getLabel().toHtmlCode());
        String rightTermCaption = format("[arabicTableCaption]#%s#", (rightTerm == null) ? HTML_SPACE : rightTerm.getLabel().toHtmlCode());
        return format("3+|%s .5+| 3+|%s .2+| %s", leftTermCaption, rightTermCaption, NEW_LINE);
    }

    public static String addNumberHeader(boolean emptyLeftSide) {
        return format("|%s%s|%s%s|%s%s|%s%s|%s%s|%s%s", emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_PLURAL),
                NEW_LINE, emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_DUAL), NEW_LINE,
                emptyLeftSide ? HTML_SPACE : getNumberCaption(NOMINATIVE_SINGULAR), NEW_LINE,
                getNumberCaption(NOMINATIVE_PLURAL), NEW_LINE, getNumberCaption(NOMINATIVE_DUAL), NEW_LINE,
                getNumberCaption(NOMINATIVE_SINGULAR), NEW_LINE);
    }

    public static void addRootWords(RootWord[] rootWords, NounConjugation nounConjugation, int initialIndex) {
        rootWords[initialIndex] = nounConjugation == null ? null : nounConjugation.getNominative();
        rootWords[initialIndex + 6] = nounConjugation == null ? null : nounConjugation.getAccusative();
        rootWords[initialIndex + 12] = nounConjugation == null ? null : nounConjugation.getGenitive();
    }

    public static void addRow(List<String> lines, HiddenNounStatus status, RootWord[] rootWords, int initialIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(getRootWord(rootWords[initialIndex]));
        for (int i = initialIndex + 1; i < initialIndex + 6; i++) {
            builder.append(NEW_LINE).append(getRootWord(rootWords[i]));
        }
        builder.append(NEW_LINE).append(getStatusCaption(status)).append(NEW_LINE);
        lines.add(builder.toString());
    }

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
