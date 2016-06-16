package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class PartlyFlexibleNounTransformer extends AbstractNounTransformer {

    PartlyFlexibleNounTransformer() {
        this(LAST_LETTER);
    }

    PartlyFlexibleNounTransformer(int variableIndex) {
        super(variableIndex);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return copyRootWord(rootWord, NOMINATIVE_PLURAL);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceDiacritic(variableIndex, FATHA);
        return target;
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_PLURAL);
    }
}
