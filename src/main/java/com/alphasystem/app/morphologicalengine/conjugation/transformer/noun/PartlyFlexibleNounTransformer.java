package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class PartlyFlexibleNounTransformer extends AbstractNounTransformer {

    PartlyFlexibleNounTransformer() {
        this(LAST_LETTER);
    }

    PartlyFlexibleNounTransformer(int variableIndex) {
        super(variableIndex);
    }

    @Override
    protected RootWord doNominative(RuleProcessor ruleProcessor, RootWord rootWord) {
        return processRules(ruleProcessor, copyRootWord(rootWord, NOMINATIVE_PLURAL));
    }

    @Override
    protected RootWord doAccusative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceDiacritic(variableIndex, FATHA);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doGenitive(RuleProcessor ruleProcessor, RootWord rootWord) {
        return copyRootWord(doAccusative(ruleProcessor, rootWord), GENITIVE_PLURAL);
    }
}
