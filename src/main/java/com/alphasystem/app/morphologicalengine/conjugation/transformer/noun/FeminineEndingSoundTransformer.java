package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
class FeminineEndingSoundTransformer extends AbstractNounTransformer {

    FeminineEndingSoundTransformer() {
        super(LAST_LETTER);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return copyRootWord(rootWord, NOMINATIVE_SINGULAR);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_SINGULAR);
        target.getRootWord().replaceDiacritic(variableIndex, FATHATAN);
        return target;
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, GENITIVE_SINGULAR);
        target.getRootWord().replaceDiacritic(variableIndex, KASRATAN);
        return target;
    }
}
