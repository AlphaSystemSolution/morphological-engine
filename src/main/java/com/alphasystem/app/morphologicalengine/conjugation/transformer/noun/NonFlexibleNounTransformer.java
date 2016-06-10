package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class NonFlexibleNounTransformer extends AbstractNounTransformer {

    NonFlexibleNounTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RuleProcessor ruleProcessor, RootWord rootWord) {
        return processRules(ruleProcessor, copyRootWord(rootWord, NOMINATIVE_PLURAL));
    }

    @Override
    protected RootWord doAccusative(RuleProcessor ruleProcessor, RootWord rootWord) {
        return copyRootWord(doNominative(ruleProcessor, rootWord), ACCUSATIVE_PLURAL);
    }

    @Override
    protected RootWord doGenitive(RuleProcessor ruleProcessor, RootWord rootWord) {
        return copyRootWord(doNominative(ruleProcessor, rootWord), GENITIVE_PLURAL);
    }
}
