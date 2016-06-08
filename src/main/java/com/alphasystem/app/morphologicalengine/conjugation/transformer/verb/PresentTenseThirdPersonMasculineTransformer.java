package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PresentTenseThirdPersonMasculineTransformer extends AbstractVerbTransformer {

    @AssistedInject
    PresentTenseThirdPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return processRules(copyRootWord(rootWord, THIRD_PERSON_MASCULINE_SINGULAR));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_DUAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), FATHA)
                .append(LETTER_ALIF, NOON_WITH_KASRA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return processRules(target.withRootWord(arabicWord));
    }
}
