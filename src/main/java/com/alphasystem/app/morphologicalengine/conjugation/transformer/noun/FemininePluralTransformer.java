package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class FemininePluralTransformer extends AbstractNounTransformer {

    FemininePluralTransformer() {
        this(LAST_LETTER);
    }

    private FemininePluralTransformer(int variableIndex) {
        super(variableIndex);
    }

    @Override
    protected RootWord doNominative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, LETTER_ALIF).append(TA_WITH_DAMMATAN);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doAccusative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, LETTER_ALIF).append(TA_WITH_KASRATAN);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doGenitive(RuleProcessor ruleProcessor, RootWord rootWord) {
        return copyRootWord(doAccusative(ruleProcessor, rootWord), GENITIVE_PLURAL);
    }
}
