package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;

/**
 * @author sali
 */
@Singleton
class ForbiddingMemberBuilder extends AbstractTenseMemberBuilder {

    ForbiddingMemberBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return FORBIDDING;
    }
}
