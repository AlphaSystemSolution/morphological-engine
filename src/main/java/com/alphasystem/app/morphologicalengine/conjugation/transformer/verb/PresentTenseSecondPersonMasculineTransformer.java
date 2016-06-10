package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public class PresentTenseSecondPersonMasculineTransformer extends PresentTenseThirdPersonFeminineTransformer {

    PresentTenseSecondPersonMasculineTransformer() {
        super();
    }

    @Override
    protected RootWord doSingular(RuleProcessor ruleProcessor, RootWord rootWord) {
        return new RootWord(super.doSingular(ruleProcessor, rootWord)).withMemberType(SECOND_PERSON_MASCULINE_SINGULAR);
    }

    @Override
    protected RootWord doDual(RuleProcessor ruleProcessor, RootWord rootWord) {
        return new RootWord(super.doDual(ruleProcessor, rootWord)).withMemberType(SECOND_PERSON_MASCULINE_DUAL);
    }

    @Override
    protected RootWord doPlural(RuleProcessor ruleProcessor, RootWord rootWord) {
        final RootWord target = copyRootWord(rootWord, SECOND_PERSON_MASCULINE_PLURAL);
        final ArabicWord arabicWord = target.getRootWord().replaceLetter(0, TA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
        return processRules(ruleProcessor, target.withRootWord(arabicWord));
    }
}
