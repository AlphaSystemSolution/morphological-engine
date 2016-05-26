package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class NonFlexibleNounTransformer extends AbstractNounTransformer {

    @AssistedInject
    public NonFlexibleNounTransformer(@Assisted RootWord rootWord,
                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                      @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical) {
        super(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public RootWord doNominative() {
        return copyRootWord(rootWord, NOMINATIVE_PLURAL);
    }

    @Override
    public RootWord doAccusative() {
        return copyRootWord(doNominative(), ACCUSATIVE_PLURAL);
    }

    @Override
    public RootWord doGenitive() {
        return copyRootWord(doNominative(), GENITIVE_PLURAL);
    }
}
