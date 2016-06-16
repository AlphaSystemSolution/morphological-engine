package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PastTenseThirdPersonMasculineTransformer extends AbstractVerbTransformer {

    PastTenseThirdPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return copyRootWord(rootWord, THIRD_PERSON_MASCULINE_SINGULAR);
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_DUAL);
        target.getRootWord().append(LETTER_ALIF);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), DAMMA).append(WAW_WITH_SUKUN, LETTER_ALIF);
        return target;
    }
}
