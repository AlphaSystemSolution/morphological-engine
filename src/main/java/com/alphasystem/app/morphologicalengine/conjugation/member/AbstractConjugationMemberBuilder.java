package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * @author sali
 */
public abstract class AbstractConjugationMemberBuilder<G, B> implements ConjugationMemberBuilder<G> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected G conjugationGroup;
    protected final RuleProcessor ruleProcessor;
    protected final B rootBase;
    protected final RootLetters rootLetters;

    protected AbstractConjugationMemberBuilder(RuleProcessor ruleProcessor, B rootBase, RootLetters rootLetters) {
        this.ruleProcessor = ruleProcessor;
        this.rootLetters = rootLetters;
        this.rootBase = rootBase;
    }

    protected AbstractConjugationMemberBuilder(RuleProcessor ruleProcessor, Form form, RootLetters rootLetters){
        this.ruleProcessor = ruleProcessor;
        this.rootLetters = rootLetters;
        this.rootBase = getRootBase(form);
    }

    @PostConstruct
    public void postConstruct() {
        beforePostConstruct();
        doPostConstruct();
        afterPostConstruct();
    }

    protected abstract B getRootBase(Form form);

    protected abstract void initializeTransformers();

    protected void beforePostConstruct() {
        initializeTransformers();
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
