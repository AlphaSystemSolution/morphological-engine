package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineDualTransformer extends AbstractNounTransformer {

    MasculineDualTransformer() {
        super();
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return target.withRootWord(arabicWord);
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceDiacritic(variableIndex, FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return target.withRootWord(arabicWord);
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_DUAL);
    }
}
