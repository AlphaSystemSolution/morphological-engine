package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;

/**
 * @author sali
 */
class ForbiddingMemberBuilder extends AbstractTenseMemberBuilder {

    ForbiddingMemberBuilder() {
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return null;
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return null;
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return null;
    }

    @Override
    public SarfTermType getTermType() {
        return FORBIDDING;
    }
}
