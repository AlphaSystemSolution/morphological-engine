package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PAST_PASSIVE_TENSE;

/**
 * @author sali
 */
@Singleton
class PastPassiveTenseMemberBuilder extends PastTenseMemberBuilder {

    PastPassiveTenseMemberBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return PAST_PASSIVE_TENSE;
    }
}
