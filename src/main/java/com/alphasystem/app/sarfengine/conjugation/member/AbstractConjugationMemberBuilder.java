package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.sarfengine.xml.model.RootWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static com.alphasystem.app.sarfengine.util.PatternHelper.doApplyPatterns;
import static java.lang.String.format;

/**
 * @author sali
 */
public abstract class AbstractConjugationMemberBuilder implements ConjugationMemberBuilder {

    protected final NamedTemplate template;
    protected final boolean skipRuleProcessing;
    protected final RuleProcessor ruleProcessor;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected RootWord baseRootWord;

    protected AbstractConjugationMemberBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                               boolean skipRuleProcessing, RootWord baseRootWord) {
        this.template = template;
        this.skipRuleProcessing = skipRuleProcessing;
        this.ruleProcessor = ruleProcessor;
        this.baseRootWord = baseRootWord;
    }

    @PostConstruct
    public void postConstruct() {
        beforeConstruct();
        doConstruct();
        afterConstruct();
    }

    protected void beforeConstruct() {
    }

    protected void doConstruct() {
    }

    protected void afterConstruct() {
    }

    /**
     * Given a source {@link RootWord} this method creates a new copy and change the member type.
     *
     * @param src        Source {@link RootWord}
     * @param memberType New {@link SarfMemberType}
     * @return New copy of {@link RootWord}
     */
    protected RootWord createRootWord(RootWord src, SarfMemberType memberType) {
        return new RootWord(src).withMemberType(memberType);
    }

    /**
     * Creates a copy {@link AbstractConjugationMemberBuilder#baseRootWord} with new member type.
     *
     * @param memberType New {@link SarfMemberType}
     * @return New copy of {@link RootWord}
     */
    protected RootWord createRootWord(SarfMemberType memberType) {
        return createRootWord(getRootWord(), memberType);
    }

    protected RootWord doPostProcessConjugation(RootWord src) {
        if (src == null) {
            return null;
        }
        RootWord rootWord = new RootWord(src);
        if (!isSkipRuleProcessing()) {
            rootWord = ruleProcessor.applyRules(getTemplate(), rootWord);
            rootWord = doApplyPatterns(rootWord);
        }

        return rootWord;
    }

    @Override
    public NamedTemplate getTemplate() {
        return template;
    }

    @Override
    public boolean isSkipRuleProcessing() {
        return skipRuleProcessing;
    }

    public RootWord getRootWord() {
        return baseRootWord;
    }

    @Override
    public RuleProcessor getRuleProcessor() {
        return ruleProcessor;
    }

    @Override
    public String toString() {
        return format("%s: %s", getClass().getName(), getTermType());
    }
}
