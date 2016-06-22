package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;

/**
 * @author sali
 */
@Singleton
class ImperativeMemberBuilder extends AbstractTenseMemberBuilder {

    ImperativeMemberBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return IMPERATIVE;
    }
}
