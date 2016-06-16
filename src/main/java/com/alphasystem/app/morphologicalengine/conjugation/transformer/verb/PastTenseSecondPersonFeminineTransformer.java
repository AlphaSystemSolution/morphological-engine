package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
class PastTenseSecondPersonFeminineTransformer extends AbstractVerbTransformer {

    PastTenseSecondPersonFeminineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_SINGULAR);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_KASRA);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_DUAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_FEMININE_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_DAMMA, NOON_WITH_SHADDA_AND_FATHA);
        return target;
    }
}
