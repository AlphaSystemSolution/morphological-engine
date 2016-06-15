package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
@Singleton
class PassiveParticipleMasculineBuilder extends ActiveParticipleMasculineBuilder {

    PassiveParticipleMasculineBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_MASCULINE;
    }
}
