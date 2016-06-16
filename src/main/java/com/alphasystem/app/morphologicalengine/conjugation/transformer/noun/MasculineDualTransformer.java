package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class MasculineDualTransformer extends AbstractNounTransformer {

    MasculineDualTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_DUAL);
        target.getRootWord().replaceDiacritic(variableIndex, FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return target;
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_DUAL);
        target.getRootWord().replaceDiacritic(variableIndex, FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return target;
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_DUAL);
    }
}
