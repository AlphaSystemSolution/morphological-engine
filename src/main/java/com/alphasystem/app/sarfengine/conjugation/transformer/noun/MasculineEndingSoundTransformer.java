package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineEndingSoundTransformer extends AbstractNounTransformer {

    MasculineEndingSoundTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return copyRootWord(rootWord, NOMINATIVE_SINGULAR);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHATAN).append(LETTER_ALIF);
        return target.withRootWord(arabicWord);
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, GENITIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, KASRATAN);
        return target.withRootWord(arabicWord);
    }
}
