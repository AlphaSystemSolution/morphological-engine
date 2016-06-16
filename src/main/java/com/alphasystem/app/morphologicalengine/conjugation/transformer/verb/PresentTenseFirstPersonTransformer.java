package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_PLURAL;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_SINGULAR;

/**
 * @author sali
 */
class PresentTenseFirstPersonTransformer extends PresentTenseThirdPersonMasculineTransformer {

    PresentTenseFirstPersonTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_SINGULAR);
        target.getRootWord().replaceLetter(0, ALIF_HAMZA_ABOVE);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return null;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_PLURAL);
        target.getRootWord().replaceLetter(0, NOON);
        return target;
    }
}
