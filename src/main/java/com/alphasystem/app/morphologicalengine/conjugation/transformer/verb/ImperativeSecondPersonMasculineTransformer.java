package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.mysema.util.ArrayUtils.isEmpty;

/**
 * @author sali
 */
class ImperativeSecondPersonMasculineTransformer extends ForbiddingSecondPersonMasculineTransformer {

    ArabicLetter imperativeLetter;

    ImperativeSecondPersonMasculineTransformer() {
        this(null);
    }

    ImperativeSecondPersonMasculineTransformer(ArabicLetter imperativeLetter) {
        this.imperativeLetter = imperativeLetter;
    }

    static ArabicLetter getImperativeLetter(ArabicLetter imperativeLetter, RootWord rootWord) {
        if (imperativeLetter != null) {
            return imperativeLetter;
        }

        ArabicLetter secondRadical = rootWord.getSecondRadical();
        DiacriticType[] diacritics = secondRadical.getDiacritics();
        DiacriticType secondRadicalDiacritic = isEmpty(diacritics) ? FATHA : diacritics[0];
        DiacriticType imperativeDiacritic = secondRadicalDiacritic.equals(DAMMA) ? DAMMA : KASRA;
        imperativeLetter = imperativeDiacritic.equals(DAMMA) ? ALIF_HAMZA_ABOVE_WITH_DAMMA
                : ALIF_HAMZA_BELOW_WITH_KASRA;
        ArabicLetter firstRadical = rootWord.getFirstRadical();
        diacritics = firstRadical.getDiacritics();
        DiacriticType firstLetterDiacritics = isEmpty(diacritics) ? SUKUN : firstRadical.getDiacritics()[0];
        if (!firstLetterDiacritics.equals(SUKUN) && !firstLetterDiacritics.equals(SHADDA)) {
            imperativeLetter = null;
        }

        return imperativeLetter;
    }

    static RootWord processImperative(ArabicLetter imperativeLetter, RootWord src) {
        if (imperativeLetter != null) {
            ArabicWord result = src.getRootWord().remove(0);
            if (imperativeLetter != null) {
                result.preppend(imperativeLetter);
            }
        }
        return src;
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
