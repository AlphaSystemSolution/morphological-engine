/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory.getConjugationBuilder;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_VI_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_X_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_TENSE;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.ArrayUtils.subarray;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class ConjugationTest extends CommonTest {

    private static final int NUM_OF_COLUMNS = 3;

    private void conjugateSarfTermType(ConjugationBuilder builder,
                                       SarfTermType sarfTermType, boolean skipRuleProcessing,
                                       ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                       ArabicLetterType thirdRadical) {
        printSarfTermType(sarfTermType);
        ConjugationMember[] conjugations = doConjugation(builder, skipRuleProcessing,
                sarfTermType, firstRadical, secondRadical, thirdRadical);
        printTable(conjugations);
    }

    private ConjugationMember[] doConjugation(ConjugationBuilder builder,
                                              boolean skipRuleProcessing, SarfTermType memberType,
                                              ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                              ArabicLetterType thirdRadical) {
        ConjugationMemberBuilder memberBuilder = builder.getMemberBuilder(
                skipRuleProcessing, memberType, firstRadical, secondRadical,
                thirdRadical);
        return memberBuilder.doConjugation();
    }

    private void print(NamedTemplate template, boolean skipRuleProcessing,
                       ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                       ArabicLetterType thirdRadical) {
        String templateName = format(ARABIC_TEXT_SPAN, template.getLabel()
                .toHtmlCode());
        String skipRuleProcessingPrefix = skipRuleProcessing ? "with"
                : "without";
        log(format(
                "<div>Conjugation for %s (%s)  %s skipping rule processing.<br/>%s</div>",
                template, templateName, skipRuleProcessingPrefix,
                printRootLetters(firstRadical, secondRadical, thirdRadical)));
    }

    String printRootLetters(ArabicLetterType firstRadical,
                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return new StringBuilder()
                .append(format(ARABIC_TEXT_SPAN, firstRadical.getHtmlCode()))
                .append("&nbsp;")
                .append(format(ARABIC_TEXT_SPAN, secondRadical.getHtmlCode()))
                .append("&nbsp;")
                .append(format(ARABIC_TEXT_SPAN, thirdRadical.getHtmlCode()))
                .toString();
    }

    void printSarfTermType(SarfTermType sarfTermType) {
        String label = format(ARABIC_TEXT_SPAN, sarfTermType.getLabel()
                .toHtmlCode());
        log(format("<div>%s (%s)</div>", sarfTermType, label));
    }

    void printTable(ConjugationMember[] conjugations) {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        int startIndexInclusive = 0;
        int endIndexExclusive = NUM_OF_COLUMNS;
        int length = conjugations.length;
        while (startIndexInclusive < length) {
            ConjugationMember[] subarray = subarray(conjugations, startIndexInclusive,
                    endIndexExclusive);
            log(START_TABLE_ROW);
            for (ConjugationMember arabicWord : subarray) {
                String text = format(ARABIC_TEXT_SPAN, arabicWord.getConjugation().toHtmlCode());
                log(format("%s%s%s", START_TABLE_COLUMN, text, END_TABLE_COLUMN));
            }
            log(END_TABLE_ROW);
            startIndexInclusive = endIndexExclusive;
            endIndexExclusive += NUM_OF_COLUMNS;
        }

        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    private void runConjugation(NamedTemplate template,
                                boolean skipRuleProcessing, SarfTermType[] termTypes,
                                ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                ArabicLetterType thirdRadical) {
        if (isEmpty(termTypes)) {
            return;
        }
        ConjugationBuilder builder = getConjugationBuilder(template);
        print(template, skipRuleProcessing, firstRadical, secondRadical,
                thirdRadical);

        for (SarfTermType sarfTermType : termTypes) {
            conjugateSarfTermType(builder, sarfTermType, skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical);
        }
    }

    @Test
    public void runConjugations() {
        runConjugation(FORM_X_TEMPLATE, false, new SarfTermType[]{
                PRESENT_TENSE, IMPERATIVE}, GHAIN, FA, RA);
        runConjugation(FORM_VI_TEMPLATE, true, new SarfTermType[]{
                PRESENT_TENSE, IMPERATIVE}, JEEM, WAW, ZAIN);
        runConjugation(FORM_X_TEMPLATE, false, new SarfTermType[]{
                PRESENT_TENSE, IMPERATIVE}, AIN, WAW, NOON);
    }
}
