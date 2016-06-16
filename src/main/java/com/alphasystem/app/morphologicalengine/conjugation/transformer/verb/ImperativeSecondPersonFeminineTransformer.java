package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.ImperativeSecondPersonMasculineTransformer.getImperativeLetter;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.ImperativeSecondPersonMasculineTransformer.processImperative;

/**
 * @author sali
 */
class ImperativeSecondPersonFeminineTransformer extends ForbiddingSecondPersonFeminineTransformer {

    ArabicLetter imperativeLetter;

    ImperativeSecondPersonFeminineTransformer() {
        this(null);
    }

    ImperativeSecondPersonFeminineTransformer(ArabicLetter imperativeLetter) {
        this.imperativeLetter = imperativeLetter;
    }

    @Override
    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        final RootWord target = super.createRootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        imperativeLetter = getImperativeLetter(imperativeLetter, target);
        return target;
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        return processImperative(imperativeLetter, new RootWord(super.doSingular(rootWord)));
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        return processImperative(imperativeLetter, new RootWord(super.doDual(rootWord)));
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        return processImperative(imperativeLetter, new RootWord(super.doPlural(rootWord)));
    }
}
