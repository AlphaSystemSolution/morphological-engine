package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.RootWord.convertToFeminine;

/**
 * @author sali
 */
class FeminineMasculineBasedPluralTransformer extends FemininePluralTransformer {

    FeminineMasculineBasedPluralTransformer() {
        super();
    }

    @Override
    protected RootWord createRootWord(RootWord rootWord, SarfTermType sarfTermType, ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        final ArabicWord arabicWord = rootWord.getRootWord();
        final ArabicLetter lastLetter = arabicWord.getLastLetter();
        if (ArabicLetterType.TA_MARBUTA.equals(lastLetter.getLetter())) {
            // for NounOfPlaceAndTime
            return super.createRootWord(rootWord, sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        } else {
            // for VerbalNoun
            return super.createRootWord(convertToFeminine(rootWord), sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
    }

}
