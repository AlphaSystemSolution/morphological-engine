/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

;

/**
 * @author sali
 */
public class HamzatedFirstRadicalProcessor extends AbstractRuleProcessor {

    public HamzatedFirstRadicalProcessor(final RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, IMPERATIVE);
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        // By the time we come here "HAMZA" is already transformed, so use
        // base word to determine whether we have first radical hamza.
        ArabicWord baseWord = baseRootWord.getBaseWord();
        RootWord rw = new RootWord(baseRootWord);
        rw.setRootWord(baseWord);
        final WordStatus wordStatus = ruleInfo.getWordStatus();
        // Page 159 of Treasure of Arabic Morphology
        if (wordStatus.isFirstRadicalHamza() && FORM_I_CATEGORY_A_GROUP_U_TEMPLATE.equals(ruleInfo.getTemplate())) {
            ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
            result.replaceLetter(0, REMOVE_MARKER);
            baseRootWord.setRootWord(result);
        }
        return baseRootWord;
    }

}
