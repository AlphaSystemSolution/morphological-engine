package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;

import javax.inject.Singleton;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;

/**
 * @author sali
 */
@Singleton
class PastTenseMemberBuilder extends AbstractTenseMemberBuilder {

    PastTenseMemberBuilder() {
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_FIRST_PERSON_TRANSFORMER);
    }
}
