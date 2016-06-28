package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.ImperativeSecondPersonMasculineTransformer.getImperativeLetter;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.ImperativeSecondPersonMasculineTransformer.processImperative;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
class ImperativeSecondPersonFeminineTransformer extends ForbiddingSecondPersonFeminineTransformer {

    ArabicLetter imperativeLetter;

    ImperativeSecondPersonFeminineTransformer() {
        this(null);
    }

    ImperativeSecondPersonFeminineTransformer(ArabicLetter imperativeLetter) {
        this.imperativeLetter = imperativeLetter;
    }

    @Override
    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        final RootWord target = super.createRootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        imperativeLetter = getImperativeLetter(imperativeLetter, target);
        return target;
    }

    @Override
    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src, SarfTermType termType) {
        final RootWord target = super.processRules(ruleProcessor, src, termType);
        return processImperative(imperativeLetter, target);
    }

    @Override
    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src) {
        return processRules(ruleProcessor, src, IMPERATIVE);
    }
}
