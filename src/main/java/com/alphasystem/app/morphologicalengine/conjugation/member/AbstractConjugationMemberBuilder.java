package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * @author sali
 */
public abstract class AbstractConjugationMemberBuilder<G> implements ConjugationMemberBuilder<G> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final RuleProcessor ruleProcessor;

    protected AbstractConjugationMemberBuilder(RuleProcessor ruleProcessor) {
        this.ruleProcessor = ruleProcessor;
    }

    @PostConstruct
    public void postConstruct() {
        beforePostConstruct();
        doPostConstruct();
        afterPostConstruct();
    }

    protected void beforePostConstruct() {
    }

    protected void doPostConstruct() {
    }

    protected void afterPostConstruct() {
    }

    @Override
    public RuleProcessor getRuleProcessor() {
        return ruleProcessor;
    }

}
