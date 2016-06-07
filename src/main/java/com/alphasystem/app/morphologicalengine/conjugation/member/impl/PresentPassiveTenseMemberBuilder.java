package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.VerbRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_PASSIVE_TENSE;

/**
 * @author sali
 */
public class PresentPassiveTenseMemberBuilder extends AbstractTenseMemberBuilder {

    @AssistedInject
    PresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted VerbRootBase rootBase,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    @AssistedInject
    PresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return verbTransformerFactory.getPresentTenseThirdPersonMasculineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return verbTransformerFactory.getPresentTenseThirdPersonFeminineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return verbTransformerFactory.getPresentTenseSecondPersonMasculineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return verbTransformerFactory.getPresentTenseSecondPersonFeminineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return verbTransformerFactory.getPresentTenseFirstPersonTransformer(getRuleProcessor());
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_PASSIVE_TENSE;
    }
}
