package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
@Singleton
class PassiveParticipleFeminineBuilder extends ActiveParticipleFeminineBuilder {

    PassiveParticipleFeminineBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_FEMININE;
    }
}
