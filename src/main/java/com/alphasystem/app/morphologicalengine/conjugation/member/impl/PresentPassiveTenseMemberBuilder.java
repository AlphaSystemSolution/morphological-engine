package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_PASSIVE_TENSE;

/**
 * @author sali
 */
@Singleton
class PresentPassiveTenseMemberBuilder extends PresentTenseMemberBuilder {

    PresentPassiveTenseMemberBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_PASSIVE_TENSE;
    }
}
