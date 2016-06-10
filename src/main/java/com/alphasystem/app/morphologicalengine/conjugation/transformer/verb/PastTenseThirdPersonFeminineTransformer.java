package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PastTenseThirdPersonFeminineTransformer extends AbstractVerbTransformer {

    PastTenseThirdPersonFeminineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_SINGULAR);
        return processRules(ruleProcessor, target.withRootWord(target.getRootWord().append(TA_WITH_SUKUN)));
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_DUAL);
        return processRules(ruleProcessor, target.withRootWord(target.getRootWord().append(TA_WITH_FATHA, LETTER_ALIF)));
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_FEMININE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(NOON_WITH_FATHA);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
