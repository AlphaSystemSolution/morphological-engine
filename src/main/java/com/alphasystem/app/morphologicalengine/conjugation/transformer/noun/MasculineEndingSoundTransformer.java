package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineEndingSoundTransformer extends AbstractNounTransformer {

    @AssistedInject
    MasculineEndingSoundTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return processRules(copyRootWord(rootWord, NOMINATIVE_SINGULAR));
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHATAN).append(LETTER_ALIF);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, GENITIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, KASRATAN);
        return processRules(target.withRootWord(arabicWord));
    }
}
