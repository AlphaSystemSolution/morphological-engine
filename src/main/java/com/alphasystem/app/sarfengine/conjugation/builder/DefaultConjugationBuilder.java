/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.sarfengine.conjugation.AbstractConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.*;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.CommandLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.PassiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ZarfLine;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.template.FormTemplate;
import com.alphasystem.app.sarfengine.conjugation.triliteralwords.TriLiteralPastTenseBuilder;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.arabic.model.RootType.CONSONANT;
import static com.alphasystem.arabic.model.RootType.WEAK;
import static com.alphasystem.arabic.model.VerbType.*;
import static com.alphasystem.arabic.model.WeakVerbType.*;
import static java.lang.Class.forName;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Default implementation of {@linkplain ConjugationBuilder}.
 *
 * @author sali
 */
public class DefaultConjugationBuilder implements ConjugationBuilder {

    private final FormTemplate formTemplate;
    private final NamedTemplate template;
    private RuleProcessor pastTenseRuleProcessor;

    public DefaultConjugationBuilder(final NamedTemplate template) {
        this.template = template;
        formTemplate = FormTemplate.getByNamedTemplate(this.template);
    }

    private void addAll(List<RootWord> targetList, List<RootWord> srcList) {
        for (RootWord rootWord : srcList) {
            targetList.add(new RootWord(rootWord));
        }
    }

    private ActiveLine createActiveLine(SarfKabeerPair activeTensePair,
                                        SarfKabeerPair activeIsmPair, ConjugationMember[] masdarValues) {
        ConjugationMember presentTenseDefaultValue = null;
        ConjugationMember pastTenseDefaultValue = null;
        if (activeTensePair != null) {
            presentTenseDefaultValue = activeTensePair
                    .getRightSideDefaultValue();
            pastTenseDefaultValue = activeTensePair.getLeftSideDefaultValue();
        }
        ConjugationMember activeIsmDefaultValue = null;
        if (activeIsmPair != null) {
            activeIsmDefaultValue = activeIsmPair.getRightSideDefaultValue();
        }
        return new ActiveLine(presentTenseDefaultValue, pastTenseDefaultValue,
                activeIsmDefaultValue, masdarValues);
    }

    SarfKabeerPair createActiveParticiplePair(boolean skipRuleProcessing,
                                              ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                              ArabicLetterType thirdRadical) {
        RootWord activeParticipleMasculineRoot = formTemplate
                .getActiveParticipleMasculineRoot();
        activeParticipleMasculineRoot = processReplacements(
                activeParticipleMasculineRoot, firstRadical, secondRadical,
                thirdRadical);
        RootWord activeParticipleFeminineRoot = formTemplate
                .getActiveParticipleFeminineRoot();
        activeParticipleFeminineRoot = processReplacements(
                activeParticipleFeminineRoot, firstRadical, secondRadical,
                thirdRadical);
        return getSarfKabeerPair(skipRuleProcessing,
                activeParticipleFeminineRoot, activeParticipleMasculineRoot);
    }

    SarfKabeerPair createCommandAndForbiddingPair(boolean skipRuleProcessing,
                                                  ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                                  ArabicLetterType thirdRadical) {
        SarfKabeerPair commandAndForbiddingPair;
        RootWord forbiddingRoot = formTemplate.getForbiddingRoot();
        forbiddingRoot = processReplacements(forbiddingRoot, firstRadical,
                secondRadical, thirdRadical);
        RootWord imperativeRoot = formTemplate.getImperativeRoot();
        imperativeRoot = processReplacements(imperativeRoot, firstRadical,
                secondRadical, thirdRadical);
        commandAndForbiddingPair = getSarfKabeerPair(skipRuleProcessing,
                forbiddingRoot, imperativeRoot);
        return commandAndForbiddingPair;
    }

