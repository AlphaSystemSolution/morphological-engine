package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.RootWordSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_TENSE;

/**
 * @author sali
 */
public abstract class AbstractTenseMemberBuilder extends AbstractConjugationMemberBuilder<VerbConjugationGroup, VerbRootBase>
        implements TenseMemberBuilder {

    protected VerbTransformer thirdPersonMasculineTransformer;
    protected VerbTransformer thirdPersonFeminineTransformer;
    protected VerbTransformer secondPersonMasculineTransformer;
    protected VerbTransformer secondPersonFeminineTransformer;
    protected VerbTransformer firstPersonTransformer;

    protected AbstractTenseMemberBuilder(RuleProcessor ruleProcessor, VerbRootBase rootBase, RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    protected AbstractTenseMemberBuilder(RuleProcessor ruleProcessor, Form form, RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected VerbRootBase getRootBase(Form form) {
        VerbRootBase rootBase = null;
        final SarfTermType termType = getTermType();
        switch (termType) {
            case PAST_TENSE:
                rootBase = form.getPastTense();
                break;
            case PRESENT_TENSE:
                rootBase = form.getPresentTense();
                break;
            case PAST_PASSIVE_TENSE:
                rootBase = form.getPastPassiveTense();
                break;
            case PRESENT_PASSIVE_TENSE:
                rootBase = form.getPresentPassiveTense();
                break;
            case IMPERATIVE:
                rootBase = form.getImperative();
                break;
            case FORBIDDING:
                rootBase = form.getForbidding();
                break;
        }
        return rootBase;
    }

    @Override
    protected void initializeTransformers() {
        thirdPersonMasculineTransformer = initializeThirdPersonMasculineTransformer();
        thirdPersonFeminineTransformer = initializeThirdPersonFeminineTransformer();
        secondPersonMasculineTransformer = initializeSecondPersonMasculineTransformer();
        secondPersonFeminineTransformer = initializeSecondPersonFeminineTransformer();
        firstPersonTransformer = initializeFirstPersonTransformer();
    }

    protected abstract VerbTransformer initializeThirdPersonMasculineTransformer();

    protected abstract VerbTransformer initializeThirdPersonFeminineTransformer();

    protected abstract VerbTransformer initializeSecondPersonMasculineTransformer();

    protected abstract VerbTransformer initializeSecondPersonFeminineTransformer();

    protected abstract VerbTransformer initializeFirstPersonTransformer();

    @Override
    public VerbConjugationGroup doConjugation() {
        if (conjugationGroup == null) {
            conjugationGroup = new VerbConjugationGroup();
            final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
            final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
            final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
            final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();
            conjugationGroup.setMasculineThirdPerson(doTransform(thirdPersonMasculineTransformer, rootBase.getRoot(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            conjugationGroup.setFeminineThirdPerson(doTransform(thirdPersonFeminineTransformer, rootBase.getRoot(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            conjugationGroup.setMasculineSecondPerson(doTransform(secondPersonMasculineTransformer, rootBase.getRoot(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            conjugationGroup.setFeminineSecondPerson(doTransform(secondPersonFeminineTransformer, rootBase.getRoot(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            conjugationGroup.setFirstPerson(doTransform(firstPersonTransformer, rootBase.getRoot(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical));
            conjugationGroup.setTermType(getTermType());
        }
        return conjugationGroup;
    }

    private ConjugationTuple doTransform(VerbTransformer transformer, RootWordSupport baseWord, ArabicLetterType firstRadical,
                                         ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && baseWord != null) {
            return transformer.doTransform(getRuleProcessor(), new RootWord(baseWord.getRootWord()).withSarfTermType(getTermType()),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }

    @Override
    public RootWord getDefaultConjugation() {
        if (conjugationGroup == null) {
            doConjugation();
        }
        return conjugationGroup.getMasculineThirdPerson().getSingular();
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_TENSE;
    }
}
