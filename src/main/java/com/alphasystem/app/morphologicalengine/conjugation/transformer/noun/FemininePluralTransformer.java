package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class FemininePluralTransformer extends AbstractNounTransformer {

    FemininePluralTransformer() {
        this(LAST_LETTER);
    }

    private FemininePluralTransformer(int variableIndex) {
        super(variableIndex);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, LETTER_ALIF).append(TA_WITH_DAMMATAN);
        return target;
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        target.getRootWord().replaceLetter(variableIndex, LETTER_ALIF).append(TA_WITH_KASRATAN);
        return target;
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_PLURAL);
    }
}
