package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory.getConjugationBuilder;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PAST_TENSE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    @Test
    public void runConjugations() {
        final ConjugationBuilder builder = getConjugationBuilder(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE);
        final ConjugationMemberBuilder memberBuilder = builder.getMemberBuilder(false, PAST_TENSE, NOON, SAD, RA);
        final ConjugationMember defaultConjugation = memberBuilder.getDefaultConjugation();
        String text = String.format(ARABIC_TEXT_SPAN, defaultConjugation.getConjugation().toHtmlCode());
        log(String.format("<div>%s: %s</div>", defaultConjugation.getMemberType(), text));
    }
}
