package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineEndingSoundTransformer extends AbstractNounTransformer {

    MasculineEndingSoundTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RuleProcessor ruleProcessor, RootWord rootWord) {
        return processRules(ruleProcessor, copyRootWord(rootWord, NOMINATIVE_SINGULAR));
    }

    @Override
    protected RootWord doAccusative(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_SINGULAR);
        target.getRootWord().replaceDiacritic(variableIndex, FATHATAN).append(LETTER_ALIF);
        return processRules(ruleProcessor, target);
    }

    @Override
    protected RootWord doGenitive(RuleProcessor ruleProcessor, RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, GENITIVE_SINGULAR);
        target.getRootWord().replaceDiacritic(variableIndex, KASRATAN);
        return processRules(ruleProcessor, target);
    }
}
