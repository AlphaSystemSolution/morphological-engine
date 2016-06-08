package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineDualTransformer extends AbstractNounTransformer {

    @AssistedInject
    MasculineDualTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceDiacritic(variableIndex, FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_DUAL);
    }
}
