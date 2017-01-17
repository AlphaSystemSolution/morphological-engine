package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import org.testng.annotations.Test;

import static com.alphasystem.app.sarfengine.test.CommonTest.printArabicText;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static java.lang.String.format;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class VerbTransformerTest {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();

    @Test
    public void testForbiddingFirstRadicalHamzah() {
        final Form form = Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
        final RootLetters rootLetters = new RootLetters(HAMZA, KAF, LAM);
        testForbidding(form, rootLetters, "Forbidding for First radical HAMZAH before rule processing is:",
                "Forbidding for first radical HAMZAH after rule processing is:");
    }

    @Test
    public void testImperativeFirstRadicalHamzah() {
        final Form form = Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
        final RootLetters rootLetters = new RootLetters(HAMZA, KAF, LAM);
        testImperative(form, rootLetters, "Imperative for First radical HAMZAH before rule processing is:",
                "Imperative for first radical HAMZAH after rule processing is:");
    }

    @Test
    public void testImperativeSecondRadicalWaw() {
        final Form form = Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
        final RootLetters rootLetters = new RootLetters(QAF, WAW, LAM);
        testImperative(form, rootLetters, "Imperative for second radical WAW before rule processing is:",
                "Imperative for second radical WAW after rule processing is:");
    }

    @Test
    public void testFormIVImperative() {
        final Form form = Form.FORM_IV_TEMPLATE;
        final RootLetters rootLetters = new RootLetters(KHA, BA, RA);
        testImperative(form, rootLetters, "Imperative for Form IV before rule processing is:",
                "Imperative for Form IV after rule processing is:");
    }

    private void testImperative(final Form form, RootLetters rootLetters, String beforeMessage, String afterMessage) {
        testImperativeOrForbidding(form, rootLetters, IMPERATIVE, beforeMessage, afterMessage);
    }

    private void testForbidding(final Form form, RootLetters rootLetters, String beforeMessage, String afterMessage) {
        testImperativeOrForbidding(form, rootLetters, FORBIDDING, beforeMessage, afterMessage);
    }

    private void testImperativeOrForbidding(final Form form, RootLetters rootLetters, SarfTermType termType,
                                            String beforeMessage, String afterMessage) {
        final VerbRootBase verbRootBase = form.getImperative();
        final Verb root = verbRootBase.getRoot();
        final AbstractVerbTransformer verbTransformer = (AbstractVerbTransformer) GUICE_SUPPORT.getVerbTransformer(
                root.getSecondPersonMasculineName());
        RootWord rootWord = new RootWord(root.getRootWord(), rootLetters.getFirstRadical(), rootLetters.getSecondRadical(),
                rootLetters.getThirdRadical()).withSarfTermType(PRESENT_TENSE);
        rootWord = verbTransformer.doSingular(rootWord);
        log(format("%s %s<br/>", beforeMessage, printArabicText(rootWord.getRootWord())));
        RuleProcessor ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(form.getTemplate(), rootLetters));
        rootWord = ruleEngine.applyRules(rootWord);
        rootWord = ruleEngine.applyRules(rootWord.withSarfTermType(termType));
        log(format("%s %s<br/>", afterMessage, printArabicText(rootWord.getRootWord())));
    }

}
