package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_PLURAL;

/**
 * @author sali
 */
public class PresentTenseThirdPersonFeminineTransformer extends PresentTenseThirdPersonMasculineTransformer {

    PresentTenseThirdPersonFeminineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = super.doSingular(ruleProcessor, rootWord);
        return processRules(ruleProcessor, target.withRootWord(target.getRootWord().replaceLetter(0, TA)));
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = super.doDual(ruleProcessor, rootWord);
        return processRules(ruleProcessor, target.withRootWord(target.getRootWord().replaceLetter(0, TA)));
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, THIRD_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceDiacritic(target.getThirdRadicalIndex(), SUKUN)
                .append(NOON_WITH_FATHA);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
