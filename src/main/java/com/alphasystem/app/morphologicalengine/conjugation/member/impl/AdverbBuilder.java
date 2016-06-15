package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import javax.inject.Singleton;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.NOUN_OF_PLACE_AND_TIME;

/**
 * @author sali
 */
@Singleton
class AdverbBuilder extends VerbalNounBuilder {

    AdverbBuilder() {
    }

    @Override
    public SarfTermType getTermType() {
        return NOUN_OF_PLACE_AND_TIME;
    }
}
