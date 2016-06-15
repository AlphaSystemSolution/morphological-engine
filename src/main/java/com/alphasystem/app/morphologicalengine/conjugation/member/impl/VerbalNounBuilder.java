package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
class VerbalNounBuilder extends ActiveParticipleMasculineBuilder {

    VerbalNounBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return VERBAL_NOUN;
    }
}
