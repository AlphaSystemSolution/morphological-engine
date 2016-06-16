package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PresentTenseSecondPersonFeminineTransformer extends PresentTenseThirdPersonFeminineTransformer {

    PresentTenseSecondPersonFeminineTransformer() {
        super();
    }

    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_SINGULAR);
        target.getRootWord().replaceLetter(0, TA).replaceDiacritic(target.getThirdRadicalIndex(), KASRA)
                .append(YA_WITH_SUKUN, NOON_WITH_FATHA);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return copyRootWord(super.doDual(rootWord), SECOND_PERSON_FEMININE_DUAL);
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_PLURAL);
        target.getRootWord().replaceLetter(0, TA).replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(NOON_WITH_FATHA);
        return target;
    }
}
