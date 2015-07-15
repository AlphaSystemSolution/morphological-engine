/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_VIII_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
public class FormVIIIProcessor extends AbstractRuleProcessor {

    /**
     * @param template
     */
    public FormVIIIProcessor(NamedTemplate template) {
        super(template);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        if (FORM_VIII_TEMPLATE.equals(template)) {
            SarfTermType sarfTermType = baseRootWord.getSarfTermType();
            if (sarfTermType.equals(IMPERATIVE)
                    || sarfTermType.equals(FORBIDDING)) {
                return baseRootWord;
            }
            ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
            ArabicLetterType firstRadicalLetter = baseRootWord
                    .getFirstRadical().getLetter();
            @SuppressWarnings("unused")
            ArabicLetterType secondRadicalLetter = baseRootWord
                    .getSecondRadical().getLetter();
            int firstRadicalIndex = baseRootWord.getFirstRadicalIndex();
            switch (firstRadicalLetter) {
                case HAMZA:
                case WAW:
                case YA:
                    result.replaceLetter(firstRadicalIndex, TA);
                    baseRootWord.setFirstRadical(result
                            .getLetter(firstRadicalIndex));
                    break;
                case DAL:
                case THAL:
                case ZAIN:
                    result.replaceLetter(2, DAL);
                    break;
                case SAD:
                case DDAD:
                case TTA:
                case DTHA:
                    result.replaceLetter(2, TTA);
                    break;
                default:
                    break;
            }

            baseRootWord.setRootWord(result);
        }
        return baseRootWord;
    }
}
