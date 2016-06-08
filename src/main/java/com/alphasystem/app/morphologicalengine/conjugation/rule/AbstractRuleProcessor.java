package com.alphasystem.app.morphologicalengine.conjugation.rule;

import com.alphasystem.arabic.model.ArabicLetter;

import static com.alphasystem.arabic.model.ArabicLetters.LETTER_ASTERISK;

/**
 * @author sali
 */
public abstract class AbstractRuleProcessor implements RuleProcessor {

    public static final ArabicLetter REMOVE_MARKER = LETTER_ASTERISK;
    protected final RuleInfo ruleInfo;

    protected AbstractRuleProcessor(RuleInfo ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

}
