package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;


/**
 * @author sali
 */
class ForbiddingSecondPersonFeminineTransformer extends PresentTenseSecondPersonFeminineTransformer {

    ForbiddingSecondPersonFeminineTransformer() {
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = new RootWord(super.doSingular(rootWord));
        target.getRootWord().removeLast();
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = new RootWord(super.doDual(rootWord));
        target.getRootWord().removeLast();
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        return new RootWord(super.doPlural(rootWord));
    }

    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src, SarfTermType termType) {
        RootWord target = new RootWord(src).withSarfTermType(PRESENT_TENSE);
        target = super.processRules(ruleProcessor, target).withSarfTermType(termType);
        return super.processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src) {
        return processRules(ruleProcessor, src, FORBIDDING);
    }
}
