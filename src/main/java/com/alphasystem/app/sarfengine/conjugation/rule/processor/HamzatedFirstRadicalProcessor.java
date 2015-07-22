/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_TATWEEL;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
public class HamzatedFirstRadicalProcessor extends AbstractRuleProcessor {

    @AssistedInject
    public HamzatedFirstRadicalProcessor(@Assisted NamedTemplate template,
                                         @Nullable @Assisted DiacriticType diacriticForWeakSecondRadicalWaw,
                                         @Assisted boolean pastTenseHasTransformed) {
        super(template, diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, IMPERATIVE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        // By the time we come here "HAMZA" is already transformed, so use
        // baseword to determine wether we have first radical hamza.
        ArabicWord baseWord = baseRootWord.getBaseWord();
        RootWord rw = new RootWord(baseRootWord);
        rw.setRootWord(baseWord);
        WordStatus wordStatus = new WordStatus(rw);
        if (wordStatus.isFirstRadicalHamza()
                && FORM_I_CATEGORY_A_GROUP_U_TEMPLATE.equals(template)) {
            ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
            result.replaceLetter(0, LETTER_TATWEEL);
            if (!wordStatus.isSecondRadicalWaw()) {
                result.replaceLetter(1, LETTER_TATWEEL);
            }
            baseRootWord.setRootWord(result);
        }
        return baseRootWord;
    }

}