    private ConjugationMember[] createDefaultValues(SarfKabeerPair[] pairs) {
        if (isEmpty(pairs)) {
            return null;
        }
        List<ConjugationMember> list = new ArrayList<>();
        for (SarfKabeerPair sarfKabeerPair : pairs) {
            ConjugationMember leftSideDefaultValue = sarfKabeerPair
                    .getLeftSideDefaultValue();
            ConjugationMember rightSideDefaultValue = sarfKabeerPair
                    .getRightSideDefaultValue();
            if (rightSideDefaultValue != null) {
                list.add(rightSideDefaultValue);
            }
            if (leftSideDefaultValue != null) {
                list.add(leftSideDefaultValue);
            }
        }
        return list.toArray(new ConjugationMember[list.size()]);
    }

    private PassiveLine createPassiveLine(SarfKabeerPair passiveTensePair,
                                          SarfKabeerPair passiveIsmPair, ConjugationMember[] masdarValues) {
        ConjugationMember pastPassiveTense = null;
        ConjugationMember presentPassiveTense = null;
        ConjugationMember ismMafoolMasculine = null;
        PassiveLine passiveLine;
        if (passiveTensePair != null) {
            pastPassiveTense = passiveTensePair.getRightSideDefaultValue();
            presentPassiveTense = passiveTensePair.getLeftSideDefaultValue();
        }
        if (passiveIsmPair != null) {
            ismMafoolMasculine = passiveIsmPair.getRightSideDefaultValue();
        }
        passiveLine = new PassiveLine(pastPassiveTense, presentPassiveTense,
                ismMafoolMasculine, masdarValues);
        return passiveLine;
    }

    SarfKabeerPair createPassiveParticiplePair(boolean skipRuleProcessing,
                                               ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                               ArabicLetterType thirdRadical) {
        SarfKabeerPair passiveParticiplePair = null;
        if (!formTemplate.isNoPassive()) {
            RootWord passiveParticipleMasculineRoot = formTemplate
                    .getPassiveParticipleMasculineRoot();
            RootWord passiveParticipleFeminineRoot = formTemplate
                    .getPassiveParticipleFeminineRoot();
            passiveParticipleMasculineRoot = processReplacements(
                    passiveParticipleMasculineRoot, firstRadical,
                    secondRadical, thirdRadical);
            passiveParticipleFeminineRoot = processReplacements(
                    passiveParticipleFeminineRoot, firstRadical, secondRadical,
                    thirdRadical);
            passiveParticiplePair = getSarfKabeerPair(skipRuleProcessing,
                    passiveParticipleFeminineRoot,
                    passiveParticipleMasculineRoot);
        }
        return passiveParticiplePair;
    }

    SarfKabeerPair createPassiveTensePair(boolean skipRuleProcessing,
                                          ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                          ArabicLetterType thirdRadical) {
        SarfKabeerPair passiveTensePair = null;
        if (!formTemplate.isNoPassive()) {
            RootWord pastPassiveTenseRoot = formTemplate
                    .getPastPassiveTenseRoot();
            RootWord presentPassiveTenseRoot = formTemplate
                    .getPresentPassiveTenseRoot();
            presentPassiveTenseRoot = processReplacements(
                    presentPassiveTenseRoot, firstRadical, secondRadical,
                    thirdRadical);
            pastPassiveTenseRoot = processReplacements(pastPassiveTenseRoot,
                    firstRadical, secondRadical, thirdRadical);
            passiveTensePair = getSarfKabeerPair(skipRuleProcessing,
                    presentPassiveTenseRoot, pastPassiveTenseRoot);
        }
        return passiveTensePair;
    }

    private SarfKabeerPair createSarfKabeerPair(SarfTermType leftSideTerm,
                                                SarfTermType rightSideTerm,
                                                ConjugationMemberBuilder leftSideBuilder,
                                                ConjugationMemberBuilder rightSideBuilder) {
        ConjugationMember[] rightSideTerms = null;
        ConjugationMember[] leftSideTerms = null;
        ConjugationMember rightSideDefaultValue = null;
        ConjugationMember leftSideDefaultValue = null;
        if (rightSideBuilder != null) {
            rightSideTerms = rightSideBuilder.doConjugation();
            rightSideDefaultValue = rightSideBuilder.getDefaultConjugation();
        }
        if (leftSideBuilder != null) {
            leftSideTerms = leftSideBuilder.doConjugation();
            leftSideDefaultValue = leftSideBuilder.getDefaultConjugation();
        }
        if (rightSideTerms == null && leftSideTerms != null) {
            rightSideTerms = new ConjugationMember[leftSideTerms.length];
        }
        if (leftSideTerms == null && rightSideTerms != null) {
            leftSideTerms = new ConjugationMember[rightSideTerms.length];
        }

        if (rightSideTerms == null && leftSideTerms == null) {
            return null;
        } else {
            return new SarfKabeerPair(leftSideTerms, rightSideTerms,
                    leftSideDefaultValue, rightSideDefaultValue, leftSideTerm,
                    rightSideTerm);
        }
    }

