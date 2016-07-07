package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
class ImperativeSecondPersonMasculineTransformer extends ForbiddingSecondPersonMasculineTransformer {

    ImperativeSecondPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src) {
        return processRules(ruleProcessor, src, IMPERATIVE);
    }
}
