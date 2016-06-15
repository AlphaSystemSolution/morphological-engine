package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public abstract class AbstractParticipleMemberBuilder implements ParticipleMemberBuilder {

    protected AbstractParticipleMemberBuilder() {
    }

    private NounTransformer getSingularTransformer(NounRootBase rootBase) {
        NounSupport baseWord = rootBase.getSingularBaseWord();
        String name = (baseWord == null) ? null : baseWord.getSingularRootName();
        return GUICE_SUPPORT.getNounTransformer(name);
    }

    private NounTransformer getDualTransformer(NounRootBase rootBase) {
        NounSupport baseWord = rootBase.getDualBaseWord();
        String name = (baseWord == null) ? null : baseWord.getDualRootName();
        return GUICE_SUPPORT.getNounTransformer(name);
    }

    private NounTransformer getPluralTransformer(NounRootBase rootBase) {
        NounSupport baseWord = rootBase.getPluralBaseWord();
        String name = (baseWord == null) ? null : baseWord.getDualRootName();
        return GUICE_SUPPORT.getNounTransformer(name);
    }

    @Override
    public NounConjugationGroup doConjugation(RuleProcessor ruleProcessor, NounRootBase rootBase, RootLetters rootLetters) {
        NounConjugationGroup conjugationGroup = new NounConjugationGroup();
        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

        NounTransformer singularTransformer = getSingularTransformer(rootBase);
        NounTransformer dualTransformer = getDualTransformer(rootBase);
        NounTransformer pluralTransformer = getPluralTransformer(rootBase);

        final NounConjugation singularConjugation = doTransform(ruleProcessor, singularTransformer, rootBase.getSingularBaseWord(),
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        final NounConjugation dualConjugation = doTransform(ruleProcessor, dualTransformer, rootBase.getDualBaseWord(),
                firstRadical, secondRadical, thirdRadical, fourthRadical);
        final NounConjugation pluralConjugation = doTransform(ruleProcessor, pluralTransformer, rootBase.getPluralBaseWord(),
                firstRadical, secondRadical, thirdRadical, fourthRadical);

        conjugationGroup.setNominative(new ConjugationTuple(singularConjugation.getNominative(),
                dualConjugation.getNominative(), pluralConjugation.getNominative()));
        conjugationGroup.setAccusative(new ConjugationTuple(singularConjugation.getAccusative(),
                dualConjugation.getAccusative(), pluralConjugation.getAccusative()));
        conjugationGroup.setGenitive(new ConjugationTuple(singularConjugation.getGenitive(),
                dualConjugation.getGenitive(), pluralConjugation.getGenitive()));
        conjugationGroup.setTermType(getTermType());
        return conjugationGroup;
    }

    private NounConjugation doTransform(RuleProcessor ruleProcessor, NounTransformer transformer, NounSupport baseWord,
                                        ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && baseWord != null) {
            return transformer.doTransform(ruleProcessor, new RootWord(baseWord.getRootWord()).withSarfTermType(getTermType()),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_MASCULINE;
    }
}
