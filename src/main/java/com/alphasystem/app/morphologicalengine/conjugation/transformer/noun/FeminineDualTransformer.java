package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class FeminineDualTransformer extends AbstractNounTransformer {

    FeminineDualTransformer() {
        super(LAST_LETTER);
    }

    @Override
    protected RootWord doNominative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, TA_WITH_FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doAccusative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, TA_WITH_FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doGenitive(RuleProcessor ruleProcessor, RootWord rootWord) {
        return copyRootWord(doAccusative(ruleProcessor, rootWord), GENITIVE_DUAL);
    }
}
