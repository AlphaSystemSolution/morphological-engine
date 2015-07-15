/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public class Rule13Processor extends AbstractRuleProcessor {

    private final RuleProcessor parent;

    public Rule13Processor(NamedTemplate template, RuleProcessor parent) {
        super(template);
        this.parent = parent;
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
                || !parent.isPastTenseHasTransformed()) {
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
