package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;

/**
 * @author sali
 */
public abstract class AbstractTenseMemberBuilder implements TenseMemberBuilder {

    protected AbstractTenseMemberBuilder() {
    }

    protected abstract VerbTransformer initializeThirdPersonMasculineTransformer();

    protected abstract VerbTransformer initializeThirdPersonFeminineTransformer();

    protected abstract VerbTransformer initializeSecondPersonMasculineTransformer();

    protected abstract VerbTransformer initializeSecondPersonFeminineTransformer();

    protected abstract VerbTransformer initializeFirstPersonTransformer();

    @Override
    public VerbConjugationGroup doConjugation(RuleProcessor ruleProcessor, VerbRootBase rootBase, RootLetters rootLetters) {
        VerbConjugationGroup conjugationGroup = new VerbConjugationGroup();
        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

        VerbTransformer thirdPersonMasculineTransformer = initializeThirdPersonMasculineTransformer();
        VerbTransformer thirdPersonFeminineTransformer = initializeThirdPersonFeminineTransformer();
        VerbTransformer secondPersonMasculineTransformer = initializeSecondPersonMasculineTransformer();
        VerbTransformer secondPersonFeminineTransformer = initializeSecondPersonFeminineTransformer();
        VerbTransformer firstPersonTransformer = initializeFirstPersonTransformer();

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
