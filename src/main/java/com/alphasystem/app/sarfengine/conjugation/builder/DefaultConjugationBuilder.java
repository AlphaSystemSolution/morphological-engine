package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.sarfengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.AdverbLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ImperativeAndForbiddingLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.PassiveLine;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.arabic.model.RootType.WEAK;
import static com.alphasystem.arabic.model.VerbType.*;
import static com.alphasystem.arabic.model.WeakVerbType.*;

/**
 * @author sali
 */
public class DefaultConjugationBuilder implements ConjugationBuilder {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();
    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GUICE_SUPPORT.getRuleProcessorFactory();
    private static final MemberBuilderFactory MEMBER_BUILDER_FACTORY = GUICE_SUPPORT.getOldMemberBuilderFactory();

    private static RootWord processReplacements(RootWord src, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                ArabicLetterType fourthRadical) {
        return new RootWord(src, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    @Override
    public MorphologicalChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                            boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                            ArabicLetterType fourthRadical, List<VerbalNoun> verbalNouns,
                                            List<NounOfPlaceAndTime> adverbs) {
        FormTemplate formTemplate = FormTemplate.getByNamedTemplate(template);
        RuleProcessor ruleEngine = RULE_PROCESSOR_FACTORY.getRuleEngine(new RuleInfo(template));

        removePassiveLine = removePassiveLine || (formTemplate.getPastPassiveTenseRoot() == null);

        DetailedConjugation detailedConjugation = createSarfKabeer(formTemplate, ruleEngine, removePassiveLine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical, verbalNouns, adverbs);

        AbbreviatedConjugation abbreviatedConjugation = createSarfSagheer(detailedConjugation, removePassiveLine);
        ActiveLine activeLine = abbreviatedConjugation.getActiveLine();
        RootWord pastTense = activeLine.getPastTense();
        RootWord presentTense = activeLine.getPresentTense();
        RootLetters rootLetters = new RootLetters().withFirstRadical(firstRadical).withSecondRadical(secondRadical)
                .withThirdRadical(thirdRadical).withFourthRadical(fourthRadical);
        ConjugationHeader conjugationHeader = createConjugationHeader(template, translation, pastTense, presentTense,
                rootLetters);

        return new MorphologicalChart(conjugationHeader, abbreviatedConjugation, detailedConjugation);
    }

    @Override
    public MorphologicalChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                                            boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                            List<VerbalNoun> verbalNouns, List<NounOfPlaceAndTime> adverbs) {
        return doConjugation(template, translation, removePassiveLine, skipRuleProcessing, firstRadical, secondRadical,
                thirdRadical, null, verbalNouns, adverbs);
    }

    private ConjugationHeader createConjugationHeader(NamedTemplate template, String translation, RootWord pastTenseRoot,
                                                      RootWord presentTenseRoot, RootLetters rootLetters) {
        WordStatus status = new WordStatus(pastTenseRoot);
        RootType rootType = RootType.CONSONANT;
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
        ChartMode chartMode = new ChartMode(template, rootType, verbType, weakVerbType);
        return new ConjugationHeader(translation, pastTenseRoot, presentTenseRoot, template.getLabel(), chartMode, rootLetters);
    }

    private DetailedConjugationPair createActiveTensePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                          boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                          ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                          ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;

