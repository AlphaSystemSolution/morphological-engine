/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_TATWEEL;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static org.apache.commons.lang3.ArrayUtils.contains;

/**
 * @author sali
 */
public class Rule16Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule16Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(NamedTemplate template, RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, VERB_BASED_TYPES);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isDefective()
                && !contains(NOUN_BASED_TYPES, sarfTermType)) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        int previousIndex = thirdRadicalIndex - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousDiacritic = getDiacritic(previousLetter);
        boolean previousDiacriticDamma = isDamma(previousDiacritic);
        if ((wordStatus.isThirdRadicalWaw() && previousDiacriticDamma)
                || (wordStatus.isThirdRadicalYa() && previousDiacriticDamma)) {
            result.replaceLetter(thirdRadicalIndex, LETTER_TATWEEL)
                    .replaceDiacritic(previousIndex, KASRATAN);
            baseRootWord.setThirdRadical(LETTER_TATWEEL);
            if (previousIndex == baseRootWord.getSecondRadicalIndex()) {
                baseRootWord.setSecondRadical(result.getLetter(previousIndex));
            }
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
