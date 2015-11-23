package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public abstract class AbstractParticipleMemberBuilder extends AbstractConjugationMemberBuilder
        implements ParticipleMemberBuilder {

    protected int variableLetterIndex;

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                              boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                              ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                              ArabicLetterType fourthRadical, RootWord baseRootWord,
                                              int variableLetterIndex) throws NullPointerException {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical,
                fourthRadical, baseRootWord);
        setVariableLetterIndex(variableLetterIndex);
    }

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                              boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                              ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                              ArabicLetterType fourthRadical, int variableLetterIndex)
            throws NullPointerException {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical,
                fourthRadical, null, variableLetterIndex);
    }

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                              boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                              ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                              RootWord baseRootWord, int variableLetterIndex)
            throws NullPointerException {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null,
                baseRootWord, variableLetterIndex);
    }

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                              boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                              ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                              int variableLetterIndex) throws NullPointerException {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null, null,
                variableLetterIndex);
    }

    protected void setVariableLetterIndex(int variableLetterIndex) {
        this.variableLetterIndex = variableLetterIndex <= -1 ? getRootWord().getThirdRadicalIndex() : variableLetterIndex;
    }

    protected int getVariableLetterIndex() {
        return variableLetterIndex;
    }

    @Override
    public RootWord[] doConjugation() {
        RootWord[] conjugations = new RootWord[9];

        conjugations[2] = nominativeSingular();
        conjugations[1] = nominativeDual();
        conjugations[0] = nominativePlural();

        conjugations[5] = accusativeSingular();
        conjugations[4] = accusativeDual();
        conjugations[3] = accusativePlural();

        conjugations[8] = genitiveSingular();
        conjugations[7] = genitiveDual();
        conjugations[6] = genitivePlural();

        return conjugations;
    }

    @Override
    public RootWord getDefaultConjugation() {
        return new RootWord(nominativeSingular());
    }

    @Override
    public RootWord nominativeSingular() {
        return doPostProcessConjugation(doNominativeSingular(createRootWord(NOMINATIVE_SINGULAR)));
    }

    @Override
    public RootWord nominativeDual() {
        return doPostProcessConjugation(doNominativeDual(createRootWord(NOMINATIVE_DUAL)));
    }

    @Override
    public RootWord nominativePlural() {
        return doPostProcessConjugation(doNominativePlural(createRootWord(NOMINATIVE_PLURAL)));
    }

    @Override
    public RootWord accusativeSingular() {
        return doPostProcessConjugation(doAccusativeSingular(createRootWord(ACCUSATIVE_SINGULAR)));
    }

    @Override
    public RootWord accusativeDual() {
        return doPostProcessConjugation(doAccusativeDual(createRootWord(ACCUSATIVE_DUAL)));
    }

    @Override
    public RootWord accusativePlural() {
        return doPostProcessConjugation(doAccusativePlural(createRootWord(ACCUSATIVE_PLURAL)));
    }

    @Override
    public RootWord genitiveSingular() {
        return doPostProcessConjugation(doGenitiveSingular(createRootWord(GENITIVE_SINGULAR)));
    }

    @Override
    public RootWord genitiveDual() {
        return doPostProcessConjugation(doGenitiveDual(createRootWord(GENITIVE_DUAL)));
    }

    @Override
    public RootWord genitivePlural() {
        return doPostProcessConjugation(doGenitivePlural(createRootWord(GENITIVE_PLURAL)));
    }

    protected RootWord doNominativeSingular(RootWord rootWord) {
        return new RootWord(rootWord);
    }

    protected abstract RootWord doNominativeDual(RootWord rootWord);

    protected abstract RootWord doNominativePlural(RootWord rootWord);

    protected abstract RootWord doAccusativeSingular(RootWord rootWord);

    protected abstract RootWord doAccusativeDual(RootWord rootWord);

    protected abstract RootWord doAccusativePlural(RootWord rootWord);

    protected abstract RootWord doGenitiveSingular(RootWord rootWord);

    protected RootWord doGenitiveDual(RootWord rootWord) {
        return doAccusativeDual(rootWord);
    }

    protected RootWord doGenitivePlural(RootWord rootWord) {
        return doAccusativePlural(rootWord);
    }
}
