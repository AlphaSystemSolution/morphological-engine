package com.alphasystem.app.morphologicalengine.conjugation.transformer;

import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public abstract class AbstractTransformer<G> implements Transformer<G>, ArabicLetters {

    protected static RootWord copyRootWord(RootWord rootWord, SarfMemberType memberType) {
        return new RootWord(rootWord).withMemberType(memberType);
    }

    protected RootWord createRootWord(RootWord rootWord, SarfTermType sarfTermType, ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new RootWord(rootWord, firstRadical, secondRadical, thirdRadical, fourthRadical).withSarfTermType(sarfTermType);
    }

    protected RootWord processRules(RuleProcessor ruleProcessor, RootWord src) {
        RootWord target = src;
        if (ruleProcessor != null && target != null) {
            target = ruleProcessor.applyRules(target);
        }
        return target;
    }

    protected String toStringValue(RootWord rootWord, OutputFormat outputFormat) {
        if (rootWord == null) {
            return null;
        }
        outputFormat = (outputFormat == null) ? OutputFormat.UNICODE : outputFormat;
        String value = null;
        final ArabicWord label = rootWord.toLabel();
        switch (outputFormat) {
            case HTML:
                value = label.toHtmlCode();
                break;
            case BUCK_WALTER:
                value = label.toBuckWalter();
                break;
            default:
                value = label.toUnicode();
                break;
        }
        return value;
    }
}
