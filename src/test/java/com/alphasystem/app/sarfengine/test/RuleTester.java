package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.util.TriLiteralTemplateHelper.createPastTenseRootWord;
import static com.alphasystem.morphologicalanalysis.morphology.util.TriLiteralTemplateHelper.createPresentTenseRootWord;
import static com.alphasystem.morphologicalanalysis.morphology.util.TriLiteralTemplateHelper.createRootWord;
import static java.lang.String.format;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
@ContextConfiguration(classes = {RuleProcessorConfiguration.class})
public class RuleTester extends Assert implements ArabicLetters {

    private static final String ARABIC_TEXT_SPAN = "<span class='arabicText'>%s</span>";

    @Autowired
    @RuleProcessorType(RuleProcessorType.Type.RULE_ENGINE)
    private RuleProcessorFactory ruleEngineFactory;

    @Autowired
    @RuleProcessorType(RuleProcessorType.Type.HAMZAH_CHAIR_PROCESSOR)
    private RuleProcessorFactory ruleProcessorFactory;

    private void print(String testName, ArabicWord src, ArabicWord result,
                       String msg) {
        log(format(
                "<hr/><div style=\"font-weight: bold\">Executing Test: %s</div>",
                testName));
        if (msg != null) {
            log(format("<div>%s</div>", msg));
        }
        log(format("<div>Word BEFORE: %s, Word AFTER: %s</div>",
                format(ARABIC_TEXT_SPAN, src.toHtmlCode()),
                format(ARABIC_TEXT_SPAN, result.toHtmlCode())));
        log("<hr/><br/>");
    }

    @Test
    public void testExecuteRuleForAssimilatedVerbWawPresentTense() {
        RootWord rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, WAW_WITH_SUKUN, AIN_WITH_KASRA, DAL_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);

