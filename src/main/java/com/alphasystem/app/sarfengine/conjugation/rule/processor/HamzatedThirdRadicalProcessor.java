/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.maddaIndex;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class HamzatedThirdRadicalProcessor extends AbstractRuleProcessor {

    @AssistedInject
    public HamzatedThirdRadicalProcessor(@Assisted NamedTemplate template,
                                         @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                         @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, PASSIVE_PARTICIPLE_FEMININE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isThirdRadicalHamza()) {
            return baseRootWord;
        }
        return processFemininePassiveParticiple(baseRootWord);
    }

    private RootWord processFemininePassiveParticiple(RootWord baseRootWord) {
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int maddaIndex = maddaIndex(result);
        if (maddaIndex > -1) {
            ArabicLetter madda = result.getLetter(maddaIndex);
            ArabicLetterType maddaLetter = madda.getLetter();
            boolean isHamzah = false;
            try {
                ArabicLetter nextLetter = result.getLetter(maddaIndex + 1);
                isHamzah = nextLetter.getLetter().equals(HAMZA);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            if ((maddaLetter.equals(WAW) || maddaLetter.equals(YA)) && isHamzah) {
                result.replaceLetter(baseRootWord.getThirdRadicalIndex(),
                        maddaLetter);
            }
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }
}
