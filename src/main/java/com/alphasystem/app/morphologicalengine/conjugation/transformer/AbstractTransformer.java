package com.alphasystem.app.morphologicalengine.conjugation.transformer;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.util.PatternHelper;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public abstract class AbstractTransformer<G> implements Transformer<G>, ArabicLetters {

    protected static RootWord copyRootWord(RootWord rootWord, SarfMemberType memberType) {
        return new RootWord(rootWord).withMemberType(memberType);
    }

    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new RootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src) {
        RootWord target = src;
        if (ruleProcessor != null && target != null) {
            target = ruleProcessor.applyRules(target);
            target = PatternHelper.doApplyPatterns(target);
        }
        return target;
    }
}
