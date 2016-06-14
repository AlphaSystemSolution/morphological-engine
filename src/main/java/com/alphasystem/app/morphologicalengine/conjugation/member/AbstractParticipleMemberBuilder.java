package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
import static java.lang.String.format;

/**
 * @author sali
 */
public abstract class AbstractParticipleMemberBuilder extends AbstractConjugationMemberBuilder<NounConjugationGroup, NounRootBase>
        implements ParticipleMemberBuilder {

    private NounTransformer singularTransformer;

    private NounTransformer dualTransformer;

    private NounTransformer pluralTransformer;

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NounRootBase rootBase, RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, Form form, RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected NounRootBase getRootBase(Form form) {
        NounRootBase rootBase = null;
        final SarfTermType termType = getTermType();
        switch (termType) {
            case ACTIVE_PARTICIPLE_MASCULINE:
                rootBase = form.getActiveParticipleMasculine();
                break;
            case ACTIVE_PARTICIPLE_FEMININE:
                rootBase = form.getActiveParticipleFeminine();
                break;
            case PASSIVE_PARTICIPLE_MASCULINE:
                rootBase = form.getPassiveParticipleMasculine();
                break;
            case PASSIVE_PARTICIPLE_FEMININE:
                rootBase = form.getPassiveParticipleFeminine();
                break;
            case VERBAL_NOUN:
                break;
            case NOUN_OF_PLACE_AND_TIME:
                break;
        }
        if (rootBase == null) {
            throw new NullPointerException(format("No root base defined for term \"%s\" in form \"%s\".",
                    termType.name(), form.getTemplate().name()));
        }
        return rootBase;
    }

    @Override
    protected void initializeTransformers() {
        NounSupport baseWord = rootBase.getSingularBaseWord();
        String name = (baseWord == null) ? null : baseWord.getSingularRootName();
        singularTransformer = GUICE_SUPPORT.getNounTransformer(name);

        baseWord = rootBase.getDualBaseWord();
        name = (baseWord == null) ? null : baseWord.getDualRootName();
        dualTransformer = GUICE_SUPPORT.getNounTransformer(name);

        baseWord = rootBase.getPluralBaseWord();
        name = (baseWord == null) ? null : baseWord.getPluralRootName();
        pluralTransformer = GUICE_SUPPORT.getNounTransformer(name);
    }

    @Override
    public NounConjugationGroup doConjugation() {
        if (conjugationGroup == null) {
            conjugationGroup = new NounConjugationGroup();
            final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
            final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
            final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
            final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

            final NounConjugation singularConjugation = doTransform(singularTransformer, rootBase.getSingularBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
            final NounConjugation dualConjugation = doTransform(dualTransformer, rootBase.getDualBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
            final NounConjugation pluralConjugation = doTransform(pluralTransformer, rootBase.getPluralBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);

            conjugationGroup.setNominative(new ConjugationTuple(singularConjugation.getNominative(),
                    dualConjugation.getNominative(), pluralConjugation.getNominative()));
            conjugationGroup.setAccusative(new ConjugationTuple(singularConjugation.getAccusative(),
                    dualConjugation.getAccusative(), pluralConjugation.getAccusative()));
            conjugationGroup.setGenitive(new ConjugationTuple(singularConjugation.getGenitive(),
                    dualConjugation.getGenitive(), pluralConjugation.getGenitive()));
            conjugationGroup.setTermType(getTermType());
        }
        return conjugationGroup;
    }

    private NounConjugation doTransform(NounTransformer transformer, NounSupport baseWord, ArabicLetterType firstRadical,
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
        final ConjugationTuple nominative = conjugationGroup.getNominative();
        return (nominative == null) ? null : nominative.getSingular();
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_MASCULINE;
    }
}