    @Override
    public SarfChart doConjugation(String translation,
                                   ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                   ArabicLetterType thirdRadical) {
        return doConjugation(translation, false, false, firstRadical,
                secondRadical, thirdRadical, null, null);
    }

    @Override
    public SarfChart doConjugation(String translation,
                                   ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                   ArabicLetterType thirdRadical, List<RootWord> verbalNounList,
                                   List<RootWord> zarfList) {
        return doConjugation(translation, false, false, firstRadical,
                secondRadical, thirdRadical, verbalNounList, zarfList);
    }

    @Override
    public SarfChart doConjugation(String translation,
                                   boolean removePassiveLine, boolean skipRuleProcessing,
                                   ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                   ArabicLetterType thirdRadical) {
        return doConjugation(translation, removePassiveLine,
                skipRuleProcessing, firstRadical, secondRadical, thirdRadical,
                null, null);
    }

    @Override
    public SarfChart doConjugation(String translation,
                                   boolean removePassiveLine, boolean skipRuleProcessing,
                                   ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                   ArabicLetterType thirdRadical, List<RootWord> verbalNounList,
                                   List<RootWord> zarfList) {

        RootWord pastTenseRoot = formTemplate.getPastTenseRoot();
        pastTenseRoot = processReplacements(pastTenseRoot, firstRadical,
                secondRadical, thirdRadical);
        RootWord presentTenseRoot = formTemplate.getPresentTenseRoot();
        presentTenseRoot = processReplacements(presentTenseRoot, firstRadical,
                secondRadical, thirdRadical);
        SarfKabeerPair activeTensePair = getSarfKabeerPair(skipRuleProcessing,
                presentTenseRoot, pastTenseRoot);

        SarfKabeerPair[] masdarPairs = null;
        List<RootWord> verbalNounRoots = mergeList(
                formTemplate.getVerbalNounRoots(), verbalNounList);
        if (verbalNounRoots != null && !verbalNounRoots.isEmpty()) {
            masdarPairs = getSarfKabeerPair(skipRuleProcessing,
                    verbalNounRoots, firstRadical, secondRadical, thirdRadical);
        }

        SarfKabeerPair activeParticiplePair = createActiveParticiplePair(
                skipRuleProcessing, firstRadical, secondRadical, thirdRadical);

        SarfKabeerPair passiveTensePair = null;
        SarfKabeerPair passiveParticiplePair = null;
        if (!removePassiveLine) {
            passiveTensePair = createPassiveTensePair(skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical);
            passiveParticiplePair = createPassiveParticiplePair(
                    skipRuleProcessing, firstRadical, secondRadical,
                    thirdRadical);
        }
        SarfKabeerPair[] zarfPairs = null;
        List<RootWord> zarfRoots = mergeList(formTemplate.getZarfRoots(),
                zarfList);
        if (zarfRoots != null && !zarfRoots.isEmpty()) {
            zarfPairs = getSarfKabeerPair(skipRuleProcessing, zarfRoots,
                    firstRadical, secondRadical, thirdRadical);
        }

        SarfKabeerPair commandAndForbiddingPair = createCommandAndForbiddingPair(
                skipRuleProcessing, firstRadical, secondRadical, thirdRadical);

        ConjugationMember[] masdarValues = createDefaultValues(masdarPairs);
        SarfKabeer sarfKabeer = new SarfKabeer(activeTensePair, masdarPairs,
                activeParticiplePair, passiveTensePair, passiveParticiplePair,
                commandAndForbiddingPair, zarfPairs);

        ActiveLine activeLine = createActiveLine(activeTensePair,
                activeParticiplePair, masdarValues);
        PassiveLine passiveLine = null;
        if (!removePassiveLine
                && (passiveTensePair != null && passiveParticiplePair != null)) {
            passiveLine = createPassiveLine(passiveTensePair,
                    passiveParticiplePair, masdarValues);
        }
        CommandLine commnadLine = null;
        if (commandAndForbiddingPair != null) {
            commnadLine = new CommandLine(
                    commandAndForbiddingPair.getRightSideDefaultValue(),
                    commandAndForbiddingPair.getLeftSideDefaultValue());
        }
        ZarfLine zarfLine = null;
        ConjugationMember[] zarfValues;
        zarfValues = createDefaultValues(zarfPairs);
        if (zarfValues != null) {
            zarfLine = new ZarfLine(zarfValues);
        }

        SarfSagheer sarfSagheer = new SarfSagheer(activeLine, passiveLine,
                commnadLine, zarfLine);

        ConjugationHeader conjugationHeader = initChartTitle(translation,
                template.getLabel(), pastTenseRoot, firstRadical,
                secondRadical, thirdRadical);
        return new SarfChart(conjugationHeader, sarfSagheer,
                sarfKabeer);
    }

