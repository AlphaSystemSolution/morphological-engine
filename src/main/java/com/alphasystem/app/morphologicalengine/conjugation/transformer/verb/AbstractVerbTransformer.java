package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public abstract class AbstractVerbTransformer extends AbstractTransformer<ConjugationTuple> implements VerbTransformer {

    @Override
    public ConjugationTuple doTransform(RuleProcessor ruleProcessor, RootWord rootWord, SarfTermType sarfTermType,
                                        ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final RootWord singular = processRules(ruleProcessor, doSingular(baseWord));
        final RootWord dual = processRules(ruleProcessor, doDual(baseWord));
        final RootWord plural = processRules(ruleProcessor, doPlural(baseWord));
        return new ConjugationTuple(singular, dual, plural);
    }

    protected abstract RootWord doSingular(RootWord rootWord);

    protected abstract RootWord doDual(RootWord rootWord);

    protected abstract RootWord doPlural(RootWord rootWord);
}
