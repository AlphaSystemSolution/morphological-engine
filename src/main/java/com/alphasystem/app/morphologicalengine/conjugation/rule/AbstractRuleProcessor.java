package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetters;

/**
 * @author sali
 */
public abstract class AbstractRuleProcessor implements RuleProcessor {

    public static final ArabicLetter REMOVE_MARKER = ArabicLetters.LETTER_FORWARD_SLASH;
    protected final RuleInfo ruleInfo;

    protected AbstractRuleProcessor(final RuleInfo ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

}
