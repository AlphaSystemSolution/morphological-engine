package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.morphologicalanalysis.morphology.model.VerbRootBase;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

/**
 * @author sali
 */
public class PastTenseMemberBuilder extends AbstractTenseMemberBuilder {

    @AssistedInject
    PastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted VerbRootBase rootBase,
                           @Assisted RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    @AssistedInject
    PastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                           @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return verbTransformerFactory.getPastTenseThirdPersonMasculineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return verbTransformerFactory.getPastTenseThirdPersonFeminineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return verbTransformerFactory.getPastTenseSecondPersonMasculineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return verbTransformerFactory.getPastTenseSecondPersonFeminineTransformer(getRuleProcessor());
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return verbTransformerFactory.getPastTenseFirstPersonTransformer(getRuleProcessor());
    }
}
