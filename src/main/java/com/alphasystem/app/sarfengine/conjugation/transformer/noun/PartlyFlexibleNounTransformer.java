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
public class PartlyFlexibleNounTransformer extends AbstractNounTransformer {

    @AssistedInject
    public PartlyFlexibleNounTransformer(@Assisted RootWord rootWord,
                                         @Assisted("firstRadical") ArabicLetterType firstRadical,
                                         @Assisted("secondRadical") ArabicLetterType secondRadical,
                                         @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                         @Nullable @Assisted("fourthRadical") ArabicLetterType fourthRadical,
                                         @Assisted int variableIndex) {
        super(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical, variableIndex);
    }

    protected PartlyFlexibleNounTransformer(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        this(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical, LAST_LETTER);
    }

    @Override
    public RootWord doNominative() {
        return copyRootWord(rootWord, NOMINATIVE_PLURAL);
    }

    @Override
    public RootWord doAccusative() {
        RootWord target = copyRootWord(this.rootWord, ACCUSATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA);
        return target.withRootWord(arabicWord);
    }

    @Override
    public RootWord doGenitive() {
        return copyRootWord(doAccusative(), GENITIVE_PLURAL);
    }
}
