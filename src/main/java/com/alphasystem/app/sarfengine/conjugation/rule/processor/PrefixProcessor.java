/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;

/**
 * @author sali
 */
public class PrefixProcessor extends AbstractRuleProcessor {

    private static final ArabicWord NEGATE = getWord(LAM, ALIF);

    @AssistedInject
    public PrefixProcessor(@Assisted NamedTemplate template,
                           @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                           @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, new SarfTermType[]{FORBIDDING}, null);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }

        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        result = ArabicWord.concatenateWithSpace(NEGATE, result);

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
