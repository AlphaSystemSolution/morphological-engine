package com.alphasystem.app.morphologicalengine.conjugation.transformer;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.util.PatternHelper;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public abstract class AbstractTransformer<G> implements Transformer<G>, ArabicLetters {

    protected final RuleProcessor ruleProcessor;

    protected AbstractTransformer(RuleProcessor ruleProcessor) {
        this.ruleProcessor = ruleProcessor;
    }

    protected static RootWord copyRootWord(RootWord rootWord, SarfMemberType memberType) {
        return new RootWord(rootWord).withMemberType(memberType);
    }

    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new RootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    protected RootWord processRules(RootWord src) {
        RootWord target = src;
        if (ruleProcessor != null) {
            target = ruleProcessor.applyRules(target);
            target = PatternHelper.doApplyPatterns(target);
        }
        return target;
    }
}
