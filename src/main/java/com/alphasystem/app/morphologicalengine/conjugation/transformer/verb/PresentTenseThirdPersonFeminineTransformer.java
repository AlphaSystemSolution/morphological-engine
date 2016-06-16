package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PresentTenseThirdPersonFeminineTransformer extends PresentTenseThirdPersonMasculineTransformer {

    PresentTenseThirdPersonFeminineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(super.doSingular(rootWord), THIRD_PERSON_FEMININE_SINGULAR);
        target.getRootWord().replaceLetter(0, TA);
        return target;
    }


    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(super.doDual(rootWord), THIRD_PERSON_FEMININE_DUAL);
        target.getRootWord().replaceLetter(0, TA);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(NOON_WITH_FATHA);
        return target;
    }
}
