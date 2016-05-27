package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class PartlyFlexibleNounTransformer extends AbstractNounTransformer {

    @AssistedInject
    public PartlyFlexibleNounTransformer(@Assisted int variableIndex) {
        super(variableIndex);
    }

    protected PartlyFlexibleNounTransformer() {
        this(LAST_LETTER);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return copyRootWord(rootWord, NOMINATIVE_PLURAL);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA);
        return target.withRootWord(arabicWord);
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_PLURAL);
    }
}
