package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.morphologicalanalysis.morphology.model.RootWord.convertToFeminine;

/**
 * @author sali
 */
class FeminineMasculineBasedPluralTransformer extends FemininePluralTransformer {

    FeminineMasculineBasedPluralTransformer() {
        super();
    }

    @Override
    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return super.createRootWord(convertToFeminine(rootWord), firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

}
