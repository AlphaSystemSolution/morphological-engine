package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;

/**
 * @author sali
 */
public class GuiceTest extends CommonTest {

    private RuleProcessorFactory ruleProcessorFactory;

    @Test
    public void test() {
        ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();
        assertNotNull(ruleProcessorFactory, "RuleProcessorFactory is null");

        NamedTemplate namedTemplate = FORM_IV_TEMPLATE;
        DiacriticType diacriticForWeakSecondRadicalWaw = null;
        boolean pastTenseHasTransformed = false;
        ArabicLetterType hamzahReplacement = null;

        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleProcessor(namedTemplate, diacriticForWeakSecondRadicalWaw,
                pastTenseHasTransformed, hamzahReplacement);

        assertNotNull(ruleProcessor);
    }
}
