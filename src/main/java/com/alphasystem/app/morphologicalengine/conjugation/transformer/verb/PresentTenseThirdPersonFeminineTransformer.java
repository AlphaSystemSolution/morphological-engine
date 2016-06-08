package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_PLURAL;

/**
 * @author sali
 */
public class PresentTenseThirdPersonFeminineTransformer extends PresentTenseThirdPersonMasculineTransformer {

    @AssistedInject
    PresentTenseThirdPersonFeminineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = super.doSingular(rootWord);
        return processRules(target.withRootWord(target.getRootWord().replaceLetter(0, TA)));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = super.doDual(rootWord);
        return processRules(target.withRootWord(target.getRootWord().replaceLetter(0, TA)));
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(NOON_WITH_FATHA);
        return processRules(target.withRootWord(arabicWord));
    }
}
