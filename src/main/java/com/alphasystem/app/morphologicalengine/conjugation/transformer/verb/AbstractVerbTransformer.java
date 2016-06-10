package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public abstract class AbstractVerbTransformer extends AbstractTransformer<VerbConjugation> implements VerbTransformer {

    @Override
    public VerbConjugation doTransform(RuleProcessor ruleProcessor, RootWord rootWord, ArabicLetterType firstRadical,
                                       ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                       ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new VerbConjugation(doSingular(ruleProcessor, baseWord), doDual(ruleProcessor, baseWord),
                doPlural(ruleProcessor, baseWord));
    }

    protected abstract RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord);

    protected abstract RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord);

    protected abstract RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord);
}