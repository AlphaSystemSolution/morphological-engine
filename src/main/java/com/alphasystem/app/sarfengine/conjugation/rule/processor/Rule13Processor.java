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
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public class Rule13Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule13Processor(@Assisted NamedTemplate template,
                           @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                           @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, VERBAL_NOUN);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isSecondRadicalWaw()
                || !isPastTenseHasTransformed()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        ArabicLetter previousLetter = result.getLetter(secondRadicalIndex - 1);
        DiacriticType diacritic = getDiacritic(previousLetter);
        if (isKasra(diacritic)) {
            result.replaceLetter(secondRadicalIndex, YA);
            baseRootWord.setSecondRadical(result.getLetter(secondRadicalIndex));
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