        RootWord rightSideRootWord = processReplacements(formTemplate.getPastTenseRoot(), firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPastTenseBuilder(ruleEngine, skipRuleProcessing,
                    rightSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }

        RootWord leftSideRootWord = processReplacements(formTemplate.getPresentTenseRoot(), firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPresentTenseBuilder(ruleEngine, skipRuleProcessing,
                    leftSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private DetailedConjugationPair createActiveParticiplePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                               boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                               ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                               ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;

        RootWord rightSideRootWord = processReplacements(formTemplate.getActiveParticipleMasculineRoot(), firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralActiveParticipleMasculineBuilder(ruleEngine,
                    skipRuleProcessing, rightSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }

        RootWord leftSideRootWord = processReplacements(formTemplate.getActiveParticipleFeminineRoot(), firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralActiveParticipleFeminineBuilder(ruleEngine,
                    skipRuleProcessing, leftSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private DetailedConjugationPair createPassiveTensePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                           boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                           ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;

        RootWord rightSideRootWord = processReplacements(formTemplate.getPastPassiveTenseRoot(), firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPastPassiveBuilder(ruleEngine, skipRuleProcessing,
                    rightSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }

        RootWord leftSideRootWord = processReplacements(formTemplate.getPresentPassiveTenseRoot(), firstRadical, secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPresentPassiveBuilder(ruleEngine, skipRuleProcessing,
                    leftSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private DetailedConjugationPair createPassiveParticiplePair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                                boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                                ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;

        RootWord rightSideRootWord = processReplacements(formTemplate.getPassiveParticipleMasculineRoot(), firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPassiveParticipleMasculineBuilder(ruleEngine,
                    skipRuleProcessing, rightSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }

        RootWord leftSideRootWord = processReplacements(formTemplate.getPassiveParticipleFeminineRoot(), firstRadical,
                secondRadical, thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralPassiveParticipleFeminineBuilder(ruleEngine,
                    skipRuleProcessing, leftSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private DetailedConjugationPair createImperativeAndForbiddingPair(FormTemplate formTemplate, RuleProcessor ruleEngine,
                                                                      boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                                      ArabicLetterType secondRadical,
                                                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;

        RootWord rightSideRootWord = processReplacements(formTemplate.getImperativeRoot(), firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            rightSideBuilder = getImperativeBuilder(formTemplate.getTemplate(), ruleEngine, skipRuleProcessing, rightSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }

        RootWord leftSideRootWord = processReplacements(formTemplate.getForbiddingRoot(), firstRadical, secondRadical,
                thirdRadical, fourthRadical);
        if (fourthRadical == null) {
            leftSideBuilder = MEMBER_BUILDER_FACTORY.getTriLiteralForbiddingBuilder(ruleEngine, skipRuleProcessing, leftSideRootWord);
        } else {
            throw new RuntimeException("Fourth radical is not implemented");
        }
        return createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord);
    }

    private DetailedConjugationPair[] createVerbalNounPairs(List<VerbalNoun> verbalNouns, RuleProcessor ruleEngine,
                                                            boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                            ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                            ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;
        RootWord rightSideRootWord;
        RootWord leftSideRootWord;

        List<DetailedConjugationPair> detailedConjugationPairs = new ArrayList<>();
        int len = verbalNouns.size();
        int index = 0;
        while (true) {
            if (index >= len) {
                break;
            }
            VerbalNoun rightSideVerbalNoun = verbalNouns.get(index++);
            VerbalNoun leftSideVerbalNoun = (index < len) ? verbalNouns.get(index++) : null;

            rightSideRootWord = new RootWord(rightSideVerbalNoun.getRootWord(), firstRadical, secondRadical,
                    thirdRadical, fourthRadical);
            rightSideBuilder = getVerbalNounBuilder(rightSideVerbalNoun, ruleEngine, skipRuleProcessing, rightSideRootWord);

            leftSideRootWord = leftSideVerbalNoun == null ? null :
                    new RootWord(leftSideVerbalNoun.getRootWord(), firstRadical, secondRadical, thirdRadical, fourthRadical);
            leftSideBuilder = leftSideRootWord == null ? null :
                    getVerbalNounBuilder(leftSideVerbalNoun, ruleEngine, skipRuleProcessing, leftSideRootWord);

            detailedConjugationPairs.add(createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord));
        }

        return detailedConjugationPairs.toArray(new DetailedConjugationPair[detailedConjugationPairs.size()]);
    }

    private DetailedConjugationPair[] createAdverbPairs(List<NounOfPlaceAndTime> adverbs, RuleProcessor ruleEngine,
                                                        boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                        ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                        ArabicLetterType fourthRadical) {
        ConjugationMemberBuilder rightSideBuilder;
        ConjugationMemberBuilder leftSideBuilder;
        RootWord rightSideRootWord;
        RootWord leftSideRootWord;

        List<DetailedConjugationPair> detailedConjugationPairs = new ArrayList<>();
        int len = adverbs.size();
        int index = 0;
        while (true) {
            if (index >= len) {
                break;
            }
            NounOfPlaceAndTime rightSideAdverb = adverbs.get(index++);
            NounOfPlaceAndTime leftSideAdverb = (index < len) ? adverbs.get(index++) : null;

            rightSideRootWord = new RootWord(rightSideAdverb.getRootWord(), firstRadical, secondRadical,
                    thirdRadical, fourthRadical);
            rightSideBuilder = getAdverbBuilder(rightSideAdverb, ruleEngine, skipRuleProcessing, rightSideRootWord);

            leftSideRootWord = leftSideAdverb == null ? null :
                    new RootWord(leftSideAdverb.getRootWord(), firstRadical, secondRadical, thirdRadical, fourthRadical);
            leftSideBuilder = leftSideRootWord == null ? null :
                    getAdverbBuilder(leftSideAdverb, ruleEngine, skipRuleProcessing, leftSideRootWord);

            detailedConjugationPairs.add(createSarfKabeerPair(leftSideBuilder, rightSideBuilder, leftSideRootWord, rightSideRootWord));
        }

        return detailedConjugationPairs.toArray(new DetailedConjugationPair[detailedConjugationPairs.size()]);
    }

    private DetailedConjugation createSarfKabeer(FormTemplate formTemplate, RuleProcessor ruleEngine, boolean removePassiveLine,
                                                 boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                 ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                 ArabicLetterType fourthRadical, List<VerbalNoun> verbalNouns,
                                                 List<NounOfPlaceAndTime> adverbs) {
        DetailedConjugationPair activeTensePair = createActiveTensePair(formTemplate, ruleEngine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical);

        DetailedConjugationPair[] verbalNounPairs = null;
        if (verbalNouns != null && !verbalNouns.isEmpty()) {
            verbalNounPairs = createVerbalNounPairs(verbalNouns, ruleEngine, skipRuleProcessing, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        DetailedConjugationPair activeParticiplePair = createActiveParticiplePair(formTemplate, ruleEngine, skipRuleProcessing,
                firstRadical, secondRadical, thirdRadical, fourthRadical);

        DetailedConjugationPair passiveTensePair = null;
        DetailedConjugationPair passiveParticiplePair = null;

        if (!removePassiveLine) {
            passiveTensePair = createPassiveTensePair(formTemplate, ruleEngine, skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical, fourthRadical);

            passiveParticiplePair = createPassiveParticiplePair(formTemplate, ruleEngine, skipRuleProcessing,
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }

        DetailedConjugationPair imperativeAndForbiddingPair = createImperativeAndForbiddingPair(formTemplate, ruleEngine,
                skipRuleProcessing, firstRadical, secondRadical, thirdRadical, fourthRadical);

        DetailedConjugationPair[] adverbPairs = null;
        if (adverbs != null && !adverbs.isEmpty()) {
            adverbPairs = createAdverbPairs(adverbs, ruleEngine, skipRuleProcessing, firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }

        return new DetailedConjugation(activeTensePair, verbalNounPairs, activeParticiplePair, passiveTensePair,
                passiveParticiplePair, imperativeAndForbiddingPair, adverbPairs);
    }

    private DetailedConjugationPair createSarfKabeerPair(ConjugationMemberBuilder leftSideBuilder,
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

    private DetailedConjugationPair createSarfKabeerPair(ConjugationMemberBuilder leftSideBuilder,
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
        return new DetailedConjugationPair(
                new ConjugationStack(leftSideRootWords, leftSideDefaultValue, leftSideTerm),
                new ConjugationStack(rightSideRootWords, rightSideDefaultValue, rightSideTerm));
    }

    private AbbreviatedConjugation createSarfSagheer(DetailedConjugation detailedConjugation, boolean removePassiveLine) {
        ActiveLine activeLine = createActiveLine(detailedConjugation);
        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            passiveLine = createPassiveLine(detailedConjugation);
        }
        ImperativeAndForbiddingLine imperativeAndForbidding = createImperativeAndForbidding(detailedConjugation);
        AdverbLine adverbLine = createAdverbLine(detailedConjugation);
        return new AbbreviatedConjugation(activeLine, passiveLine, imperativeAndForbidding, adverbLine);
    }

    private ActiveLine createActiveLine(DetailedConjugation detailedConjugation) {
        DetailedConjugationPair activeTensePair = detailedConjugation.getActiveTensePair();
        DetailedConjugationPair activeParticiplePair = detailedConjugation.getActiveParticiplePair();
        DetailedConjugationPair[] verbalNounPairs = detailedConjugation.getVerbalNounPairs();

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

    private PassiveLine createPassiveLine(DetailedConjugation detailedConjugation) {
        DetailedConjugationPair passiveTensePair = detailedConjugation.getPassiveTensePair();
        DetailedConjugationPair activeParticiplePair = detailedConjugation.getPassiveParticiplePair();
        DetailedConjugationPair[] verbalNounPairs = detailedConjugation.getVerbalNounPairs();

        ConjugationStack rightSideStack = passiveTensePair.getRightSideStack();
        ConjugationStack leftSideStack = passiveTensePair.getLeftSideStack();
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

    private ImperativeAndForbiddingLine createImperativeAndForbidding(DetailedConjugation detailedConjugation) {
        DetailedConjugationPair imperativeAndForbiddingPair = detailedConjugation.getImperativeAndForbiddingPair();

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

    private AdverbLine createAdverbLine(DetailedConjugation detailedConjugation) {
        return new AdverbLine(getDefaultValues(detailedConjugation.getAdverbPairs()));
    }

    private RootWord[] getDefaultValues(DetailedConjugationPair[] detailedConjugationPairs) {
        ConjugationStack rightSideStack;
        ConjugationStack leftSideStack;
        int len = detailedConjugationPairs == null ? 0 : detailedConjugationPairs.length;
        List<RootWord> rootWords = new ArrayList<>();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                DetailedConjugationPair verbalNounPair = detailedConjugationPairs[i];
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

    private ConjugationMemberBuilder getVerbalNounBuilder(VerbalNoun verbalNoun, RuleProcessor ruleProcessor,
                                                          boolean skipRuleProcessing, RootWord baseRootWord) {
        ConjugationMemberBuilder builder;
        switch (verbalNoun) {
            case VERBAL_NOUN_V26:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralVerbalNounV1Builder(ruleProcessor, skipRuleProcessing, baseRootWord);
                break;
            default:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralVerbalNounBuilder(ruleProcessor, skipRuleProcessing, baseRootWord);
                break;
        }
        return builder;
    }

    private ConjugationMemberBuilder getAdverbBuilder(NounOfPlaceAndTime nounOfPlaceAndTime, RuleProcessor ruleProcessor,
                                                      boolean skipRuleProcessing, RootWord baseRootWord) {
        ConjugationMemberBuilder builder;
        switch (nounOfPlaceAndTime) {
            case NOUN_OF_PLACE_AND_TIME_V1:
            case NOUN_OF_PLACE_AND_TIME_V2:
            case NOUN_OF_PLACE_AND_TIME_V3:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralBrokenPluralAdverbBuilder(ruleProcessor,
                        skipRuleProcessing, baseRootWord);
                break;
            default:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralAdverbBuilder(ruleProcessor, skipRuleProcessing, baseRootWord);
                break;
        }
        return builder;
    }

    private ConjugationMemberBuilder getImperativeBuilder(NamedTemplate template, RuleProcessor ruleProcessor,
                                                          boolean skipRuleProcessing, RootWord baseRootWord) {
        ConjugationMemberBuilder builder;
        switch (template) {
            case FORM_IV_TEMPLATE:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralImperativeFormIVBuilder(ruleProcessor, skipRuleProcessing, baseRootWord);
                break;
            default:
                builder = MEMBER_BUILDER_FACTORY.getTriLiteralImperativeBuilder(ruleProcessor, skipRuleProcessing, baseRootWord);
                break;
        }
        return builder;
    }
}
