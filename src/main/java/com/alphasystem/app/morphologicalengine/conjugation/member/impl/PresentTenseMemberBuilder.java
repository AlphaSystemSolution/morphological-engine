package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;

/**
 * @author sali
 */
@Singleton
class PresentTenseMemberBuilder extends AbstractTenseMemberBuilder {

    PresentTenseMemberBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_TENSE;
    }
}
