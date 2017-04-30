package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * @author sali
 */
public abstract class AbstractVerbTransformerFactory implements VerbTransformerFactory {

    @Override
    public VerbConjugationGroup doConjugation(RuleProcessor ruleProcessor, SarfTermType sarfTermType, OutputFormat outputFormat,
                                              VerbRootBase rootBase, RootLetters rootLetters) {

        final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
        final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
        final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
        final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();

        VerbConjugationGroup conjugationGroup = new VerbConjugationGroup();
        conjugationGroup.setId(rootBase.getId());
        conjugationGroup.setMasculineThirdPerson(doTransform(ruleProcessor, thirdPersonMasculineTransformer(), sarfTermType,
                outputFormat, rootBase, firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFeminineThirdPerson(doTransform(ruleProcessor, thirdPersonFeminineTransformer(), sarfTermType,
                outputFormat, rootBase, firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setMasculineSecondPerson(doTransform(ruleProcessor, secondPersonMasculineTransformer(), sarfTermType,
                outputFormat, rootBase, firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFeminineSecondPerson(doTransform(ruleProcessor, secondPersonFeminineTransformer(), sarfTermType,
                outputFormat, rootBase, firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setFirstPerson(doTransform(ruleProcessor, firstPersonTransformer(), sarfTermType, outputFormat,
                rootBase, firstRadical, secondRadical, thirdRadical, fourthRadical));
        conjugationGroup.setTermType(sarfTermType);

        return conjugationGroup;
    }

    private ConjugationTuple doTransform(RuleProcessor ruleProcessor, VerbTransformer transformer, SarfTermType sarfTermType,
                                         OutputFormat outputFormat, VerbRootBase rootBase, ArabicLetterType firstRadical,
                                         ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && rootBase != null) {
            return transformer.doTransform(ruleProcessor, new RootWord(rootBase.getRoot().getRootWord()),
                    sarfTermType, outputFormat, firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }
}
