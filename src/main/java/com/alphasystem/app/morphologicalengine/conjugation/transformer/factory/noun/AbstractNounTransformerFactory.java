package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public abstract class AbstractNounTransformerFactory implements NounTransformerFactory {

    @Override
    public NounConjugationGroup doConjugation(RuleProcessor ruleProcessor, SarfTermType sarfTermType,
                                              NounRootBase rootBase, RootLetters rootLetters) {
        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

        final NounConjugation singularConjugation = doTransform(ruleProcessor, singularTransformer(), sarfTermType,
                rootBase.getSingularBaseWord(), firstRadical, secondRadical, thirdRadical, fourthRadical);
        final NounConjugation dualConjugation = doTransform(ruleProcessor, dualTransformer(), sarfTermType,
                rootBase.getDualBaseWord(), firstRadical, secondRadical, thirdRadical, fourthRadical);
        final NounConjugation pluralConjugation = doTransform(ruleProcessor, pluralTransformer(), sarfTermType,
                rootBase.getPluralBaseWord(), firstRadical, secondRadical, thirdRadical, fourthRadical);

        NounConjugationGroup conjugationGroup = new NounConjugationGroup();

        conjugationGroup.setNominative(new ConjugationTuple(singularConjugation.getNominative(),
                dualConjugation.getNominative(), pluralConjugation.getNominative()));
        conjugationGroup.setAccusative(new ConjugationTuple(singularConjugation.getAccusative(),
                dualConjugation.getAccusative(), pluralConjugation.getAccusative()));
        conjugationGroup.setGenitive(new ConjugationTuple(singularConjugation.getGenitive(),
                dualConjugation.getGenitive(), pluralConjugation.getGenitive()));
        conjugationGroup.setTermType(sarfTermType);

        return conjugationGroup;
    }

    private NounConjugation doTransform(RuleProcessor ruleProcessor, NounTransformer transformer, SarfTermType sarfTermType,
                                        NounSupport baseWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                        ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && baseWord != null) {
            return transformer.doTransform(ruleProcessor, new RootWord(baseWord.getRootWord()),
                    sarfTermType, firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }
}
