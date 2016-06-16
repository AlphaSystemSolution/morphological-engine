package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PresentTenseThirdPersonMasculineTransformer extends AbstractVerbTransformer {

    PresentTenseThirdPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return copyRootWord(rootWord, THIRD_PERSON_MASCULINE_SINGULAR);
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_DUAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        target.getRootWord().append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return target;
    }
}
