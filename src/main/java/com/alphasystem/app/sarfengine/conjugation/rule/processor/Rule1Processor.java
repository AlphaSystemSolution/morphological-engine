/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_TATWEEL;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_PASSIVE_TENSE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_TENSE;
import static org.apache.commons.lang3.ArrayUtils.contains;

/**
 * @author sali
 */
public class Rule1Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule1Processor(@Assisted NamedTemplate template,
                          @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                          @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, PRESENT_TENSE, PRESENT_PASSIVE_TENSE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        DiacriticType diacriticOfImperfectSign = getDiacritic(result
                .getFirstLetter());
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isFirstRadicalWaw()
                || !isFatha(diacriticOfImperfectSign)) {
            return baseRootWord;
        }
        ArabicLetter secondRadical = baseRootWord.getSecondRadical();
        DiacriticType secondRadicalDiacritic = getDiacritic(secondRadical);
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        boolean containsHeavyLetters = contains(HEAVY_LETTERS,
                secondRadical.getLetter())
                || contains(HEAVY_LETTERS, thirdRadical.getLetter());
        if (isKasra(secondRadicalDiacritic)
                || (isFatha(secondRadicalDiacritic) && containsHeavyLetters)) {
            ArabicLetter firstRadical = LETTER_TATWEEL;
            result.replaceLetter(baseRootWord.getFirstRadicalIndex(),
                    firstRadical);
            baseRootWord.setFirstRadical(firstRadical);
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
