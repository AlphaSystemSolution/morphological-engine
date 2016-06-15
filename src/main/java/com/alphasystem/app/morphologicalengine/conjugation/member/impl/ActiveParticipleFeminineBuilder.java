package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
@Singleton
class ActiveParticipleFeminineBuilder extends AbstractParticipleMemberBuilder {

    ActiveParticipleFeminineBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_FEMININE;
    }
}
