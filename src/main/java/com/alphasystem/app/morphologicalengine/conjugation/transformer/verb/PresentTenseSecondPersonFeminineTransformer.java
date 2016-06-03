package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PresentTenseSecondPersonFeminineTransformer extends PresentTenseThirdPersonFeminineTransformer {

    @AssistedInject
    PresentTenseSecondPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_SINGULAR);
        final ArabicWord arabicWord = target.getRootWord().replaceLetter(0, TA)
                .replaceDiacritic(target.getThirdRadicalIndex(), KASRA).append(YA_WITH_SUKUN, NOON_WITH_FATHA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return new RootWord(super.doDual(rootWord)).withMemberType(SECOND_PERSON_FEMININE_DUAL);
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceLetter(0, TA).replaceDiacritic(
                target.getThirdRadicalIndex(), SUKUN).append(NOON_WITH_FATHA);
        return processRules(target.withRootWord(arabicWord));
    }
}
