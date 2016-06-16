package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PastTenseSecondPersonMasculineTransformer extends AbstractVerbTransformer {

    PastTenseSecondPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_SINGULAR);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_FATHA);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_DUAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_DAMMA, MEEM_WITH_SUKUN);
        return target;
    }
}
