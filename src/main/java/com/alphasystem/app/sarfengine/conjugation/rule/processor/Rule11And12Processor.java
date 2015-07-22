/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
public class Rule11And12Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule11And12Processor(@Assisted NamedTemplate template,
                                @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{IMPERATIVE,
                    FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isDefective()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        ArabicLetterType thirdRadicalLetter = thirdRadical.getLetter();
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        int previousIndex = thirdRadicalIndex - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousLetterDiacritic = getDiacritic(previousLetter);
        if (isWaw(thirdRadicalLetter) && isKasra(previousLetterDiacritic)) {
            result.replaceLetter(thirdRadicalIndex, YA);
        } else if (isYa(thirdRadicalLetter) && isDamma(previousLetterDiacritic)) {
            result.replaceLetter(thirdRadicalIndex, WAW);
        }

        thirdRadical = result.getLetter(thirdRadicalIndex);
        baseRootWord.setThirdRadical(thirdRadical);
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
