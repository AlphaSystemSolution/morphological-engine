package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
public class PresentTenseMemberBuilder extends AbstractTenseMemberBuilder {

    @AssistedInject
    PresentTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted VerbRootBase rootBase,
                              @Assisted RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    @AssistedInject
    PresentTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                              @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER);
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_TENSE;
    }
}
