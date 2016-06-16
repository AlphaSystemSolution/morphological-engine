package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class NonFlexibleNounTransformer extends AbstractNounTransformer {

    NonFlexibleNounTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return copyRootWord(rootWord, NOMINATIVE_PLURAL);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        return copyRootWord(doNominative(rootWord), ACCUSATIVE_PLURAL);
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doNominative(rootWord), GENITIVE_PLURAL);
    }
}
