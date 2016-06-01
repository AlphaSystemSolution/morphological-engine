package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class FeminineDualTransformer extends AbstractNounTransformer {

    @AssistedInject
    FeminineDualTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor, LAST_LETTER);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceLetter(variableIndex, TA_WITH_FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceLetter(variableIndex, TA_WITH_FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_DUAL);
    }
}