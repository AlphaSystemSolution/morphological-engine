package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NounDetailedConjugationPair extends DetailedConjugationPair<NounConjugationGroup> {

    public NounDetailedConjugationPair(NounConjugationGroup leftSideConjugations, NounConjugationGroup rightSideConjugations) {
        super(leftSideConjugations, rightSideConjugations);
    }
}
