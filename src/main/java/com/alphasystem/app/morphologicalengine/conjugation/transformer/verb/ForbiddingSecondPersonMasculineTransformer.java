package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;


/**
 * @author sali
 */
class ForbiddingSecondPersonMasculineTransformer extends PresentTenseSecondPersonMasculineTransformer {

    ForbiddingSecondPersonMasculineTransformer() {
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = super.doSingular(rootWord);
        ArabicWord arabicWord = target.getRootWord();
        int lastLetterIndex = arabicWord.getLength() - 1;
        ArabicLetter lastLetter = arabicWord.getLetter(lastLetterIndex);
        DiacriticType[] diacritics = lastLetter.getDiacritics();
        if ((diacritics != null) && !isEmpty(diacritics)) {
            diacritics[diacritics.length - 1] = diacritics[0].equals(SHADDA) ? FATHA : SUKUN;
        }
        arabicWord.replaceDiacritic(lastLetterIndex, diacritics);
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = new RootWord(super.doDual(rootWord));
        target.getRootWord().removeLast();
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        final RootWord target = new RootWord(super.doPlural(rootWord));
        target.getRootWord().removeLast().append(LETTER_ALIF);
        return target;
    }
}
