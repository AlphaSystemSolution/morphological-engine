package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.ChartMode;
import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationHeader;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
public final class ConjugationHeaderBuilder {

    private final ConjugationRoots conjugationRoots;
    private final RuleProcessor ruleProcessor;

    public ConjugationHeaderBuilder(ConjugationRoots conjugationRoots, RuleProcessor ruleProcessor) {
        this.conjugationRoots = conjugationRoots;
        this.ruleProcessor = ruleProcessor;
    }

    public ConjugationHeader createConjugationHeader(RootLetters rootLetters) {
        final RootWord pastTenseRoot = getRootWord(PAST_TENSE, rootLetters);
        final RootWord presentTenseRoot = getRootWord(PRESENT_TENSE, rootLetters);

        WordStatus status = new WordStatus(rootLetters);
        RootType rootType = RootType.CONSONANT;
        VerbType verbType = VerbType.CONSONANT;
        WeakVerbType weakVerbType = null;
        if (status.isWeak()) {
            rootType = RootType.WEAK;
            if (status.twoSeparateLettersWeak()) {
                verbType = VerbType.TWO_SEPARATE_RADICALS_WEAK;
            } else if (status.twoConsecutiveLettersWeak()) {
                verbType = VerbType.TWO_CONSECUTIVE_RADICALS_WEAK;
            } else if (status.isAssimilated()) {
                verbType = VerbType.FIRST_RADICAL_WEAK;
                weakVerbType = status.isFirstRadicalWaw() ? WeakVerbType.FIRST_RADICAL_WEAK_WAW
                        : WeakVerbType.FIRST_RADICAL_WEAK_YA;
            } else if (status.isHollow()) {
                verbType = VerbType.SECOND_RADICAL_WEAK;
                weakVerbType = status.isSecondRadicalWaw() ? WeakVerbType.SECOND_RADICAL_WEAK_WAW
                        : WeakVerbType.SECOND_RADICAL_WEAK_YA;
            } else if (status.isDefective()) {
                verbType = VerbType.THIRD_RADICAL_WEAK;
                weakVerbType = status.isThirdRadicalWaw() ? WeakVerbType.THIRD_RADICAL_WEAK_WAW
                        : WeakVerbType.THIRD_RADICAL_WEAK_YA;
            }
        } else if (status.isDoubledLettered()) {
            verbType = VerbType.DOUBLE_LETTERED;
        } else if (status.isHamzatted()) {
            if (status.isFirstRadicalHamza()) {
                verbType = VerbType.FIRST_RADICAL_HAMZA;
            } else if (status.isSecondRadicalHamza()) {
                verbType = VerbType.SECOND_RADICAL_HAMZA;
            } else if (status.isThirdRadicalHamza()) {
                verbType = VerbType.THIRD_RADICAL_HAMZA;
            }
        }
        final NamedTemplate template = conjugationRoots.template;
        ChartMode chartMode = new ChartMode(template, rootType, verbType, weakVerbType);
        return new ConjugationHeader(conjugationRoots.translation, pastTenseRoot, presentTenseRoot, template.getLabel(),
                chartMode, rootLetters);
    }

    private RootWord getRootWord(SarfTermType termType, RootLetters rootLetters) {
        final GuiceSupport guiceSupport = GuiceSupport.getInstance();
        final Verb verb = PAST_TENSE.equals(termType) ? conjugationRoots.getPastTense().getRoot() :
                conjugationRoots.getPresentTense().getRoot();
        final VerbTransformer verbTransformer = guiceSupport.getVerbTransformer(verb.getThirdPersonMasculineName());
        return verbTransformer.doTransform(ruleProcessor, new RootWord(verb.getRootWord()).withSarfTermType(termType),
                rootLetters.getFirstRadical(), rootLetters.getSecondRadical(), rootLetters.getThirdRadical(),
                rootLetters.getFourthRadical()).getSingular();
    }
}