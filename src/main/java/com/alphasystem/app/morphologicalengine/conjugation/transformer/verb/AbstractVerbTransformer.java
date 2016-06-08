package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public abstract class AbstractVerbTransformer extends AbstractTransformer<VerbConjugation> implements VerbTransformer {

    protected AbstractVerbTransformer(RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    public VerbConjugation doTransform(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType
            secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        return new VerbConjugation(doSingular(baseWord), doDual(baseWord), doPlural(baseWord));
    }

    protected abstract RootWord doSingular(RootWord rootWord);

    protected abstract RootWord doDual(RootWord rootWord);

    protected abstract RootWord doPlural(RootWord rootWord);
}
