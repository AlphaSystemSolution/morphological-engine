package com.alphasystem.app.sarfengine.conjugation.rule;

/**
 * @author sali
 */
public abstract class AbstractRuleProcessor implements RuleProcessor {
    protected final RuleInfo ruleInfo;

    protected AbstractRuleProcessor(RuleInfo ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

}
