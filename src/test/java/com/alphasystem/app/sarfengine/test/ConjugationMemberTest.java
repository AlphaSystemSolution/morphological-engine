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
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.*;
import static java.lang.String.format;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    @Test
    public void runConjugations() {
        runConjugations(FORM_IV_TEMPLATE, SEEN, LAM, MEEM);
    }

    private void runConjugations(NamedTemplate namedTemplate, ArabicLetterType firstRadical,
                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        final ConjugationBuilder builder = getConjugationBuilder(namedTemplate);

        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        log(START_TABLE_ROW);
        printColumn(builder, ACTIVE_PARTICIPLE_MASCULINE, firstRadical, secondRadical, thirdRadical);
        printColumn(builder, VERBAL_NOUN, firstRadical, secondRadical, thirdRadical);
        printColumn(builder, PRESENT_TENSE, firstRadical, secondRadical, thirdRadical);
        printColumn(builder, PAST_TENSE, firstRadical, secondRadical, thirdRadical);
        log(END_TABLE_ROW);

        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    public void printColumn(ConjugationBuilder builder, SarfTermType sarfTermType,
                            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical) {
        log(format("%s%s%s", START_TABLE_COLUMN, runConjugation(builder, sarfTermType,
                firstRadical, secondRadical, thirdRadical), END_TABLE_COLUMN));
    }

    private String runConjugation(ConjugationBuilder builder, SarfTermType sarfTermType,
                                  ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                  ArabicLetterType thirdRadical) {
        final ConjugationMemberBuilder memberBuilder = builder.getMemberBuilder(false, sarfTermType, firstRadical,
                secondRadical, thirdRadical);
        final ConjugationMember defaultConjugation = memberBuilder.getDefaultConjugation();
        String text = format(ARABIC_TEXT_SPAN, defaultConjugation.getConjugation().toHtmlCode());
        return format("<div>%s &mdash; %s &mdash; %s</div>", sarfTermType, defaultConjugation.getMemberType(), text);
    }
}
