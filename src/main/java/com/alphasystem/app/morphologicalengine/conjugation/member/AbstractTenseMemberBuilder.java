package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;

/**
 * @author sali
 */
public abstract class AbstractTenseMemberBuilder implements TenseMemberBuilder {

    protected AbstractTenseMemberBuilder() {
    }

    private VerbTransformer initializeThirdPersonMasculineTransformer(VerbRootBase rootBase) {
        return getTransformer(rootBase.getRoot().getThirdPersonMasculineName());
    }

    private VerbTransformer initializeThirdPersonFeminineTransformer(VerbRootBase rootBase) {
        return getTransformer(rootBase.getRoot().getThirdPersonFeminineName());
    }

    private VerbTransformer initializeSecondPersonMasculineTransformer(VerbRootBase rootBase) {
        return getTransformer(rootBase.getRoot().getSecondPersonMasculineName());
    }

    private VerbTransformer initializeSecondPersonFeminineTransformer(VerbRootBase rootBase) {
        return getTransformer(rootBase.getRoot().getSecondPersonFeminineName());
    }

    private VerbTransformer initializeFirstPersonTransformer(VerbRootBase rootBase) {
        return getTransformer(rootBase.getRoot().getFirstPersonName());
    }

    private VerbTransformer getTransformer(String name){
        return (name == null) ? null : GUICE_SUPPORT.getVerbTransformer(name);
    }

    @Override
    public VerbConjugationGroup doConjugation(RuleProcessor ruleProcessor, VerbRootBase rootBase, RootLetters rootLetters) {
        VerbConjugationGroup conjugationGroup = new VerbConjugationGroup();
        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

        VerbTransformer thirdPersonMasculineTransformer = initializeThirdPersonMasculineTransformer(rootBase);
        VerbTransformer thirdPersonFeminineTransformer = initializeThirdPersonFeminineTransformer(rootBase);
        VerbTransformer secondPersonMasculineTransformer = initializeSecondPersonMasculineTransformer(rootBase);
        VerbTransformer secondPersonFeminineTransformer = initializeSecondPersonFeminineTransformer(rootBase);
        VerbTransformer firstPersonTransformer = initializeFirstPersonTransformer(rootBase);

        conjugationGroup.setMasculineThirdPerson(doTransform(ruleProcessor, thirdPersonMasculineTransformer, rootBase,
                firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFeminineThirdPerson(doTransform(ruleProcessor, thirdPersonFeminineTransformer, rootBase,
                firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setMasculineSecondPerson(doTransform(ruleProcessor, secondPersonMasculineTransformer, rootBase,
                firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFeminineSecondPerson(doTransform(ruleProcessor, secondPersonFeminineTransformer, rootBase,
                firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFirstPerson(doTransform(ruleProcessor, firstPersonTransformer, rootBase, firstRadical,
                secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setTermType(getTermType());
        return conjugationGroup;
    }

    private ConjugationTuple doTransform(RuleProcessor ruleProcessor, VerbTransformer transformer, VerbRootBase rootBase, ArabicLetterType firstRadical,
                                         ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && rootBase != null) {
            return transformer.doTransform(ruleProcessor, new RootWord(rootBase.getRoot().getRootWord()).withSarfTermType(getTermType()),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_TENSE;
    }
}
