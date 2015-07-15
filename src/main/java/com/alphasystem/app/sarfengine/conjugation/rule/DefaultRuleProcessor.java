/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.app.sarfengine.conjugation.rule.processor.*;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public class DefaultRuleProcessor extends AbstractRuleProcessor implements
        RuleProcessor {

    protected boolean pastTenseHasTransformed;

    protected DiacriticType diacriticForWeakSecondRadicalWaw;

    protected ArabicLetterType hamzahReplacement;

    private Rule1Processor rule1Processor;

    private Rule7Processor rule7Processor;

    private Rule8Processor rule8Processor;

    private Rule9Processor rule9Processor;

    private Rule10Processor rule10Processor;

    private Rule11And12Processor rule11And12Processor;

    private Rule13Processor rule13Processor;

    private Rule14Processor rule14Processor;

    private Rule16Processor rule16Processor;

    private Rule17Processor rule17Processor;

    private Rule20Processor rule20Processor;

    private Rule19Processor rule19Processor;

    private DoubleLetteredProcessor doubleLetteredProcessor;

    private HamzatedFirstRadicalProcessor hamzatedFirstRadicalProcessor;

    private HamzatedThirdRadicalProcessor hamzatedThirdRadicalProcessor;

    private HamzaRule7Processor hamzaRule7Processor;

    private HamzahChairProcessor hamzahChairProcessor;

    private FormVIIIProcessor formVIIIProcessor;

    private PrefixProcessor prefixProcessor;

    /**
     * @param template
     */
    public DefaultRuleProcessor(NamedTemplate template) {
        super(template);
        formVIIIProcessor = new FormVIIIProcessor(template);
        rule1Processor = new Rule1Processor(template);
        rule7Processor = new Rule7Processor(template, this);
        rule8Processor = new Rule8Processor(template);
        rule9Processor = new Rule9Processor(template, this);
        rule10Processor = new Rule10Processor(template);
        rule11And12Processor = new Rule11And12Processor(template);
        rule13Processor = new Rule13Processor(template, this);
        rule14Processor = new Rule14Processor(template);
        rule16Processor = new Rule16Processor(template);
        rule17Processor = new Rule17Processor(template, this);
        rule19Processor = new Rule19Processor(template);
        rule20Processor = new Rule20Processor(template);
        doubleLetteredProcessor = new DoubleLetteredProcessor(template);
        hamzaRule7Processor = new HamzaRule7Processor(template);
        hamzatedFirstRadicalProcessor = new HamzatedFirstRadicalProcessor(
                template);
        hamzatedThirdRadicalProcessor = new HamzatedThirdRadicalProcessor(
                template);
        hamzahChairProcessor = new HamzahChairProcessor(template);
        prefixProcessor = new PrefixProcessor(template);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        hamzahChairProcessor.setHamzahReplacement(hamzahReplacement);
        baseRootWord = formVIIIProcessor.applyRules(baseRootWord);
        baseRootWord = rule14Processor.applyRules(baseRootWord);
        baseRootWord = rule1Processor.applyRules(baseRootWord);
        baseRootWord = rule7Processor.applyRules(baseRootWord);
        baseRootWord = rule9Processor.applyRules(baseRootWord);
        baseRootWord = rule8Processor.applyRules(baseRootWord);
        baseRootWord = rule10Processor.applyRules(baseRootWord);
        baseRootWord = rule11And12Processor.applyRules(baseRootWord);
        baseRootWord = rule13Processor.applyRules(baseRootWord);
        baseRootWord = rule16Processor.applyRules(baseRootWord);
        baseRootWord = rule17Processor.applyRules(baseRootWord);
        baseRootWord = rule20Processor.applyRules(baseRootWord);
        baseRootWord = rule19Processor.applyRules(baseRootWord);
        baseRootWord = doubleLetteredProcessor.applyRules(baseRootWord);
        baseRootWord = hamzatedFirstRadicalProcessor.applyRules(baseRootWord);
        baseRootWord = hamzatedThirdRadicalProcessor.applyRules(baseRootWord);
        // baseRootWord = hamzaRule7Processor.applyRules(baseRootWord);
        baseRootWord = hamzahChairProcessor.applyRules(baseRootWord);
        baseRootWord = prefixProcessor.applyRules(baseRootWord);
        return baseRootWord;
    }

    @Override
    public DiacriticType getDiacriticForWeakSecondRadicalWaw() {
        return diacriticForWeakSecondRadicalWaw;
    }

    @Override
    public void setDiacriticForWeakSecondRadicalWaw(
            DiacriticType diacriticForWeakSecondRadicalWaw) {
        this.diacriticForWeakSecondRadicalWaw = diacriticForWeakSecondRadicalWaw;
    }

    public DoubleLetteredProcessor getDoubleLetteredProcessor() {
        return doubleLetteredProcessor;
    }

    public FormVIIIProcessor getFormVIIIProcessor() {
        return formVIIIProcessor;
    }

    public HamzahChairProcessor getHamzahChairProcessor() {
        return hamzahChairProcessor;
    }

    public ArabicLetterType getHamzahReplacement() {
        return hamzahReplacement;
    }

    public void setHamzahReplacement(ArabicLetterType hamzahReplacement) {
        this.hamzahReplacement = hamzahReplacement;
    }

    public HamzaRule7Processor getHamzaRule7Processor() {
        return hamzaRule7Processor;
    }

    public HamzatedFirstRadicalProcessor getHamzatedFirstRadicalProcessor() {
        return hamzatedFirstRadicalProcessor;
    }

    public HamzatedThirdRadicalProcessor getHamzatedThirdRadicalProcessor() {
        return hamzatedThirdRadicalProcessor;
    }

    public PrefixProcessor getPrefixProcessor() {
        return prefixProcessor;
    }

    public Rule10Processor getRule10Processor() {
        return rule10Processor;
    }

    public Rule11And12Processor getRule11And12Processor() {
        return rule11And12Processor;
    }

    public Rule13Processor getRule13Processor() {
        return rule13Processor;
    }

    public Rule14Processor getRule14Processor() {
        return rule14Processor;
    }

    public Rule16Processor getRule16Processor() {
        return rule16Processor;
    }

    public Rule17Processor getRule17Processor() {
        return rule17Processor;
    }

    public Rule19Processor getRule19Processor() {
        return rule19Processor;
    }

    public Rule1Processor getRule1Processor() {
        return rule1Processor;
    }

    public Rule20Processor getRule20Processor() {
        return rule20Processor;
    }

    public Rule7Processor getRule7Processor() {
        return rule7Processor;
    }

    public Rule8Processor getRule8Processor() {
        return rule8Processor;
    }

    public Rule9Processor getRule9Processor() {
        return rule9Processor;
    }

    @Override
    public boolean isPastTenseHasTransformed() {
        return pastTenseHasTransformed;
    }

    @Override
    public void setPastTenseHasTransformed(boolean pastTenseHasTransformed) {
        this.pastTenseHasTransformed = pastTenseHasTransformed;
    }

}
