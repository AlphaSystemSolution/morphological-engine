package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PresentTenseSecondPersonMasculineTransformer extends PresentTenseThirdPersonFeminineTransformer {

    @AssistedInject
    PresentTenseSecondPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return new RootWord(super.doSingular(rootWord)).withMemberType(SECOND_PERSON_MASCULINE_SINGULAR);
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return new RootWord(super.doDual(rootWord)).withMemberType(SECOND_PERSON_MASCULINE_DUAL);
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceLetter(0, TA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return processRules(target.withRootWord(arabicWord));
    }
}
