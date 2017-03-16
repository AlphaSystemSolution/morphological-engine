package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.AbstractTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.ConjugationTuple;

/**
 * @author sali
 */
public abstract class AbstractVerbTransformer extends AbstractTransformer<ConjugationTuple> implements VerbTransformer {

    @Override
    public ConjugationTuple doTransform(RuleProcessor ruleProcessor, RootWord rootWord, SarfTermType sarfTermType,
                                        OutputFormat outputFormat, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        RootWord baseWord = createRootWord(rootWord, sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        final RootWord singular = processRules(ruleProcessor, doSingular(baseWord));
        final RootWord dual = processRules(ruleProcessor, doDual(baseWord));
        final RootWord plural = processRules(ruleProcessor, doPlural(baseWord));
        return new ConjugationTuple(toStringValue(singular, outputFormat), toStringValue(dual, outputFormat),
                toStringValue(plural, outputFormat));
    }

    protected abstract RootWord doSingular(RootWord rootWord);

    protected abstract RootWord doDual(RootWord rootWord);

    protected abstract RootWord doPlural(RootWord rootWord);
}
