package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PastTenseSecondPersonMasculineTransformer extends AbstractVerbTransformer {

    @AssistedInject
    PastTenseSecondPersonMasculineTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_SINGULAR);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_FATHA);
        target.setRootWord(arabicWord);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_DUAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);
        target.setRootWord(arabicWord);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_DAMMA, MEEM_WITH_SUKUN);
        target.setRootWord(arabicWord);
        return target;
    }
}
