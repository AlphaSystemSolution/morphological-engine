package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class MasculinePluralTransformer extends AbstractNounTransformer {

    @AssistedInject
    MasculinePluralTransformer(@Assisted RootWord rootWord,
                               @Assisted("firstRadical") ArabicLetterType firstRadical,
                               @Assisted("secondRadical") ArabicLetterType secondRadical,
                               @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                               @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical) {
        super(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public RootWord doNominative() {
        RootWord target = copyRootWord(rootWord, NOMINATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceDiacritic(variableIndex, DAMMA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doAccusative() {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord();
        arabicWord.replaceDiacritic(variableIndex, KASRA).append(YA_WITH_SUKUN, NOON_WITH_FATHA);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doGenitive() {
        return copyRootWord(doAccusative(), GENITIVE_PLURAL);
    }
}
