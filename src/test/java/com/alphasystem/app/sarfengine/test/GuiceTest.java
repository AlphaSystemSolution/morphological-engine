package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import org.testng.annotations.Test;

/**
 * @author sali
 */
public class GuiceTest extends CommonTest {

    private RuleProcessorFactory ruleProcessorFactory;

    @Test
    public void test() {
        /*ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();
        assertNotNull(ruleProcessorFactory, "RuleProcessorFactory is null");

        NamedTemplate namedTemplate = FORM_IV_TEMPLATE;
        DiacriticType diacriticForWeakSecondRadicalWaw = null;
        boolean pastTenseHasTransformed = false;
        ArabicLetterType hamzahReplacement = null;

        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleProcessor(namedTemplate, diacriticForWeakSecondRadicalWaw,
                pastTenseHasTransformed, hamzahReplacement);

        assertNotNull(ruleProcessor);*/
    }
}
