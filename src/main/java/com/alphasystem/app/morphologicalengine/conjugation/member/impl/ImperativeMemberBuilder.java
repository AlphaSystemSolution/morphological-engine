package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
class ImperativeMemberBuilder extends AbstractTenseMemberBuilder {

    ImperativeMemberBuilder() {
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
        return GUICE_SUPPORT.getVerbTransformer(IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return null;
    }

    @Override
    public SarfTermType getTermType() {
        return IMPERATIVE;
    }
}
