package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculineEndingSoundTransformer extends AbstractNounTransformer {

    @AssistedInject
    MasculineEndingSoundTransformer(@Assisted RootWord rootWord,
                                    @Assisted("firstRadical") ArabicLetterType firstRadical,
                                    @Assisted("secondRadical") ArabicLetterType secondRadical,
                                    @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                    @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical) {
        super(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public RootWord doNominative() {
        return copyRootWord(rootWord, NOMINATIVE_SINGULAR);
    }

    @Override
    public RootWord doAccusative() {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHATAN).append(LETTER_ALIF);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doGenitive() {
        RootWord target = copyRootWord(rootWord, GENITIVE_SINGULAR);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, KASRATAN);
        return target.withRootWord(arabicWord);
    }
}
