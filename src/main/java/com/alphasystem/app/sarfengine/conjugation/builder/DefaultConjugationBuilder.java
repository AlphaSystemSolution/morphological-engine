package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.sarfengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.*;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.AdverbLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ImperativeAndForbiddingLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.PassiveLine;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sali
 */
public class DefaultConjugationBuilder implements ConjugationBuilder {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    private static final MemberBuilderFactory MEMBER_BUILDER_FACTORY = GUICE_SUPPORT.getMemberBuilderFactory();


    public DefaultConjugationBuilder() {
    }

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                   boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                   ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                   ArabicLetterType fourthRadical, List<RootWord> verbalNouns, List<RootWord> adverbs) {
        FormTemplate formTemplate = FormTemplate.getByNamedTemplate(template);
        RuleProcessor ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(template));

        SarfKabeer sarfKabeer = createSarfKabeer(formTemplate, ruleEngine, removePassiveLine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical, verbalNouns, adverbs);

        SarfSagheer sarfSagheer = createSarfSagheer(sarfKabeer);

        return null;
    }

    @Override
    public SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                   boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                   ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                   List<RootWord> verbalNouns, List<RootWord> adverbs) {
        return doConjugation(template, translation, removePassiveLine, skipRuleProcessing, firstRadical, secondRadical,
                thirdRadical, null, verbalNouns, adverbs);
    }

    private SarfKabeerPair createActiveTensePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                 boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                 ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder = null;
        ConjugationMemberBuilder leftSideBuilder = null;

        RootWord rightSideRootWord = formTemplate.getPastTenseRoot();
        rightSideRootWord = processReplacements(rightSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPastTenseBuilder(ruleEngine, skipRuleProcessing,
                    rightSideRootWord);
        } else {
            //TODO:
        }

        RootWord leftSideRootWord = formTemplate.getPresentTenseRoot();
        leftSideRootWord = processReplacements(leftSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPresentTenseBuilder(ruleEngine, skipRuleProcessing,
                    leftSideRootWord);
        } else {
            //TODO:
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private SarfKabeerPair createActiveParticiplePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                      boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                      ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder = null;
        ConjugationMemberBuilder leftSideBuilder = null;

        RootWord rightSideRootWord = formTemplate.getActiveParticipleMasculineRoot();
        rightSideRootWord = processReplacements(rightSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralActiveParticipleMasculineBuilder(ruleEngine,
                    skipRuleProcessing, rightSideRootWord);
        } else {
            //TODO:
        }

        RootWord leftSideRootWord = formTemplate.getActiveParticipleFeminineRoot();
        leftSideRootWord = processReplacements(leftSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralActiveParticipleFeminineBuilder(ruleEngine,
                    skipRuleProcessing, leftSideRootWord);
        } else {
            //TODO:
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private SarfKabeerPair createPassiveTensePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                  boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                  ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                  ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder = null;
        ConjugationMemberBuilder leftSideBuilder = null;

        RootWord rightSideRootWord = formTemplate.getPastPassiveTenseRoot();
        rightSideRootWord = processReplacements(rightSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPastPassiveBuilder(ruleEngine, skipRuleProcessing,
                    rightSideRootWord);
        } else {
            //TODO:
        }

        RootWord leftSideRootWord = formTemplate.getPresentPassiveTenseRoot();
        leftSideRootWord = processReplacements(leftSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPresentPassiveBuilder(ruleEngine, skipRuleProcessing,
                    leftSideRootWord);
        } else {
            //TODO:
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private SarfKabeerPair createPassiveParticiplePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                       boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                       ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                       ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder = null;
        ConjugationMemberBuilder leftSideBuilder = null;

        RootWord rightSideRootWord = formTemplate.getPassiveParticipleMasculineRoot();
        rightSideRootWord = processReplacements(rightSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPassiveParticipleMasculineBuilder(ruleEngine,
                    skipRuleProcessing, rightSideRootWord);
        } else {
            //TODO:
        }

        RootWord leftSideRootWord = formTemplate.getPassiveParticipleFeminineRoot();
        leftSideRootWord = processReplacements(leftSideRootWord, firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPassiveParticipleFeminineBuilder(ruleEngine,
                    skipRuleProcessing, leftSideRootWord);
        } else {
            //TODO:
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private SarfKabeer createSarfKabeer(FormTemplate formTemplate, RuleProcessor ruleEngine, boolean removePassiveLine,
                                        boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                        ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                        ArabicLetterType fourthRadical, List<RootWord> verbalNouns,
                                        List<RootWord> adverbs) {
        SarfKabeerPair activeTensePair = createActiveTensePair(formTemplate, ruleEngine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical);

        //TODO: VerbalNouns
        SarfKabeerPair[] verbalNounPairs = null;

        SarfKabeerPair activeParticiplePair = createActiveParticiplePair(formTemplate, ruleEngine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical);

        SarfKabeerPair passiveTensePair = null;
        SarfKabeerPair passiveParticiplePair = null;

        if (!removePassiveLine) {
            passiveTensePair = createPassiveTensePair(formTemplate, ruleEngine, skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical, fourthRadical);

            passiveParticiplePair = createPassiveParticiplePair(formTemplate, ruleEngine, skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }

        // TODO: Imperative & Forbidding
        SarfKabeerPair imperativeAndForbiddingPair = null;

        // TODO: Adverbs
        SarfKabeerPair[] adverbPairs = null;

        return new SarfKabeer(activeTensePair, verbalNounPairs, activeParticiplePair, passiveTensePair,
                passiveParticiplePair, imperativeAndForbiddingPair, adverbPairs);
    }

    private SarfKabeerPair createSarfKabeerPair(ConjugationMemberBuilder leftSideBuilder,
                                                ConjugationMemberBuilder rightSideBuilder,
                                                RootWord leftSideRootWord,
                                                RootWord rightSideRootWord) {
        if (leftSideRootWord == null && rightSideRootWord == null) {
            return null;
        }
        SarfTermType leftSideTerm = null;
        if (leftSideRootWord != null) {
            leftSideTerm = leftSideRootWord.getSarfTermType();
        }
        SarfTermType rightSideTerm = null;
        if (rightSideRootWord != null) {
            rightSideTerm = rightSideRootWord.getSarfTermType();
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideTerm, rightSideTerm);
    }

    private SarfKabeerPair createSarfKabeerPair(ConjugationMemberBuilder leftSideBuilder,
                                                ConjugationMemberBuilder rightSideBuilder,
                                                SarfTermType leftSideTerm, SarfTermType rightSideTerm) {
        RootWord[] rightSideRootWords = null;
        RootWord rightSideDefaultValue = null;
        RootWord[] leftSideRootWords = null;
        RootWord leftSideDefaultValue = null;
        if (rightSideBuilder != null) {
            rightSideRootWords = rightSideBuilder.doConjugation();
            rightSideDefaultValue = rightSideBuilder.getDefaultConjugation();
        }
        if (leftSideBuilder != null) {
            leftSideRootWords = leftSideBuilder.doConjugation();
            leftSideDefaultValue = leftSideBuilder.getDefaultConjugation();
        }
        if (rightSideRootWords == null && leftSideRootWords == null) {
            return null;
        }
        if (rightSideRootWords == null && leftSideRootWords != null) {
            rightSideRootWords = new RootWord[leftSideRootWords.length];
        }
        if (leftSideRootWords == null && rightSideRootWords != null) {
            leftSideRootWords = new RootWord[rightSideRootWords.length];
        }
        return new SarfKabeerPair(
                new ConjugationStack(leftSideRootWords, leftSideDefaultValue, leftSideTerm),
                new ConjugationStack(rightSideRootWords, rightSideDefaultValue, rightSideTerm));
    }

    private SarfSagheer createSarfSagheer(SarfKabeer sarfKabeer) {
        ActiveLine activeLine = createActiveLine(sarfKabeer);
        PassiveLine passiveLine = createPassiveLine(sarfKabeer);
        ImperativeAndForbiddingLine imperativeAndForbidding = createImperativeAndForbidding(sarfKabeer);
        AdverbLine adverbLine = createAdverbLine(sarfKabeer);
        return new SarfSagheer(activeLine, passiveLine, imperativeAndForbidding, adverbLine);
    }

    private ActiveLine createActiveLine(SarfKabeer sarfKabeer) {
        SarfKabeerPair activeTensePair = sarfKabeer.getActiveTensePair();
        SarfKabeerPair activeParticiplePair = sarfKabeer.getActiveParticiplePair();
        SarfKabeerPair[] verbalNounPairs = sarfKabeer.getVerbalNounPairs();

        ConjugationStack rightSideStack = activeTensePair.getRightSideStack();
        ConjugationStack leftSideStack = activeTensePair.getLeftSideStack();
        RootWord pastTense = null;
        RootWord presentTense = null;
        if (rightSideStack != null) {
            pastTense = rightSideStack.getDefaultValue();
        }
        if (leftSideStack != null) {
            presentTense = leftSideStack.getDefaultValue();
        }

        rightSideStack = activeParticiplePair.getRightSideStack();
        RootWord activeParticipleMasculine = null;
        if (rightSideStack != null) {
            activeParticipleMasculine = rightSideStack.getDefaultValue();
        }

        return new ActiveLine(pastTense, presentTense, activeParticipleMasculine,
                getDefaultValues(verbalNounPairs));
    }

    private PassiveLine createPassiveLine(SarfKabeer sarfKabeer) {
        SarfKabeerPair activeTensePair = sarfKabeer.getActiveTensePair();
        SarfKabeerPair activeParticiplePair = sarfKabeer.getActiveParticiplePair();
        SarfKabeerPair[] verbalNounPairs = sarfKabeer.getVerbalNounPairs();

        ConjugationStack rightSideStack = activeTensePair.getRightSideStack();
        ConjugationStack leftSideStack = activeTensePair.getLeftSideStack();
        RootWord pastPassiveTense = null;
        RootWord presentPassiveTense = null;
        if (rightSideStack != null) {
            pastPassiveTense = rightSideStack.getDefaultValue();
        }
        if (leftSideStack != null) {
            presentPassiveTense = leftSideStack.getDefaultValue();
        }

        rightSideStack = activeParticiplePair.getRightSideStack();
        RootWord passiveParticipleMasculine = null;
        if (rightSideStack != null) {
            passiveParticipleMasculine = rightSideStack.getDefaultValue();
        }

        return new PassiveLine(pastPassiveTense, presentPassiveTense, passiveParticipleMasculine,
                getDefaultValues(verbalNounPairs));
    }

    private ImperativeAndForbiddingLine createImperativeAndForbidding(SarfKabeer sarfKabeer) {
        SarfKabeerPair imperativeAndForbiddingPair = sarfKabeer.getImperativeAndForbiddingPair();

        ConjugationStack rightSideStack = imperativeAndForbiddingPair.getRightSideStack();
        ConjugationStack leftSideStack = imperativeAndForbiddingPair.getLeftSideStack();
        RootWord imperative = null;
        RootWord forbidding = null;
        if (rightSideStack != null) {
            imperative = rightSideStack.getDefaultValue();
        }
        if (leftSideStack != null) {
            forbidding = leftSideStack.getDefaultValue();
        }
        return new ImperativeAndForbiddingLine(imperative, forbidding);
    }

    private AdverbLine createAdverbLine(SarfKabeer sarfKabeer) {
        return new AdverbLine(getDefaultValues(sarfKabeer.getAdverbPairs()));
    }

    private RootWord[] getDefaultValues(SarfKabeerPair[] sarfKabeerPairs) {
        ConjugationStack rightSideStack;
        ConjugationStack leftSideStack;
        int len = sarfKabeerPairs == null ? 0 : sarfKabeerPairs.length;
        List<RootWord> rootWords = new ArrayList<>();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                SarfKabeerPair verbalNounPair = sarfKabeerPairs[i];
                rightSideStack = verbalNounPair.getRightSideStack();
                leftSideStack = verbalNounPair.getLeftSideStack();
                if (rightSideStack != null) {
                    rootWords.add(rightSideStack.getDefaultValue());
                }
                if (leftSideStack != null) {
                    rootWords.add(leftSideStack.getDefaultValue());
                }
            }
        }
        return rootWords.toArray(new RootWord[rootWords.size()]);
    }
}
