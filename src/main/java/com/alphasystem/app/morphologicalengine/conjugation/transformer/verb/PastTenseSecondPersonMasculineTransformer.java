package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PastTenseSecondPersonMasculineTransformer extends AbstractVerbTransformer {

    PastTenseSecondPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_SINGULAR);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_FATHA);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_DUAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_DAMMA, MEEM_WITH_FATHA, LETTER_ALIF);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(TA_WITH_DAMMA, MEEM_WITH_SUKUN);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
