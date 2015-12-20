/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.model.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.util.TriLiteralTemplateHelper.*;
import static java.lang.String.format;
import static org.testng.Reporter.log;

;

/**
 * @author sali
 */
public class RuleTester extends Assert implements ArabicLetters {

    private static final String ARABIC_TEXT_SPAN = "<span class='arabicText'>%s</span>";

    private RuleProcessorFactory ruleProcessorFactory = GuiceSupport.getInstance().getInjector().
            getInstance(RuleProcessorFactory.class);

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
        RootWord rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA,
                WAW_WITH_SUKUN, AIN_WITH_KASRA, DAL_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord,
                "Positive Case 1:&mdash; Second radical has KASRA.", false);

        rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA,
                WAW_WITH_SUKUN, HA_WITH_FATHA, BA_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        testExecuteRuleForAssimilatedVerbWawPresentTense(rootWord,
                "Positive Case 2:&mdash; second radical has a heavy letter.",
                false);

        rootWord = (createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA,
                WAW_WITH_SUKUN, JEEM_WITH_DAMMA, LAM_WITH_DAMMA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        testExecuteRuleForAssimilatedVerbWawPresentTense(
                rootWord,
                "Negative Case 1:&mdash; second radical has DAMMAA, no change occured.",
                true);

        rootWord = (createPastTenseRootWord(0, 1, 2, DDAD_WITH_FATHA,
                RA_WITH_KASRA, BA_WITH_FATHA))
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);
        testExecuteRuleForAssimilatedVerbWawPresentTense(
                rootWord,
                "Negative Case 2:&mdash; not a Present or Present passive, no change occured.",
                true);
    }

    private void testExecuteRuleForAssimilatedVerbWawPresentTense(
            RootWord rootWord, String msg, boolean fail) {
        ArabicWord src = rootWord.getRootWord();
        RuleProcessor ruleEngine = ruleProcessorFactory.getRuleEngine(new RuleInfo(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE));
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

        RootWord rootWord = createPresentTenseRootWord(1, 3, 4, YA_WITH_DAMMA,
                HHA_WITH_FATHA, LETTER_ALIF, WAW_WITH_KASRA, RA_WITH_DAMMA);
        String arabicText = format(ARABIC_TEXT_SPAN, rootWord.getRootWord()
                .toHtmlCode());
        log(format("<div>Initial Word: %s</div>", arabicText));
        RuleProcessor ruleEngine = ruleProcessorFactory.getRuleEngine(new RuleInfo(template));
        RootWord rw = ruleEngine.applyRules(rootWord);
        arabicText = format(ARABIC_TEXT_SPAN, rw.getRootWord().toHtmlCode());
        log(format("<div>After Applying HamzaRule7Processor: %s</div>",
                arabicText));
    }

    @Test
    public void testReplaceHamzahWithChair() {
        RootWord rootWord = createRootWord(0, 1, 2, HAMZA_WITH_FATHA,
                KAF_WITH_FATHA, LAM_WITH_FATHA);
        String msg = format("%s at the beginning with %s", HAMZA, FATHA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 1, 2, HAMZA_WITH_DAMMA, KAF_WITH_KASRA,
                LAM_WITH_FATHA);
        msg = format("%s at the beginning with %s", HAMZA, DAMMA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 1, 2, HAMZA_WITH_KASRA, KAF_WITH_SUKUN,
                LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN);
        msg = format("%s at the beginning with %s", HAMZA, KASRA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(1, 2, 3, YA_WITH_FATHA, HAMZA_WITH_SUKUN,
                KAF_WITH_DAMMA, LAM_WITH_DAMMA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA,
                SUKUN, FATHA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(1, 2, 3, YA_WITH_DAMMA, HAMZA_WITH_SUKUN,
                KAF_WITH_FATHA, LAM_WITH_DAMMA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA,
                SUKUN, DAMMA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 1, 2, RA_WITH_DAMMA, HAMZA_WITH_KASRA,
                SEEN_WITH_FATHA);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA,
                KASRA, DAMMA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 1, 2, HA_WITH_DAMMA, NOON_WITH_KASRA,
                HAMZA_WITH_FATHA);
        msg = format("%s in the end with %s with previous letter %s", HAMZA,
                FATHA, KASRA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 1, 2, JEEM_WITH_DAMMA, HAMZA_WITH_SUKUN,
                TA_WITH_DAMMA, MEEM_WITH_SUKUN);
        msg = format("%s in the middle with %s with previous letter %s", HAMZA,
                SUKUN, DAMMA);
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = (createPastTenseRootWord(0, 1, 2, DDAD_WITH_FATHA,
                RA_WITH_KASRA, BA_WITH_FATHA));
        msg = "Non Hamzated word";
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = FormTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE
                .getPassiveParticipleFeminineRoot();
        rootWord = new RootWord(rootWord, QAF, RA, HAMZA);
        ArabicWord word = rootWord.getRootWord();
        rootWord.setRootWord(word);
        msg = "FormICategoryAGroupA";
        testReplaceHamzahWithChair(rootWord, msg);

        rootWord = createRootWord(0, 2, 3, QAF_WITH_FATHA, LETTER_ALIF,
                HAMZA_WITH_KASRA, LAM_WITH_DAMMATAN);
        msg = "FormICategoryAGroupA";
        testReplaceHamzahWithChair(rootWord, msg);
    }

    private void testReplaceHamzahWithChair(RootWord baseRootWord, String msg) {
        RuleProcessor processor = ruleProcessorFactory.getHamzahChairProcessor(new RuleInfo(FORM_I_CATEGORY_U_TEMPLATE));
        ArabicWord src = baseRootWord.getRootWord();
        ArabicWord result = processor.applyRules(baseRootWord).getRootWord();
        print("testReplaceHamzahWithChair", src, result, msg);
    }
}
