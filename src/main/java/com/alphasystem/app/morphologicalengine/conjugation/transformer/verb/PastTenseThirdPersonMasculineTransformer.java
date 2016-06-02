package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PastTenseThirdPersonMasculineTransformer extends AbstractVerbTransformer {

    @AssistedInject
    PastTenseThirdPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return processRules(copyRootWord(rootWord, THIRD_PERSON_MASCULINE_SINGULAR));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_DUAL);
        return processRules(target.withRootWord(target.getRootWord().append(LETTER_ALIF)));
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), DAMMA)
                .append(WAW_WITH_SUKUN, LETTER_ALIF);
        return processRules(target.withRootWord(arabicWord));
    }
}
