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
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
public class DoubleLetteredProcessor extends AbstractRuleProcessor {

    /**
     * @param template
     */
    public DoubleLetteredProcessor(NamedTemplate template) {
        super(template);
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
        if (!wordStatus.isDouledLettered()) {
            return baseRootWord;
        }

        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        int firstLetterIndex = secondRadicalIndex;
        ArabicLetter firstLetter = result.getLetter(firstLetterIndex);
        ArabicLetter secondLetter = result.getLetter(baseRootWord
                .getThirdRadicalIndex());
        int indexOfLetterBeforeFirstLetter = firstLetterIndex - 1;
        ArabicLetter letterBeforeFirstLetter = result
                .getLetter(indexOfLetterBeforeFirstLetter);
        DiacriticType firstLetterDiacritic = getDiacritic(firstLetter);
        DiacriticType secondLetterDiacritic = getDiacritic(secondLetter);
        DiacriticType letterBeforeFirstLetterDiacritic = getDiacritic(letterBeforeFirstLetter);
        boolean activeFirstLetter = isMutaharik(firstLetterDiacritic);
        boolean activeSecondLetter = isMutaharik(secondLetterDiacritic);
        boolean activeLetterBeforeFirstLetter = isMutaharik(letterBeforeFirstLetterDiacritic);
        int maddaIndex = maddaIndex(result);
        boolean madda = maddaIndex == indexOfLetterBeforeFirstLetter;
        if (activeFirstLetter && activeSecondLetter) {
            if (activeLetterBeforeFirstLetter) {
                result.replaceDiacritic(firstLetterIndex, SUKUN);
            } else if (!activeLetterBeforeFirstLetter && !madda) {
                result.replaceDiacritic(firstLetterIndex, SUKUN)
                        .replaceDiacritic(indexOfLetterBeforeFirstLetter,
                                firstLetterDiacritic);
            } else if (madda) {
                result.replaceDiacritic(firstLetterIndex, SUKUN);
            }
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