    public FormTemplate getFormTemplate() {
        return formTemplate;
    }

    private ConjugationMemberBuilder getMemberBuilder(
            boolean skipRuleProcessing, RootWord rootWord) {
        System.out.println("RootWord: " + rootWord);
        AbstractConjugationMemberBuilder builder = null;
        if (rootWord != null) {
            String implementationClass = rootWord.getImplementationClass();
            System.out.println("implementationClass: " + implementationClass);
            try {
                Class<?> klass = forName(implementationClass);
                builder = (AbstractConjugationMemberBuilder) klass.newInstance();
                builder.setTemplate(template);
                builder.setSkipRuleProcessing(skipRuleProcessing);
                builder.setRootWord(new RootWord(rootWord));
                RuleProcessor ruleProcessor = initRuleProcesor(builder);
                System.out.println("RuleProcessor: " + ruleProcessor);
                if (TriLiteralPastTenseBuilder.class.getName().equals(
                        builder.getClass().getName())) {
                    pastTenseRuleProcessor = ruleProcessor;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder;
    }

    @Override
    public ConjugationMemberBuilder getMemberBuilder(
            boolean skipRuleProcessing, SarfTermType sarfTermType,
            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
            ArabicLetterType thirdRadical) {
        RootWord templateWord = new RootWord(
                formTemplate.getTemplateWord(sarfTermType));
        templateWord = processReplacements(templateWord, firstRadical,
                secondRadical, thirdRadical);
        return getMemberBuilder(skipRuleProcessing, templateWord);
    }

    private SarfKabeerPair[] getSarfKabeerPair(boolean skipRuleProcessing,
                                               List<RootWord> list, ArabicLetterType firstRadical,
                                               ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        if (list.size() % 2 != 0) {
            list.add(null);
        }
        int index = 0;
        List<SarfKabeerPair> pairs = new ArrayList<>();
        while (index < list.size()) {
            RootWord rightSideTemplate = list.get(index++);
            RootWord leftSideTemplate = list.get(index++);
            if (leftSideTemplate != null) {
                leftSideTemplate = processReplacements(leftSideTemplate,
                        firstRadical, secondRadical, thirdRadical);
            }
            if (rightSideTemplate != null) {
                rightSideTemplate = processReplacements(rightSideTemplate,
                        firstRadical, secondRadical, thirdRadical);
            }
            pairs.add(getSarfKabeerPair(skipRuleProcessing, leftSideTemplate,
                    rightSideTemplate));
        }
        return pairs.toArray(new SarfKabeerPair[pairs.size()]);
    }

    public SarfKabeerPair getSarfKabeerPair(boolean skipRuleProcessing,
                                            RootWord leftSideTemplate, RootWord rightSideTemplate) {
        if (leftSideTemplate == null && rightSideTemplate == null) {
            return null;
        }
        ConjugationMemberBuilder rightSideMemberBuilder = getMemberBuilder(
                skipRuleProcessing, rightSideTemplate);
        ConjugationMemberBuilder leftSideMemberBuilder = getMemberBuilder(
                skipRuleProcessing, leftSideTemplate);

        SarfTermType leftSideTerm = null;
        if (leftSideTemplate != null) {
            leftSideTerm = leftSideTemplate.getSarfTermType();
        }
        SarfTermType rightSideTerm = null;
        if (rightSideTemplate != null) {
            rightSideTerm = rightSideTemplate.getSarfTermType();
        }
        return createSarfKabeerPair(leftSideTerm, rightSideTerm,
                leftSideMemberBuilder, rightSideMemberBuilder);
    }

    private ConjugationHeader initChartTitle(String translation,
                                             ArabicWord baseWord, RootWord pastTenseRoot,
                                             ArabicLetterType... rootLetters) {
        WordStatus status = new WordStatus(pastTenseRoot);
        RootType rootType = CONSONANT;
        VerbType verbType = VerbType.CONSONANT;
        WeakVerbType weakVerbType = null;
        if (status.isWeak()) {
            rootType = WEAK;
            if (status.twoSeparateLettersWeak()) {
                verbType = TWO_SEPARATE_RADICALS_WEAK;
            } else if (status.twoConsecutiveLettersWeak()) {
                verbType = TWO_CONSECUTIVE_RADICALS_WEAK;
            } else if (status.isAssimilated()) {
                verbType = FIRST_RADICAL_WEAK;
                weakVerbType = status.isFirstRadicalWaw() ? FIRST_RADICAL_WEAK_WAW
                        : FIRST_RADICAL_WEAK_YA;
            } else if (status.isHollow()) {
                verbType = SECOND_RADICAL_WEAK;
                weakVerbType = status.isSecondRadicalWaw() ? SECOND_RADICAL_WEAK_WAW
                        : SECOND_RADICAL_WEAK_YA;
            } else if (status.isDefective()) {
                verbType = THIRD_RADICAL_WEAK;
                weakVerbType = status.isThirdRadicalWaw() ? THIRD_RADICAL_WEAK_WAW
                        : THIRD_RADICAL_WEAK_YA;
            }
        } else if (status.isDouledLettered()) {
            verbType = DOUBLE_LETTERED;
        } else if (status.isHamzatted()) {
            if (status.isFirstRadicalHamza()) {
                verbType = FIRST_RADICAL_HAMZA;
            } else if (status.isSecondRadicalHamza()) {
                verbType = SECOND_RADICAL_HAMZA;
            } else if (status.isThirdRadicalHamza()) {
                verbType = THIRD_RADICAL_HAMZA;
            }
        }
        ChartMode chartMode = new ChartMode(template, rootType, verbType,
                weakVerbType);
        return new ConjugationHeader(translation, baseWord, chartMode,
                rootLetters);
    }

    private RuleProcessor initRuleProcesor(ConjugationMemberBuilder builder) {
        RuleProcessor ruleProcessor = builder.getRuleProcessor();
        if (pastTenseRuleProcessor != null) {
            ruleProcessor
                    .setDiacriticForWeakSecondRadicalWaw(pastTenseRuleProcessor
                            .getDiacriticForWeakSecondRadicalWaw());
            ruleProcessor.setPastTenseHasTransformed(pastTenseRuleProcessor
                    .isPastTenseHasTransformed());
        }
        return ruleProcessor;
    }

    private List<RootWord> mergeList(List<RootWord> list1, List<RootWord> list2) {
        List<RootWord> mergedList = new ArrayList<>();
        List<RootWord> srcList1 = list1 == null ? new ArrayList<>()
                : list1;
        List<RootWord> srcList2 = list2 == null ? new ArrayList<>()
                : list2;
        addAll(mergedList, srcList1);
        addAll(mergedList, srcList2);
        return mergedList;
    }

    private RootWord processReplacements(RootWord src,
                                         ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                         ArabicLetterType thirdRadical) {
        return processReplacements(src, firstRadical, secondRadical,
                thirdRadical, null);
    }

    private RootWord processReplacements(RootWord src,
                                         ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                         ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical);
    }

}
