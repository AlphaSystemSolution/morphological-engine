package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_PLURAL;
import static com.alphasystem.arabic.model.HiddenPronounStatus.FIRST_PERSON_SINGULAR;

/**
 * @author sali
 */
class PastTenseFirstPersonTransformer extends AbstractVerbTransformer {

    PastTenseFirstPersonTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_SINGULAR);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_DAMMA);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        return null;
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, FIRST_PERSON_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(NOON_WITH_FATHA, LETTER_ALIF);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
