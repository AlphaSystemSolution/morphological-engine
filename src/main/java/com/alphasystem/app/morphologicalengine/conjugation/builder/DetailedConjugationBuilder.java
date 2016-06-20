package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

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

    private final ConjugationRoots conjugationRoots;
    private final RuleProcessor ruleEngine;

    public DetailedConjugationBuilder(ConjugationRoots conjugationRoots, RuleProcessor ruleEngine) {
        this.conjugationRoots = conjugationRoots;
        this.ruleEngine = ruleEngine;
    }

    public DetailedConjugation createDetailedConjugation(RootLetters rootLetters, boolean removePassiveLine) {
        final VerbDetailedConjugationPair activeTensePair = createActiveTensePair(rootLetters);
        final NounDetailedConjugationPair activeParticiplePair = createActiveParticiplePair(rootLetters);
        final NounDetailedConjugationPair[] verbalNounPairs = createVerbalNounPairs(rootLetters);
        final VerbDetailedConjugationPair passiveTensePair = removePassiveLine ? null : createPassiveTensePair(rootLetters);
        final NounDetailedConjugationPair passiveParticiplePair = removePassiveLine ? null : createPassiveParticiplePair(rootLetters);
        final NounDetailedConjugationPair[] adverbPairs = createAdverbPairs(rootLetters);
        final VerbDetailedConjugationPair imperativeAndForbiddingPair = createImperativeAndForbiddingPair(rootLetters);

        return new DetailedConjugation(activeTensePair, verbalNounPairs, activeParticiplePair, passiveTensePair,
                passiveParticiplePair, imperativeAndForbiddingPair, adverbPairs);
    }

    private VerbDetailedConjugationPair createTensePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                        VerbRootBase leftBase, VerbRootBase rightBase,
                                                        RootLetters rootLetters) {
        VerbConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            TenseMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, conjugationRoots.template, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleEngine, rightBase, rootLetters);
        }

        VerbConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            TenseMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(TenseMemberBuilder.class, conjugationRoots.template, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleEngine, leftBase, rootLetters);
        }
        return new VerbDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private VerbDetailedConjugationPair createActiveTensePair(RootLetters rootLetters) {
        return createTensePair(PRESENT_TENSE, PAST_TENSE, conjugationRoots.presentTense, conjugationRoots.pastTense, rootLetters);
    }

    private NounDetailedConjugationPair createParticiplePair(SarfTermType leftTerm, SarfTermType rightTerm,
                                                             NounRootBase leftBase, NounRootBase rightBase,
                                                             RootLetters rootLetters) {
        NounConjugationGroup leftSideConjugations = null;
        if (leftTerm != null) {
            ParticipleMemberBuilder leftSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, leftTerm);
            leftSideConjugations = leftSideBuilder.doConjugation(ruleEngine, leftBase, rootLetters);
        }

        NounConjugationGroup rightSideConjugations = null;
        if (rightTerm != null) {
            ParticipleMemberBuilder rightSideBuilder = GUICE_SUPPORT.getMemberBuilder(ParticipleMemberBuilder.class, rightTerm);
            rightSideConjugations = rightSideBuilder.doConjugation(ruleEngine, rightBase, rootLetters);
        }
        return new NounDetailedConjugationPair(leftSideConjugations, rightSideConjugations);
    }

    private NounDetailedConjugationPair createActiveParticiplePair(RootLetters rootLetters) {
        return createParticiplePair(ACTIVE_PARTICIPLE_FEMININE, ACTIVE_PARTICIPLE_MASCULINE, conjugationRoots.activeParticipleFeminine,
                conjugationRoots.activeParticipleMasculine, rootLetters);
    }

    private NounDetailedConjugationPair createPassiveParticiplePair(RootLetters rootLetters) {
        return createParticiplePair(PASSIVE_PARTICIPLE_FEMININE, PASSIVE_PARTICIPLE_MASCULINE, conjugationRoots.passiveParticipleFeminine,
                conjugationRoots.passiveParticipleMasculine, rootLetters);
    }

    private NounDetailedConjugationPair[] createVerbalNounPairs(RootLetters rootLetters) {
        return createPairs(conjugationRoots.verbalNouns, VERBAL_NOUN, rootLetters);
    }

    private NounDetailedConjugationPair[] createAdverbPairs(RootLetters rootLetters) {
        return createPairs(conjugationRoots.adverbs, NOUN_OF_PLACE_AND_TIME, rootLetters);
    }

    private VerbDetailedConjugationPair createPassiveTensePair(RootLetters rootLetters) {
        return createTensePair(PRESENT_PASSIVE_TENSE, PAST_PASSIVE_TENSE, conjugationRoots.presentPassiveTense,
                conjugationRoots.pastPassiveTense, rootLetters);
    }

    private VerbDetailedConjugationPair createImperativeAndForbiddingPair(RootLetters rootLetters) {
        return createTensePair(FORBIDDING, IMPERATIVE, conjugationRoots.forbidding, conjugationRoots.imperative, rootLetters);
    }

    private NounDetailedConjugationPair[] createPairs(NounRootBase[] pairs, SarfTermType termType, RootLetters rootLetters) {
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
                result = add(result, createPair(subList.get(1), subList.get(0), termType, rootLetters));
                fromIndex = toIndex;
                toIndex += NUM_OF_COLUMNS;
            }
        }
        return result;
    }

    private NounDetailedConjugationPair createPair(NounRootBase leftBase, NounRootBase rightBase, SarfTermType termType,
                                                   RootLetters rootLetters) {
        SarfTermType leftTerm = (leftBase == null) ? null : termType;
        SarfTermType rightTerm = (rightBase == null) ? null : termType;
        return createParticiplePair(leftTerm, rightTerm, leftBase, rightBase, rootLetters);
    }
}
