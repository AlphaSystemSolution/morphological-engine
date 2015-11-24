package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.model.VerbalNoun;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    private MemberBuilderFactory factory = GuiceSupport.getInstance().getMemberBuilderFactory();
    private RuleProcessorFactory ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        return processReplacements(src, firstRadical, secondRadical, thirdRadical, null);
    }

    @Test
    public void runConjugations() {
        runConjugations(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, NOON, SAD, RA, VerbalNoun.VERBAL_NOUN_V1.getRootWord());
        runConjugations(FORM_IV_TEMPLATE, SEEN, LAM, MEEM, VerbalNoun.VERBAL_NOUN_FORM_IV.getRootWord());
    }

//    @Test
//    public void testVerbalNouns() {
//        ConjugationMemberBuilder rightBuilder;
//        ConjugationMemberBuilder leftBuilder;
//        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleEngine(new RuleInfo());
//
//        VerbalNoun[] verbalNouns = VerbalNoun.values();
//        int index = 0;
//        while (index < 38) {
//            rightBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor,
//                    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, false, FA, AIN, LAM, verbalNouns[index++].getRootWord());
//            leftBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor,
//                    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, false, FA, AIN, LAM, verbalNouns[index++].getRootWord());
//            printConjugations(leftBuilder, rightBuilder, false);
//        }
//    }

    private void runConjugations(NamedTemplate namedTemplate, ArabicLetterType firstRadical,
                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical, RootWord verbalNoun) {
        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleEngine(new RuleInfo(namedTemplate));
        FormTemplate formTemplate = FormTemplate.getByNamedTemplate(namedTemplate);
        // Active Present and Past Tenses
        ConjugationMemberBuilder rightBuilder = factory.getTriLiteralPastTenseBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPastTenseRoot(), firstRadical, secondRadical, thirdRadical));
        ConjugationMemberBuilder leftBuilder = factory.getTriLiteralPresentTenseBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPresentTenseRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

        // Verbal noun
        leftBuilder = factory.getTriLiteralVerbalNounBuilder(ruleProcessor, false, verbalNoun);
        printConjugations(leftBuilder, null, true);

        // Active Participle Masculine and Feminine
        rightBuilder = factory.getTriLiteralActiveParticipleMasculineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getActiveParticipleMasculineRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralActiveParticipleFeminineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getActiveParticipleFeminineRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, false);

        // Passive Present and Past Tenses
        rightBuilder = factory.getTriLiteralPastPassiveBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPastPassiveTenseRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralPresentPassiveBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPresentPassiveTenseRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

        // Passive Participle Masculine and Feminine
        rightBuilder = factory.getTriLiteralPassiveParticipleMasculineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPassiveParticipleMasculineRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralPassiveParticipleFeminineBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getPassiveParticipleFeminineRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, false);

        // Imperative and Forbidding
        rightBuilder = namedTemplate.equals(FORM_IV_TEMPLATE) ?
                factory.getTriLiteralImperativeFormIVBuilder(ruleProcessor, false,
                        processReplacements(formTemplate.getImperativeRoot(), firstRadical, secondRadical, thirdRadical)) :
                factory.getTriLiteralImperativeBuilder(ruleProcessor, false,
                        processReplacements(formTemplate.getImperativeRoot(), firstRadical, secondRadical, thirdRadical));
        leftBuilder = factory.getTriLiteralForbiddingBuilder(ruleProcessor, false,
                processReplacements(formTemplate.getForbiddingRoot(), firstRadical, secondRadical, thirdRadical));
        printConjugations(leftBuilder, rightBuilder, true);

    }

    private void printConjugations(ConjugationMemberBuilder leftBuilder, ConjugationMemberBuilder rightBuilder,
                                   boolean displayStatus) {
        RootWord[] leftSideRootWords = leftBuilder.doConjugation();
        SarfTermType leftTermType = leftBuilder.getTermType();
        RootWord[] rightSideRootWords = new RootWord[leftSideRootWords.length];
        SarfTermType rightTermType = null;
        if (rightBuilder != null) {
            rightSideRootWords = rightBuilder.doConjugation();
            rightTermType = rightBuilder.getTermType();
        }
        printTable(leftSideRootWords, rightSideRootWords, leftTermType, rightTermType, displayStatus);
    }

}
