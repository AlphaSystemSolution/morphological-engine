package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PastTenseThirdPersonMasculineTransformer extends AbstractVerbTransformer {

    PastTenseThirdPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        return processRules(ruleProcessor, copyRootWord(rootWord, THIRD_PERSON_MASCULINE_SINGULAR));
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_DUAL);
        return processRules(ruleProcessor, target.withRootWord(target.getRootWord().append(LETTER_ALIF)));
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), DAMMA)
                .append(WAW_WITH_SUKUN, LETTER_ALIF);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