        RootLetters rootLetters = new RootLetters(WAW, AIN, DAL);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord, rootLetters,
                "Positive Case 1:&mdash; Second radical has KASRA.", false);

        rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, WAW_WITH_SUKUN, HA_WITH_FATHA, BA_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        rootLetters = new RootLetters(WAW, HA, BA);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord, rootLetters,
                "Positive Case 2:&mdash; second radical has a heavy letter.",
                false);

        rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, WAW_WITH_SUKUN, JEEM_WITH_DAMMA, LAM_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        rootLetters = new RootLetters(WAW, JEEM, LAM);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord, rootLetters,
                "Negative Case 1:&mdash; second radical has DAMMAA, no change occured.", true);

        rootWord = (createPastTenseRootWord(0, 1, 2, DDAD_WITH_FATHA, RA_WITH_KASRA, BA_WITH_FATHA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        rootLetters = new RootLetters(DDAD, RA, BA);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord, rootLetters,
                "Negative Case 2:&mdash; not a Present or Present passive, no change occurred.", true);
    }

    private void testExecuteRuleForAssimilatedVerbWawPresentTense(RootWord rootWord, RootLetters rootLetters, String msg, boolean fail) {
        ArabicWord src = rootWord.getRootWord();
        RuleProcessor ruleEngine = ruleEngineFactory.createRuleProcessor(new RuleInfo(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, rootLetters));
        ArabicWord result = ruleEngine.applyRules(rootWord).getRootWord();
        print("applyRulesForAssimilatedVerbWawPresentTense",
                rootWord.getRootWord(), result, msg);
        if (fail) {
            assertEquals(src, result);
        } else {
            assertNotEquals(src, result);
        }
    }

    @Test
    public void test() {
        NamedTemplate template = NamedTemplate.FORM_III_TEMPLATE;

        RootWord rootWord = createPresentTenseRootWord(1, 3, 4, YA_WITH_DAMMA, HHA_WITH_FATHA, LETTER_ALIF, WAW_WITH_KASRA,
                RA_WITH_DAMMA);
        String arabicText = format(ARABIC_TEXT_SPAN, rootWord.getRootWord().toHtmlCode());
        log(format("<div>Initial Word: %s</div>", arabicText));
        RootLetters rootLetters = new RootLetters(HHA, WAW, RA);
        RuleProcessor ruleEngine = ruleEngineFactory.createRuleProcessor(new RuleInfo(template, rootLetters));
        RootWord rw = ruleEngine.applyRules(rootWord);
        arabicText = format(ARABIC_TEXT_SPAN, rw.getRootWord().toHtmlCode());
        log(format("<div>After Applying HamzaRule7Processor: %s</div>",
                arabicText));
    }

    @Test
    public void testReplaceHamzahWithChair() {
        RootWord rootWord = createRootWord(0, 1, 2, HAMZA_WITH_FATHA, KAF_WITH_FATHA, LAM_WITH_FATHA);
        String msg = format("%s at the beginning with %s", HAMZA, FATHA);
        RootLetters rootLetters = new RootLetters(HAMZA, KAF, LAM);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 1, 2, HAMZA_WITH_DAMMA, KAF_WITH_KASRA, LAM_WITH_FATHA);
        msg = format("%s at the beginning with %s", HAMZA, DAMMA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 1, 2, HAMZA_WITH_KASRA, KAF_WITH_SUKUN, LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN);
        msg = format("%s at the beginning with %s", HAMZA, KASRA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(1, 2, 3, YA_WITH_FATHA, HAMZA_WITH_SUKUN, KAF_WITH_DAMMA, LAM_WITH_DAMMA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA, SUKUN, FATHA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(1, 2, 3, YA_WITH_DAMMA, HAMZA_WITH_SUKUN, KAF_WITH_FATHA, LAM_WITH_DAMMA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA, SUKUN, DAMMA);
        rootLetters = new RootLetters(HAMZA, KAF, LAM);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 1, 2, RA_WITH_DAMMA, HAMZA_WITH_KASRA, SEEN_WITH_FATHA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA, KASRA, DAMMA);
        rootLetters = new RootLetters(RA, HAMZA, SEEN);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 1, 2, HA_WITH_DAMMA, NOON_WITH_KASRA, HAMZA_WITH_FATHA);
        msg = format("%s in the end with %s with previous letter %s", HAMZA, FATHA, KASRA);
        rootLetters = new RootLetters(HA, NOON, HAMZA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 1, 2, JEEM_WITH_DAMMA, HAMZA_WITH_SUKUN, TA_WITH_DAMMA, MEEM_WITH_SUKUN);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA, SUKUN, DAMMA);
        rootLetters = new RootLetters(JEEM, HAMZA, TA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = (createPastTenseRootWord(0, 1, 2, DDAD_WITH_FATHA, RA_WITH_KASRA, BA_WITH_FATHA));
        msg = "Non Hamzated word";
        rootLetters = new RootLetters(DDAD, RA, BA);
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = Form.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE.getPassiveParticipleFeminine().getSingularBaseWord().getRootWord();
        rootWord = new RootWord(rootWord, QAF, RA, HAMZA);
        rootLetters = new RootLetters(QAF, RA, HAMZA);
        ArabicWord word = rootWord.getRootWord();
        rootWord.setRootWord(word);
        msg = "FormICategoryAGroupA";
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);

        rootWord = createRootWord(0, 2, 3, QAF_WITH_FATHA, LETTER_ALIF,
                HAMZA_WITH_KASRA, LAM_WITH_DAMMATAN);
        msg = "FormICategoryAGroupA";
        testReplaceHamzahWithChair(rootWord, rootLetters, msg);
    }

    private void testReplaceHamzahWithChair(RootWord baseRootWord, RootLetters rootLetters, String msg) {
        RuleProcessor processor = ruleEngineFactory.createRuleProcessor(new RuleInfo(FORM_I_CATEGORY_U_TEMPLATE, rootLetters));
        ArabicWord src = baseRootWord.getRootWord();
        ArabicWord result = processor.applyRules(baseRootWord).getRootWord();
        print("testReplaceHamzahWithChair", src, result, msg);
    }
}
