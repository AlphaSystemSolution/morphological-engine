package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.test.CommonTest;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType.Type.RULE_ENGINE;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType.Type.FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getRuleProcessor;
import static com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory.getVerbTransformer;
import static com.alphasystem.arabic.model.ArabicLetterType.BA;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.arabic.model.ArabicLetterType.KAF;
import static com.alphasystem.arabic.model.ArabicLetterType.KHA;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.QAF;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
@ContextConfiguration(classes = {MorphologicalEngineConfiguration.class})
public class VerbTransformerTest {

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
        final AbstractVerbTransformer verbTransformer = (AbstractVerbTransformer) getVerbTransformer(IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER);
        RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, new RuleInfo(form.getTemplate(), rootLetters));
        testImperativeOrForbidding(form, verbTransformer, ruleProcessor, rootLetters, IMPERATIVE, beforeMessage, afterMessage);
    }

    private void testForbidding(final Form form, RootLetters rootLetters, String beforeMessage, String afterMessage) {
        final AbstractVerbTransformer verbTransformer = (AbstractVerbTransformer) getVerbTransformer(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER);
        RuleProcessor ruleProcessor = getRuleProcessor(RULE_ENGINE, new RuleInfo(form.getTemplate(), rootLetters));
        testImperativeOrForbidding(form, verbTransformer, ruleProcessor, rootLetters, FORBIDDING, beforeMessage, afterMessage);
    }

    private void testImperativeOrForbidding(final Form form, AbstractVerbTransformer verbTransformer, RuleProcessor ruleEngine,
                                            RootLetters rootLetters, SarfTermType termType,
                                            String beforeMessage, String afterMessage) {
        final VerbRootBase verbRootBase = form.getImperative();
        final Verb root = verbRootBase.getRoot();
        RootWord rootWord = new RootWord(root.getRootWord(), rootLetters.getFirstRadical(), rootLetters.getSecondRadical(),
                rootLetters.getThirdRadical()).withSarfTermType(PRESENT_TENSE);
        rootWord = verbTransformer.doSingular(rootWord);
        log(String.format("%s %s<br/>", beforeMessage, CommonTest.printArabicText(rootWord.getRootWord())));
        rootWord = ruleEngine.applyRules(rootWord);
        rootWord = ruleEngine.applyRules(rootWord.withSarfTermType(termType));
        log(String.format("%s %s<br/>", afterMessage, CommonTest.printArabicText(rootWord.getRootWord())));
    }

}
