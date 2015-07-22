package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

/**
 * @author sali
 */
public class DefaultRuleProcessor extends AbstractRuleProcessor {

    private final ArabicLetterType hamzahReplacement;

    @AssistedInject
    public DefaultRuleProcessor(@Assisted NamedTemplate template,
                                @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                @Assisted boolean pastTenseHasTransformed,
                                @Nullable @Assisted ArabicLetterType hamzahReplacement) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
        this.hamzahReplacement = hamzahReplacement;

    }

    public ArabicLetterType getHamzahReplacement() {
        return hamzahReplacement;
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        RuleProcessorFactory factory = GuiceSupport.getInstance()
                .getInjector().getInstance(RuleProcessorFactory.class);

        RuleProcessor ruleProcessor = null;

        ruleProcessor = factory.getFormVIIIProcessor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule14Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule1Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule7Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);
        setDiacriticForWeakSecondRadicalWaw(ruleProcessor.getDiacriticForWeakSecondRadicalWaw());
        setPastTenseHasTransformed(ruleProcessor.isPastTenseHasTransformed());

        ruleProcessor = factory.getRule9Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule8Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule10Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule11And12Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule13Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule16Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule17Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule20Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getRule19Processor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getDoubleLetteredProcessor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getHamzatedFirstRadicalProcessor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getHamzatedThirdRadicalProcessor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getHamzahChairProcessors(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed(), getHamzahReplacement());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        ruleProcessor = factory.getPrefixProcessor(template, getDiacriticForWeakSecondRadicalWaw(),
                isPastTenseHasTransformed());
        baseRootWord = ruleProcessor.applyRules(baseRootWord);

        return baseRootWord;
    }
}
