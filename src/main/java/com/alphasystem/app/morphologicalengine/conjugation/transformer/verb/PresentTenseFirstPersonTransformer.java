package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_PLURAL;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_SINGULAR;

/**
 * @author sali
 */
public class PresentTenseFirstPersonTransformer extends PresentTenseThirdPersonMasculineTransformer {

    @AssistedInject
    PresentTenseFirstPersonTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_SINGULAR);
        return processRules(target.withRootWord(target.getRootWord().replaceLetter(0, ALIF_HAMZA_ABOVE)));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return null;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_PLURAL);
        return processRules(target.withRootWord(target.getRootWord().replaceLetter(0, NOON)));
    }
}
