/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class Rule17Processor extends AbstractRuleProcessor {

    private final RuleProcessor parent;

    /**
     * @param template
     */
    public Rule17Processor(NamedTemplate template, RuleProcessor parent) {
        super(template);
        this.parent = parent;
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, ACTIVE_PARTICIPLE_MASCULINE,
                    ACTIVE_PARTICIPLE_FEMININE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isHollow() || !parent.isPastTenseHasTransformed()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        result.replaceLetter(secondRadicalIndex, HAMZA);
        baseRootWord.setSecondRadical(result.getLetter(secondRadicalIndex));
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
