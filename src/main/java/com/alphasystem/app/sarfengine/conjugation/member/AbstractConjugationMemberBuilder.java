package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.sarfengine.xml.model.RootWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static com.alphasystem.app.sarfengine.conjugation.template.FormTemplate.getByNamedTemplate;
import static com.alphasystem.app.sarfengine.guice.GuiceSupport.getInstance;
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

    /**
     * @param template
     * @param skipRuleProcessing
     * @param firstRadical
     * @param secondRadical
     * @param thirdRadical
     * @param fourthRadical
     * @param baseRootWord       Base Root Word for Verbal Noun
     * @throws NullPointerException
     */
    protected AbstractConjugationMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                               ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                               ArabicLetterType thirdRadical, ArabicLetterType fourthRadical,
                                               RootWord baseRootWord) throws NullPointerException {
        this.template = template;
        this.skipRuleProcessing = skipRuleProcessing;
        this.ruleProcessor = getInstance().getRuleProcessor(template, null, false, null);
        RootWord templateWord = getByNamedTemplate(template).getTemplateWord(getTermType());
        templateWord = baseRootWord == null ? templateWord : baseRootWord;
        if (templateWord == null) {
            throw new NullPointerException(format("No Sarf Term {%s} found for template {%s}", getTermType(), getTemplate()));
        }
        this.baseRootWord = new RootWord(templateWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    protected AbstractConjugationMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                               ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                               ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        this(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, fourthRadical, null);
    }

    protected AbstractConjugationMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                               ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                               ArabicLetterType thirdRadical, RootWord baseRootWord) {
        this(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null, baseRootWord);
    }

    protected AbstractConjugationMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                               ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                               ArabicLetterType thirdRadical) {
        this(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null, null);
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
            rootWord = ruleProcessor.applyRules(rootWord);
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
