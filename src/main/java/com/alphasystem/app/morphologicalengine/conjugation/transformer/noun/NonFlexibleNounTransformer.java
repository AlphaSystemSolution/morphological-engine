package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class NonFlexibleNounTransformer extends AbstractNounTransformer {

    @AssistedInject
    NonFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return processRules(copyRootWord(rootWord, NOMINATIVE_PLURAL));
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        return processRules(copyRootWord(doNominative(rootWord), ACCUSATIVE_PLURAL));
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doNominative(rootWord), GENITIVE_PLURAL);
    }
}
