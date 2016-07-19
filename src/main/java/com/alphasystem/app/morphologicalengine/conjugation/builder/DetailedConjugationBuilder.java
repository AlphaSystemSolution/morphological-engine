package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.GUICE_SUPPORT;
import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder.NUM_OF_COLUMNS;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.add;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public final class DetailedConjugationBuilder {

    DetailedConjugationBuilder() {
    }

    public DetailedConjugation createDetailedConjugation(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                         RootLetters rootLetters, boolean removePassiveLine) {
        final VerbDetailedConjugationPair activeTensePair = createActiveTensePair(conjugationRoots, ruleProcessor, rootLetters);
        final NounDetailedConjugationPair activeParticiplePair = createActiveParticiplePair(conjugationRoots, ruleProcessor, rootLetters);
        final NounDetailedConjugationPair[] verbalNounPairs = createVerbalNounPairs(conjugationRoots, ruleProcessor, rootLetters);
        final VerbDetailedConjugationPair passiveTensePair = removePassiveLine ? null : createPassiveTensePair(conjugationRoots, ruleProcessor, rootLetters);
        final NounDetailedConjugationPair passiveParticiplePair = removePassiveLine ? null : createPassiveParticiplePair(conjugationRoots, ruleProcessor, rootLetters);
        final NounDetailedConjugationPair[] adverbPairs = createAdverbPairs(conjugationRoots, ruleProcessor, rootLetters);
        final VerbDetailedConjugationPair imperativeAndForbiddingPair = createImperativeAndForbiddingPair(conjugationRoots, ruleProcessor, rootLetters);

        return new DetailedConjugation(activeTensePair, verbalNounPairs, activeParticiplePair, passiveTensePair,
                passiveParticiplePair, imperativeAndForbiddingPair, adverbPairs);
    }

    private VerbDetailedConjugationPair createTensePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                        VerbRootBase leftBase, VerbRootBase rightBase,
                                                        RuleProcessor ruleProcessor, RootLetters rootLetters) {
        VerbConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            TenseMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleProcessor, rightBase, rootLetters);
        }

        VerbConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            TenseMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleProcessor, leftBase, rootLetters);
        }
        return new VerbDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private VerbDetailedConjugationPair createActiveTensePair(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                              RootLetters rootLetters) {
        return createTensePair(PRESENT_TENSE, PAST_TENSE, conjugationRoots.presentTense, conjugationRoots.pastTense,
                ruleProcessor, rootLetters);
    }

    private NounDetailedConjugationPair createParticiplePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                             NounRootBase leftBase, NounRootBase rightBase,
                                                             RuleProcessor ruleProcessor, RootLetters rootLetters) {
        NounConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            ParticipleMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleProcessor, leftBase, rootLetters);
        }

        NounConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            ParticipleMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleProcessor, rightBase, rootLetters);
        }
        return new NounDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private NounDetailedConjugationPair createActiveParticiplePair(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                                   RootLetters rootLetters) {
        return createParticiplePair(ACTIVE_PARTICIPLE_FEMININE, ACTIVE_PARTICIPLE_MASCULINE, conjugationRoots.activeParticipleFeminine,
                conjugationRoots.activeParticipleMasculine, ruleProcessor, rootLetters);
    }

    private NounDetailedConjugationPair createPassiveParticiplePair(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                                    RootLetters rootLetters) {
        return createParticiplePair(PASSIVE_PARTICIPLE_FEMININE, PASSIVE_PARTICIPLE_MASCULINE, conjugationRoots.passiveParticipleFeminine,
                conjugationRoots.passiveParticipleMasculine, ruleProcessor, rootLetters);
    }

    private NounDetailedConjugationPair[] createVerbalNounPairs(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                                RootLetters rootLetters) {
        return createPairs(conjugationRoots.verbalNouns, VERBAL_NOUN, ruleProcessor, rootLetters);
    }

    private NounDetailedConjugationPair[] createAdverbPairs(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                            RootLetters rootLetters) {
        return createPairs(conjugationRoots.adverbs, NOUN_OF_PLACE_AND_TIME, ruleProcessor, rootLetters);
    }

    private VerbDetailedConjugationPair createPassiveTensePair(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                               RootLetters rootLetters) {
        return createTensePair(PRESENT_PASSIVE_TENSE, PAST_PASSIVE_TENSE, conjugationRoots.presentPassiveTense,
                conjugationRoots.pastPassiveTense, ruleProcessor, rootLetters);
    }

    private VerbDetailedConjugationPair createImperativeAndForbiddingPair(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor,
                                                                          RootLetters rootLetters) {
        return createTensePair(FORBIDDING, IMPERATIVE, conjugationRoots.forbidding, conjugationRoots.imperative, ruleProcessor,
                rootLetters);
    }

    private NounDetailedConjugationPair[] createPairs(NounRootBase[] pairs, SarfTermType termType, RuleProcessor ruleProcessor,
                                                      RootLetters rootLetters) {
        NounDetailedConjugationPair[] result = new NounDetailedConjugationPair[0];

        if (!isEmpty(pairs)) {
            List<NounRootBase> rootBaseList = new ArrayList<>();
            Collections.addAll(rootBaseList, pairs);
            while ((rootBaseList.size() % NUM_OF_COLUMNS) != 0) {
                rootBaseList.add(null);
            }

            int fromIndex = 0;
            int toIndex = NUM_OF_COLUMNS;
            while (fromIndex < rootBaseList.size()) {
                final List<NounRootBase> subList = rootBaseList.subList(fromIndex, toIndex);
                result = add(result, createPair(subList.get(1), subList.get(0), termType, ruleProcessor, rootLetters));
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }
        }
        return result;
    }

    private NounDetailedConjugationPair createPair(NounRootBase leftBase, NounRootBase rightBase, SarfTermType termType,
                                                   RuleProcessor ruleProcessor, RootLetters rootLetters) {
        SarfTermType leftTerm = (leftBase == null) ? null : termType;
        SarfTermType rightTerm = (rightBase == null) ? null : termType;
        return createParticiplePair(leftTerm, rightTerm, leftBase, rightBase, ruleProcessor, rootLetters);
    }

    static class ProviderImpl implements Provider<DetailedConjugationBuilder>{

        @Override
        public DetailedConjugationBuilder get() {
            return new DetailedConjugationBuilder();
        }
    }
}
