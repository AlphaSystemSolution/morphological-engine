package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_PLURAL;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_SINGULAR;

/**
 * @author sali
 */
class PastTenseFirstPersonTransformer extends AbstractVerbTransformer {

    PastTenseFirstPersonTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_SINGULAR);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(TA_WITH_DAMMA);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return null;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_PLURAL);
        target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN).append(NOON_WITH_FATHA, LETTER_ALIF);
        return target;
    }
}
