package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineDualTransformer extends AbstractNounTransformer {

    @AssistedInject
    MasculineDualTransformer(@Assisted RootWord rootWord,
                             @Assisted("firstRadical") ArabicLetterType firstRadical,
                             @Assisted("secondRadical") ArabicLetterType secondRadical,
                             @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                             @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical) {
        super(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public RootWord doNominative() {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doAccusative() {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_DUAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceDiacritic(variableIndex, FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doGenitive() {
        return copyRootWord(doAccusative(), GENITIVE_DUAL);
    }
}
