/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alphasystem.app.sarfengine.util.PatternHelper.doApplyPatterns;
import static java.lang.String.format;

/**
 * @author sali
 */
public abstract class AbstractConjugationMemberBuilder implements
        ConjugationMemberBuilder {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected ArabicWord rootWord;

    protected ArabicLetter firstRadical;

    protected ArabicLetter secondRadical;

    protected ArabicLetter thirdRadical;

    protected ArabicLetter fourthRadical;

    protected ArabicLetterType firstRadicalLetter;

    protected ArabicLetterType secondRadicalLetter;

    protected ArabicLetterType thirdRadicalLetter;

    protected ArabicLetterType fourthRadicalLetter;

    protected int firstRadicalIndex = -1;

    protected int secondRadicalIndex = -1;

    protected int thirdRadicalIndex = -1;

    protected int fourthRadicalIndex = -1;

    protected RootWord baseRootWord;

    protected RuleProcessor ruleProcessor;

    protected NamedTemplate template;

    protected boolean skipRuleProcessing = false;

    protected ConjugationMember defaultConjugation;

    protected AbstractConjugationMemberBuilder() {
    }

    public void initMemberBuilder(NamedTemplate template, boolean skipRuleProcessing, RootWord rootWord) {
        setTemplate(template);
        setSkipRuleProcessing(skipRuleProcessing);
        setRootWord(new RootWord(rootWord));
    }

    protected abstract void initDefaultConjugation();

    @Override
    public ConjugationMember getDefaultConjugation() {
        return defaultConjugation;
    }

    /**
     * @param memberType
     * @param src
     * @return
     */
    protected RootWord createRootWord(SarfMemberType memberType, ArabicWord src) {
        return new RootWord(getRootWord()).withMemberType(memberType)
                .withRootWord(new ArabicWord(src));
    }

    protected ArabicWord doPostProcessConjugation(SarfMemberType memberType,
                                                  ArabicWord src) {
        ArabicWord result = new ArabicWord(src);
        if (result != null) {
            RootWord baseRootWord = createRootWord(memberType, result);
            if (!skipRuleProcessing) {
                result = new ArabicWord(executeRules(baseRootWord));
                baseRootWord.setRootWord(result);
                result = new ArabicWord(doApplyPatterns(baseRootWord)
                        .getRootWord());
            }
        }
        return result;
    }

    private ArabicWord executeRules(RootWord baseRootWord) {
        initRuleProcessor();
        baseRootWord = ruleProcessor.applyRules(baseRootWord);
        return new ArabicWord(baseRootWord.getRootWord());
    }

    public RootWord getRootWord() {
        return baseRootWord;
    }

    /**
     * Sets the root word of this conjugation. All words will be conjugated from
     * this word, either by changing the ending or changing the structure.
     *
     * @param rootWord
     */
    public void setRootWord(RootWord rootWord) {
        this.baseRootWord = rootWord;
        this.firstRadical = rootWord.getFirstRadical();
        this.secondRadical = rootWord.getSecondRadical();
        this.thirdRadical = rootWord.getThirdRadical();
        this.fourthRadical = rootWord.getFourthRadical();
        this.firstRadicalLetter = this.firstRadical.getLetter();
        this.firstRadicalIndex = rootWord.getFirstRadicalIndex();
        this.secondRadicalLetter = this.secondRadical.getLetter();
        this.secondRadicalIndex = rootWord.getSecondRadicalIndex();
        this.thirdRadicalLetter = this.thirdRadical.getLetter();
        this.thirdRadicalIndex = rootWord.getThirdRadicalIndex();
        if (this.fourthRadical != null) {
            this.fourthRadicalLetter = this.fourthRadical.getLetter();
            this.fourthRadicalIndex = rootWord.getFourthRadicalIndex();
        }
        this.rootWord = rootWord.getRootWord();
        initDefaultConjugation();
        postInit();
    }

    public ArabicLetter getFirstRadical() {
        return firstRadical;
    }

    public int getFirstRadicalIndex() {
        return firstRadicalIndex;
    }

    public ArabicLetterType getFirstRadicalLetter() {
        return firstRadicalLetter;
    }

    public ArabicLetter getFourthRadical() {
        return fourthRadical;
    }

    public int getFourthRadicalIndex() {
        return fourthRadicalIndex;
    }

    public ArabicLetterType getFourthRadicalLetter() {
        return fourthRadicalLetter;
    }

    @Override
    public RuleProcessor getRuleProcessor() {
        return ruleProcessor;
    }

    @Override
    public void setRuleProcessor(RuleProcessor ruleProcessor) {
        this.ruleProcessor = ruleProcessor;
    }

    public ArabicLetter getSecondRadical() {
        return secondRadical;
    }

    public int getSecondRadicalIndex() {
        return secondRadicalIndex;
    }

    public ArabicLetterType getSecondRadicalLetter() {
        return secondRadicalLetter;
    }

    @Override
    public NamedTemplate getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(NamedTemplate template) {
        this.template = template;
    }

    public ArabicLetter getThirdRadical() {
        return thirdRadical;
    }

    public int getThirdRadicalIndex() {
        return thirdRadicalIndex;
    }

    public ArabicLetterType getThirdRadicalLetter() {
        return thirdRadicalLetter;
    }

    protected void initRuleProcessor() {
        if (logger.isDebugEnabled()) {
            logger.debug("initRuleProcessor Enter");
        }
        if (ruleProcessor == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("initializing");
            }
            //ruleProcessor = getInstance().getRuleProcessor(template);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("initRuleProcessor Exit");
        }
    }

    @Override
    public boolean isSkipRuleProcessing() {
        return skipRuleProcessing;
    }

    @Override
    public void setSkipRuleProcessing(boolean skip) {
        skipRuleProcessing = skip;
    }

    @SuppressWarnings("unused")
    private boolean isWeak() {
        RootWord copy = new RootWord(baseRootWord);
        copy.setRootWord(baseRootWord.getBaseWord());
        WordStatus status = new WordStatus(copy);
        return status.isWeak() || status.isHamzatted();
    }

    protected void postInit() {
        logger.debug("Inside postInit");
        initRuleProcessor();
    }

    @Override
    public String toString() {
        return format("%s: %s", getClass().getName(), getTermType());
    }
}
