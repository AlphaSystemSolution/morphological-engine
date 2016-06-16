package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PresentTenseSecondPersonMasculineTransformer extends PresentTenseThirdPersonFeminineTransformer {

    PresentTenseSecondPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return copyRootWord(super.doSingular(rootWord), SECOND_PERSON_MASCULINE_SINGULAR);
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return copyRootWord(super.doDual(rootWord), SECOND_PERSON_MASCULINE_DUAL);
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        target.getRootWord().replaceLetter(0, TA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return target;
    }
}
