package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PastTenseThirdPersonFeminineTransformer extends AbstractVerbTransformer {

    PastTenseThirdPersonFeminineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_SINGULAR);
        target.getRootWord().append(TA_WITH_SUKUN);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_DUAL);
        target.getRootWord().append(TA_WITH_FATHA, LETTER_ALIF);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(NOON_WITH_FATHA);
        return target;
    }
}
