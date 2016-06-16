package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class MasculinePluralTransformer extends AbstractNounTransformer {

    MasculinePluralTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        target.getRootWord().replaceDiacritic(variableIndex, DAMMA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return target;
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceDiacritic(variableIndex, KASRA).append(YA_WITH_SUKUN, NOON_WITH_FATHA);
        return target;
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_PLURAL);
    }
}
