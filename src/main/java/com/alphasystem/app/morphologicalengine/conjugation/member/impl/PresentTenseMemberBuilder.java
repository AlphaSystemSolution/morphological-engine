package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
@Singleton
class PresentTenseMemberBuilder extends AbstractTenseMemberBuilder {

    PresentTenseMemberBuilder() {
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
